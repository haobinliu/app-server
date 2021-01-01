package com.lbh.response;

/**
 * Copyright(c)lbhbinhao@163.com
 * @author liubinhao
 * @date 2020/12/31
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
public class RespUtils {
    /**
     * 获取请求的文件
     * @param uri 请求内容
     * @return 请求文件类型
     */
    public static String getType(String uri) {
        System.out.println("uri:" + uri);
        String stringDot = ".";
        String dot = "\\.";
        String[] fileName;
        if (uri.contains(stringDot)) {
            fileName = uri.split(dot);
            return fileName[fileName.length - 1];
        }
        return null;
    }

    protected static class Range {
        private long start;
        private long offset;
        private long size;
        public long getStart() {
            return start;
        }
        public void setStart(long start) {
            this.start = start;
        }
        public long getOffset() {
            return offset;
        }
        public void setOffset(long offset) {
            this.offset = offset;
        }
        public long getSize() {
            return size;
        }
        public void setSize(int size) {
            this.size = size;
        }
    }

    public static Range requestRange(String ifRange,int size) {
        Range range = new Range();
        long startByte = 0;
        //结束下载位置
        long endByte = size - 1;
        ifRange = ifRange.substring(ifRange.lastIndexOf("=") + 1).trim();
        String[] ranges = ifRange.split("-");
        try {
            //判断range的类型
            if (ranges.length == 1) {
                //类型一：bytes=-2343
                if (ifRange.startsWith("-")) {
                    endByte = Long.parseLong(ranges[0]);
                }
                //类型二：bytes=2343-
                else if (ifRange.endsWith("-")) {
                    startByte = Long.parseLong(ranges[0]);
                    if (startByte+1024*1024<size) {
                        endByte = startByte + 1024 * 1024;
                    }
                    else{
                        endByte = size - 1;
                    }
                }
            }
            //类型三：bytes=22-2343
            else if (ranges.length == 2) {
                startByte = Long.parseLong(ranges[0]);
                endByte = Long.parseLong(ranges[1]);
            }

        } catch (NumberFormatException e) {
            startByte = 0;
            System.out.println("Range Occur Error,Message:{"+e.getLocalizedMessage()+"}");
        }
        range.setStart(startByte);
        range.setSize(size);
        range.setOffset(endByte);
        System.out.println("文件开始位置：{"+startByte+"}，文件结束位置：{"+endByte+"}，文件总长度：{"+size+"}");
        return range;
    }

}
