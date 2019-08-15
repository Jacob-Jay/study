package com.base.string;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-14 17:04
 *
 * 字符串存于永久代，所以最好使用“”创建已复用，而不是使用new在堆上创建一个新的对象
 */
public class Demo1 {
    public static void main(String[] args) {
     m7();
    }

    public  static void m7() {

        System.out.println(String.format("格式化字符串：%s%S","字符1","字符串2"));
        System.out.println(String.format("格式化字符：%c%C",'c','s'));
        System.out.println(String.format("格式化布尔：%b%B",true,false));
        System.out.println(String.format("格式化十进制数字16：%d",16));
        System.out.println(String.format("格式化十六进制数字16：%x",16));
        System.out.println(String.format("格式化八进制数字16：%o",16));
        //%%  转译为%
        System.out.println(String.format("转义百分号：%% %d",10));

        System.out.println(String.format("字符串换行%n新的一行"));
    }


    /**
     * 只有“”+“”才会返回常量池的引用
     */
    public static void m6() {
        String all = "sasscd";
        String all2 = new String("sasscd");
        String s = "sas";
        String s2 = "scd";
        String s3 = "sas" + "scd";
        String s4 = s + s2;

        System.out.println(all==s3);
        System.out.println(all==s4);

        String s5 = new String("scd");
        String s6 = "sas" + s5;
        String s7 = s + s5;
        System.out.println(all2==s6);
        System.out.println(all2==s7);
    }

    /**
     * ""使用常量池
     * new  如果没有就放入常量池，并在new一个对象
     * intern 返回常量池的引用
     */
    public static void m5() {
        String s = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");


        System.out.println(s==s2);
        System.out.println(s== s3.intern());
        System.out.println(s2== s3);
        System.out.println(s3==s4);
        System.out.println(s3==s4.intern());
        //等价于s3.equals(s4)
        System.out.println(s3.intern() ==s4.intern());
    }


    public static void m4() {
        //将首尾的空格去掉
        String s = "  sd sd ";
        System.out.println(s.trim());
    }


    public static void m3() {
        //将给定CharSequence使用给定分割符连接起来，包括null
        String s = String.join("-", null, new StringBuffer("buffer"), null,"sada");
        System.out.println(s);

        List<String> strings = new ArrayList<>();
        strings.add("asd");
        strings.add(null);
        strings.add("sdad");
        //将给定Iterable实现使用给定分割符连接起来，包括null
        System.out.println(String.join("-", strings));
    }
    /**
     * 没有limit等价于limit=0
     * limit n
     * 大于0 分割次数为n-1  保留结尾的""空船
     * 等0 分割任意次  不保留结尾的""空船
     * 小于0 分割任意次 保留结尾的""空船
     *
     * 首尾  一个匹配符一个""空串
     * 中间  n个匹配符n-1个""空串
     */
    public static void m2() {
        String s = "aabcdaaasaa";
        String[] as = s.split("a");
        String[] as1 = s.split("a", 0);
        String[] as2 = s.split("a", 5);
        String[] as3 = s.split("a", -1);

    }

    public static void m1() {
        String s = "sasd";
        //替换所有
        System.out.println(s.replace('s','e'));
        System.out.println( s.replace("s", "g"));
        //替换第一个
        System.out.println(s.replaceFirst("s", "c"));
        //替换所有
        System.out.println(s.replaceAll("s","l"));

        System.out.println(s.contains(""));
    }

    /**
     * 截取字符串，如果截取的长度等于原串长度，直接返回原串
     */
    public static void t10() {
        String s = "asadad";
        String s2 = s.substring(0, 6);
        System.out.println(s2);
        //与subString一样只是返回类型不同
        CharSequence charSequence = s.subSequence(0, 6);
        System.out.println(charSequence);
        System.out.println(s2==charSequence);

        String sd = s.concat("sd");

    }

