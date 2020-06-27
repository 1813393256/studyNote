package com.test.interviewTest;

import com.sun.xml.internal.ws.util.StringUtils;

/**
 * @Author laizhiqiang
 * @Description:java字符串分割问题
 * 编写一个截取字符串的函数，
 * 输入为一个字符串和字节数，输出为按字节截取的字符串。
 * 但是要保证汉字不被截半个，如“我ABC”4，应该截为“我AB”，
 * 输入“我ABC汉DEF”，6，应该输出为“我ABC”而不是“我ABC+汉的半个
 * @Date 2020/6/17 0017 18:56
 */
public class firstTest {
    public static void main(String[] args) {
        String str="我ABC汉DEF, |";
        splitString(str, 7);
    }

    /**
     * @param str
     * @param len
     * 此种方法受编码方式影响，“我”字占3个字节，具体应该是要看编译器设置编码格式
     */
    public static void splitString(String str, int len) {
        char[] chars = str.toCharArray();
        int count=0;
        for (int i=0;i<len;i++){
            System.out.println(chars[i]);
            count+=String.valueOf(chars[i]).getBytes().length;
            if (count<=len){
//                System.out.print(chars[i]);
            }else {
                return;
            }

        }
    }

    /**
     * 本方法使用正则表达式进行解答
     * @param str
     * @param len
     */
    public static void splitString2(String str, int len) {

    }
}
