package com.lbh.request.factory;

import com.lbh.request.Factory;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright..@lbhbinhao@163.com
 * Author:liubinhao
 * Date:2020/12/29
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
public class ResourceFactory implements Factory<StaticResource> {


    public static final String RESOURCE_PATH = "D:/image";


    public static Map<String,StaticResource> resourceFactory = new ConcurrentHashMap<>(12);


    @Override
    public StaticResource get(String uri) {
        return resourceFactory.get(uri);
    }

    public void init(){

    }

    public static void init(String filepath){
        File file = new File(filepath);
        if (!file.isDirectory()) {
            resourceFactory.put("/"+file.getName(),new StaticResource(file.getName(),0));
        }
        else {
            String[] filelist = file.list();
            if (filelist == null){
                return;
            }
            for (String s : filelist) {
                init(s);
            }
        }
    }

}
