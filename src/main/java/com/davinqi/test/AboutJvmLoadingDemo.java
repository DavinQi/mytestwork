package com.davinqi.test;

/**
 * @Author qixiangqun
 * @Date 2019-09-06 11:59
 * @Version 1.0
 **/
public class AboutJvmLoadingDemo {

    //===============================//
    //
    //      calss文件加载到JVM中的顺序
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

        private  static  String a ="parent";
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

    public  static  class  Dog  extends   Animal {
        private  static  String a ="children";
        static {
            System.out.println("//// children  static ！");
        }

        public Dog() {
            System.out.println("////  children  constroutor");
        }

        @Override
        void eat(String a) {
            System.out.println("//// children eat :"+a);
        }
        @Override
        void sleep(String a) {
            System.out.println("////  children, go to the bed ! ");
        }

    }

    public static void main(String[] args) {

        //代码：
        System.out.println("// >>>>>>>> begin  .........................");
        Dog dog = new Dog();
          dog.eat("苹果");
        System.out.println("// >>>>>>>> end .........................");

        //结果：
        // >>>>>>>> begin  .........................
        //// parent  static ！
        //// children  static ！
        ////  parent  constroutor
        ////  children  constroutor
        //// children eat :苹果
        // >>>>>>>> end .........................

        //总结：

        /**
         *
         *
         * */
    }


    /**
     * 加载
     */
    public void aboutClassLoader() {


    }


}
