package com.nengxin.example.exampleproject.model.map;

/**
 * 作者: alan on 2016/9/26 10:47.
 * 邮箱: likaileeopen@163.com
 * 文件名:GCJ2WGS.java
 * 创建时间:
 * 所在包:com
 * 作者:
 * 说明:该类的delta方法可以将高德地图SDK获取到的GPS经纬度转换为真实的经纬度。
 */

import java.util.HashMap;

public class GCJ2WGS {

    public static void main(String[] args) {

        GCJ2WGS wg = new GCJ2WGS();

        HashMap<String, Double> hm = wg.delta(38.123456, 114.654321);

        System.out.println(hm);
    }

    //圆周率 GCJ_02_To_WGS_84
    double PI = 3.14159265358979324;

    /**
     * @author 作者:
     * 方法描述:方法可以将高德地图SDK获取到的GPS经纬度转换为真实的经纬度，可以用于解决安卓系统使用高德SDK获取经纬度的转换问题。
     * @return 转换为真实GPS坐标后的经纬度
     * @throws <异常类型> {@inheritDoc} 异常描述
     */
    public HashMap<String, Double> delta(double lat, double lon) {
        double a = 6378245.0;
        double ee = 0.00669342162296594323;
        double dLat = this.transformLat(lon - 105.0, lat - 35.0);
        double dLon = this.transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * this.PI;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * this.PI);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * this.PI);

        HashMap<String, Double> hm = new HashMap<String, Double>();
        hm.put("lat", lat - dLat);
        hm.put("lon", lon - dLon);
        return hm;
    }

    //转换经度
    public double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * this.PI) + 40.0 * Math.sin(x / 3.0 * this.PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * this.PI) + 300.0 * Math.sin(x / 30.0 * this.PI)) * 2.0 / 3.0;
        return ret;
    }

    //转换纬度
    public double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * this.PI) + 40.0 * Math.sin(y / 3.0 * this.PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * this.PI) + 320 * Math.sin(y * this.PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

}