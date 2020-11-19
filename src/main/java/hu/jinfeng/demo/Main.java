package hu.jinfeng.demo;

import java.text.DecimalFormat;

/**
 * @Author hujinfeng  @Date 2020/11/18
 **/
public class Main {

    public static void main(String[] args) {

        double value = 3.1415926;
        System.out.println(String.format("%.3f", value));       //四舍五入

        DecimalFormat df = new DecimalFormat("#.000");    //也能四舍五入
        System.out.println(df.format(value));
    }
}
