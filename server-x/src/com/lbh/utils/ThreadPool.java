package com.lbh.utils;

import com.lbh.request.Req;

import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

/**
 * ++++ ______ @author       liubinhao   ______             ______
 * +++/     /|                         /     /|           /     /|
 * +/_____/  |                       /_____/  |         /_____/  |
 * |     |   |                      |     |   |        |     |   |
 * |     |   |                      |     |   |        |     |   |
 * |     |   |                      |     |   |________|     |   |
 * |     |   |                      |     |  /         |     |   |
 * |     |   |                      |     |/___________|     |   |
 * |     |   |___________________   |     |   |        |     |   |
 * |     |  /                  / |  |     |   |        |     |   |
 * |     |/ _________________/   |  |     |   |        |     |   |
 * |                         |  /   |     |  /         |     |  /
 * |_________________________|/b    |_____|/           |_____|/
 *
 * @date 2020/9/23
 */

public class ThreadPool {


    public static ConcurrentHashMap<SocketChannel, Req> requestMap = new ConcurrentHashMap<>(5);

    static ThreadFactory myThreadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable);
        }
    };

    public static ExecutorService pool = new ThreadPoolExecutor(1, 6,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), myThreadFactory, new ThreadPoolExecutor.AbortPolicy());
}
