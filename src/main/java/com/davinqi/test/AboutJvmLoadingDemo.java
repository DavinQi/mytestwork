package com.davinqi.test;

import java.lang.reflect.Method;

/**
 * @Author qixiangqun
 * @Date 2019-09-06 11:59
 * @Version 1.0
 **/
public class AboutJvmLoadingDemo {

    //===============================//
    //
    //  calss文件加载到JVM中的顺序
    //  1。加载
    //  2。链接 ： 验证，准备，解析
    //  3。初始化
    //===============================//


    /**
     * JVM 主动使用Class才会，进行初始化。
     * <p>
     * 主动使用的场景：
     * 1，new  反射 反序列化 克隆
     * 2，使用当前类的 static方法 或者 static的属性（不包括final常量，他会放在常量池中 ）
     * 3，继承了父类的子类 初始化时先要初始化父类。
     * 4，jvm启动时，包含  main 的方法。
     */

    public static class Animal {
        public static String a = "parent-a";
        public static String b = "parent-b";
        public static final String c = "parent-c  static final ";

        static {
            System.out.println("//// parent  static ！");
        }

        public Animal() {
            System.out.println("////  parent  constroutor");
        }

        void eat(String a) {
            System.out.println("//// parent ");
        }

        void sleep(String a) {
            System.out.println("//// go to the bed ! ");
        }
    }

    public static class Dog extends Animal {
        public static String a = "children-a";

        static {
            System.out.println("//// children  static ！");
        }

        public Dog() {
            System.out.println("////  children  constroutor");
        }

        @Override
        void eat(String a) {
            System.out.println("//// children eat :" + a);
        }

        @Override
        void sleep(String a) {
            System.out.println("////  children, go to the bed ! ");
        }

    }

    public static void main(String[] args) {

        //代码  1 ：
     /*   System.out.println("// >>>>>>>> begin  .........................");
        Dog dog = new Dog();
        dog.eat("苹果");
        System.out.println("// >>>>>>>> end .........................");
*/
        //结果：
        // >>>>>>>> begin  .........................
        //// parent  static ！
        //// children  static ！
        ////  parent  constroutor
        ////  children  constroutor
        //// children eat :苹果
        // >>>>>>>> end .........................


        /**
         * 总结：
         * 1,new加载子类的时候会先加载父类。
         * 2,先加载静态代码块，再加载构造函数。
         */

        /*//代码  2 ：
        System.out.println("// >>>>>>>> begin  .........................");
        String b = Dog.b;
        System.out.println("// b>>>>  " + b);
        String a = Dog.a;
        System.out.println("// a>>>> " + a);
        System.out.println("// >>>>>>>> end .........................");
        */
        //结果：
        // >>>>>>>> begin  .........................
        //// parent  static ！
        // b>>>>  parent-b
        //// children  static ！
        // a>>>> children-a
        // >>>>>>>> end .........................

        /**
         *总结：
         * 1。当调用的静态变量时，虽然用的时Dog，但是实际变量在Animal中，初始化的是Animal。(dog 已经加载但是未到达初始化阶段)
         */
        //代码  3 ：
        System.out.println("// >>>>>>>> begin  .........................");
        String c = Dog.c;
        System.out.println("// c>>>> " + c);
        System.out.println("// >>>>>>>> end .........................");
        //结果  3 ：
        // >>>>>>>> begin  .........................
        // c>>>> parent-c  static final
        // >>>>>>>> end .........................

        /**
         *总结：
         * 1。当调用的类中常量，jVM会优化为常量池中 ，不会初始化类去取值。
         */

    }


    /**
     * 1 : >>>>加载
     * 类的加载是第一阶段：
     */
    public void aboutClassLoader() {
        //1. 通过类全名，获取二进制数据流。来源：去读文件系统，读入JAR,ZIP,二进制数据库，Http协议加载，运行中生成的。。。。
        //2. 解析类的二进制流为内部数据结构
        //3. 创建java.lang.Class类实例。
        try {
            Class<?> aClass = Class.forName("");
            Method[] declaredMethods = aClass.getDeclaredMethods();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 2: >>>> 连接： （1）验证
     * 类的加载是第一阶段：
     */
    public void aboutLink_Check() {

        /**
         *格式检查(calss文件)
         *--- 魔数检查
         *--- 版本检查
         *---
         */
        //语义检查
        //字节码验证
        //符号引用验证
    }

    /**
     * 2: >>>> 连接： （2）准备
     * 类的加载是第一阶段：
     */
    public void aboutLink_Prepare() {

    }
    /**
     * 2: >>>> 连接： （3）解析
     * 类的加载是第一阶段：
     */
    public void aboutLink_Analysis() {

    }

     /**
     * 3: >>>> 初始化：
     * 类的加载是第一阶段：
     */
    public void aboutInitialize() {


    }


}
