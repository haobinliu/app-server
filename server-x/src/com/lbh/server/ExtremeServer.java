package com.lbh.server;

import com.lbh.client.EndPoint;
import com.lbh.request.Req;
import com.lbh.response.DefaultRespParser;
import com.lbh.response.Resp;
import com.lbh.utils.ServerException;
import com.lbh.utils.ThreadPool;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Copyright..@lbhbinhao@163.com
 * Author:liubinhao
 * Date:2020/12/28
 * ++++ ______                           ______             ______
 * +++/     /|                         /     /|           /     /|
 * +/_____/  |                       /_____/  |         /_____/  |
 * |     |   |                      |     |   |        |     |   |
 * |     |   |                      |     |   |________|     |   |
 * |     |   |                      |     |  /         |     |   |
 * |     |   |                      |     |/___________|     |   |
 * |     |   |___________________   |     |____________|     |   |
 * |     |  /                  / |  |     |   |        |     |   |
 * |     |/ _________________/  /   |     |  /         |     |  /
 * |_________________________|/b    |_____|/           |_____|/
 */

public class ExtremeServer {

    private int port;

    private Selector selector;

    public ExtremeServer(int port) {
        this.port = port;
    }

    public void init() {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(this.port));
            this.selector = Selector.open();
            server.register(this.selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void accept(SelectionKey key) {
        try {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel sc = server.accept();
            sc.configureBlocking(false);
            sc.register(this.selector, SelectionKey.OP_READ);
            System.out.println("accept a client : " + sc.socket().getInetAddress().getHostName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException, ExecutionException, InterruptedException, ServerException {
        init();

        for (;;) {
            int events = this.selector.select();
            if (events>0) {
                Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        System.err.println("执行连接事件");
                        accept(key);
                    }
                    else if (key.isReadable()){
                        EndPoint endPoint = new EndPoint(key);
                        Future<Req> future = ThreadPool.pool.submit(endPoint);
                        Req req = future.get();
                        if (req!=null) {
                            //如果为长连接
                            ThreadPool.requestMap.put((SocketChannel) key.channel(), req);
                            if (req.getHeaders().get("Connection").trim().toLowerCase().equals("keep-alive")) {
                                ((SocketChannel) key.channel()).socket().setKeepAlive(true);
                            }
                            key.channel().register(selector, SelectionKey.OP_WRITE);
                        }
                    }
                    else if (key.isWritable()) {
                        Req req = ThreadPool.requestMap.get((SocketChannel) key.channel());
//                        ThreadPool.requestMap.remove((SocketChannel) key.channel());
                        if (req != null) {
                            Resp resp = DefaultRespParser.getRespParser().parse(req);
                            resp.setChannel((SocketChannel) key.channel());
                            System.err.println("响应:" + resp);
                            ThreadPool.pool.submit(resp);
                            System.err.println(req.getHeaders().get("Connection"));
                            if (req.getHeaders().get("Connection").trim().toLowerCase().equals("keep-alive")) {
                                System.err.println("keepalive");
                                key.interestOps(SelectionKey.OP_READ);
                            } else {
                                System.err.println("key取消了");
                                key.cancel();
                            }
                        }
                    }


                }
            }
        }
    }

}
