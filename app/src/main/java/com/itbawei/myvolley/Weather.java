package com.itbawei.myvolley;

/**
 * //java对象中的字段名称和json字符串中的键的名字必须一致,否则报错.解析不成功.
 */

public class Weather {

    private WeatherInfo weatherinfo;

    public void setWeatherinfo(WeatherInfo weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    public WeatherInfo getWeatherinfo() {
        return weatherinfo;
    }


    //错误的定义:和json字符串有出入,严格区分大小写.
//    private WeatherInfo weatherInfo;
//    public void setWeatherinfo(WeatherInfo weatherinfo) {
//        this.weatherInfo = weatherinfo;
//    }
//    public WeatherInfo getWeatherinfo() {
//        return weatherInfo;
//    }
}
