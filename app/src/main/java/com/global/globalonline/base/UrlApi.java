package com.global.globalonline.base;

/**
 * Created by lijl on 16/5/23.
 */
public class UrlApi {

    public  static  String key = "1234567890";
    public static  String action =  "/mapi/";

    /*public  static  String baseUrl = "http://api.50f.cn/mapi/";
   public  static  String baseImageUrl = "http://coins.zhaojizi.com";

*/
    public static boolean b = true;
    public  static  String baseUrl =  (b ? "http://api.globalonline.cc"  : "http://t.api.globalonline.cc") + action;   //接口地址
    public  static  String baseImageUrl =b ? "http://v.globalonline.cc" : "http://t.v.globalonline.cc"; //图片地址接口







}
