package com.qjp.util;

/**
 * @Author yuchuanshen
 * @Date Created by 2017/4/13
 * @Desc qijiapo-com.qjp.util
 */
public class BaiduUtils {
    String AK = "04013b78d38a71987f31ca935eeb18ec";
    String IP_ ="http://api.map.baidu.com/location/ip";
    public static String location(String ipAddr){
            return "";
    }

    public static void main(String[] args) {
        String aa = HttpUtils.get("https://api.map.baidu.com/location/ip?ak=04013b78d38a71987f31ca935eeb18ec&ip=118.122.117.66");
        System.out.println(aa);
    }
}