    /**
     * 查找给定字符或字符unicode码在给定偏移量之后的源字符串中的位置
     */
    public static void t9() {
        String s = "安sadasd安的";

        System.out.println(s.indexOf("sa"));
        //安  0
        System.out.println(s.indexOf(23433));
        //安    7
        System.out.println(s.indexOf(23433,1));
        System.out.println(s.lastIndexOf("s"));
        System.out.println(s.lastIndexOf(97));
    }

    public static void t8() {
        String s1 = "asasdas";
        String s2 = "As";
        //比较两个字符串给定偏移量 给定长度的子串是否相等
        System.out.println(s1.regionMatches(1,s2,0,2));
        //忽略大小写
        System.out.println(s1.regionMatches(true,1,s2,0,2));

        //偏移给定位置后是否以给定字符串开始
        System.out.println(s1.startsWith("sas",1));

        System.out.println(s1.endsWith("sdas"));
    }


    public static void t7() {
        String s = new String();
        //长度为0
        System.out.println(s.isEmpty());

        String s2 = "我打阿萨大";
        //返回内部char数组的给定下标的值
        System.out.println(s2.charAt(2));

        //返回给定char数组下标的unicode值
        s2 = "我是打算偰";
        System.out.println(s2.codePointAt(4));
        //返回给定char数组下标-1的unicode值
        System.out.println(s2.codePointBefore(5));

       /*s2 = "acdqe";
        //返回两个指定位置unicode的差
        System.out.println(s2.codePointCount(1,4));
        System.out.println(s2.offsetByCodePoints(2, 3));*/


        s2 = "sasdadsad";
        char[]chars = new char[10];
        //吧给定范围的char复制至指定开始位置
        s2.getChars(0,5,chars,2);


    }

    /**
     * 使用byte数组在指定字符集下转换为string
     * 1、全部
     * 2、部分
     */
    public static void t6()  {
        try {
//        byte[] bytes = "我是你".getBytes("utf-8");
            byte[] bytes = {97, 98, 99, 100, 101};
//        String s = new String(bytes, 0, 5, "utf-8");
//        String s = new String(bytes, 0, 5, Charset.forName("utf-8"));
//        String s = new String(bytes, "utf-8");
        String s = new String(bytes,  Charset.forName("utf-8"));
        System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 使用int数组创建一个字符串，int转换为unicode对应的字符
     *
     * 越界异常
     * int对应unicode表位置无字符 参数异常
     */
    public static void t5() {
        int[] a = {97, 98, 99};
        String s = new String(a,0,3);
        System.out.println(s);
    }

    /**
     * 从字符数组创建一个字符串
     * 开始位置
     * 赋值的长度
     * 注意越界异常
     */
    public static void t4() {
        char[] chars = {'s', 'd', 'f', 'g'};
        String a = new String(chars,0,4);
        System.out.println(a);
    }

    /**
     * 构造方法1
     * 构造一个新字符串，指向已存在的字符传常量
     */
    public static void t3() {
        String s1 = new String("sdssd");
        //hash属性值为0，在hashcode方法运行时计算的hash值
        System.out.println(s1.hashCode());
    }

    /**
     *  System.arraycopy() 把源数组部分数据复制到目标数组指定位置
     *  源数组
     *  原数组开始位置  下标0开始
     *  目标数组
     *  目标数组开始位置
     *  长度
     *  如果长度过长或者开始位置无效会抛出越界异常
     */
    public static void t2() {
        String[] a = {"a","b","c","d"};
        String [] b = new String[2];
        System.arraycopy(a,1,b,1,2);
        for (String s : b) {
            System.out.println(s);
        }
    }

    /**
     * 将char数组赋予string之后经过克隆出来的
     * 所以char数组的改变不会影响到string
     */
    public static void t1() {
        char[] chars = new char[10];
        chars[1] = 'a';
        String a = new String(chars);
        chars[2] = 'b';
        System.out.println(a);
    }




}
