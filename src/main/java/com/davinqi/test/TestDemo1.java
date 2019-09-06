package com.davinqi.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author qixiangqun
 * @Date 2019-09-04 17:26
 * @Version 1.0
 **/
public class TestDemo1 {


    //===============================//
    //
    //          泛  型demos
    //
    //===============================//
    /**
     * 利用类型擦除的原理，用反射的手段就绕过了正常开发中编译器不允许的操作限制。
     *
     * @param args
     */
    public static void main(String[] args) {

        List<String> strList = new ArrayList<String>();
        List<Integer> integerList = new ArrayList<Integer>();

        Class<? extends List> aClass = strList.getClass();
        Class<? extends List> bClass = integerList.getClass();
        System.out.println("1 >>> " + aClass);
        System.out.println("2 >>> " + bClass);
        System.out.println(strList.getClass() == integerList.getClass());

        //泛型  类型擦除
        List<Integer> list = new ArrayList<>();
        list.add(23);

        //===============================//
        // 因为泛型的限制 boolean add(E e);//
        // list.add("123"); //TODO error //
        //===============================//


        // 利用反射可以绕过编译器去调用add方法
        // 又因为类型擦除时 boolean add(E e); 等同于 boolean add(Object e);

        try {
            Method method = list.getClass().getDeclaredMethod("add", Object.class);

            method.invoke(list, "test");
            method.invoke(list, 42.9f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //打印。
        for (Object o : list) {
            System.out.println(o);
        }
    }


    /**
     * 【 ====上限 通配符===== 】
     * 类型擦除 相关类
     * 泛型是 Java 1.5 版本才引进的概念，在这之前是没有泛型的概念的，但显然，泛型代码能够很好地和之前版本的代码很好地兼容。
     * 这是因为，泛型信息只存在于代码编译阶段，在进入 JVM 之前，与泛型相关的信息会被擦除掉
     * <p>
     * 上限 通配符
     *
     * @author Richard_yyf
     * @version 1.0 2019/8/29
     */
    public static class EraseHolder<T> {

        T data;

        public EraseHolder(T data) {
            this.data = data;
        }

        public static void main(String[] args) {

            //默认   ======【 上界通配符 】====== 是 Object
            EraseHolder<String> holder = new EraseHolder<>("hello");
            Class clazz = holder.getClass();
            System.out.println("erasure class is:" + clazz.getName());

            Field[] fs = clazz.getDeclaredFields();
            for (Field f : fs) {
                // 那我们可不可以说，泛型类被类型擦除后，相应的类型就被替换成 Object 类型呢？
                System.out.println("Field name " + f.getName() + " type:" + f.getType().getName());
            }
            //======【 上界通配符 】====== 是String
            EraseHolder2<String> holder2 = new EraseHolder2<>("hello");
            clazz = holder2.getClass();
            fs = clazz.getDeclaredFields();
            for (Field f : fs) {
                System.out.println("Field name " + f.getName() + " type:" + f.getType().getName());
            }
        }

        static class EraseHolder2<T extends String> {
            T data;

            public EraseHolder2(T data) {
                this.data = data;
            }
        }
    }

    /**
     * 下限通配符。
     */
    class Food {
    }

    class Fruit extends Food {
    }

    class Apple extends Fruit {
    }

    class Banana extends Fruit {
    }

    // 容器类
    class Plate<T extends Fruit> {
        private T item;

        public Plate(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }
    }


    /**
     * 『  泛型容器只能取，不能存。』
     */
    public void testUpperBoundedBoundedGeneric() {
        Plate<? extends Fruit> p = new Plate<>(new Apple());

        // 不能存入任何元素
        //  p.setItem(new Apple()); // TODO error

        // 读出来的元素需要是 Fruit或者Fruit的基类
        Fruit fruit = p.getItem();
        Food food = p.getItem();
    }







    /*======================================================================分界============================================================*/


    //===============================//
    //
    //             枚   举
    //
    //===============================//
    /**
     * 相信不少java开发者写过状态变更的业务，比如订单流程、请假流程等等。一般会搞一个状态标识来标识生命周期的某个阶段。很多人会写这种逻辑：
     */
    private void demos(String status) {
        if ("0".equals(status)) {
            //
        }
        if ("1".equals(status)) {
            //
        }

        if ("2".equals(status)) {
            //
        }

    }

    /**
     * 以上代码优化。
     * <p>
     * 状态机 ：表示状态 有限，可以枚举的。可以在这些状态中转移和动作行为的数学模型。
     * 定义了一套状态变更的流程。
     * 包括以下几个基本概念 ：
     * ===================
     * 1，状态
     * 2，事件
     * 3，行为
     * 4，变更
     * ===================
     * 枚举介绍：
     * --枚举是1.5开始引入的新特性
     * --设计成了单利模式，外部不能实例化，其实例化是在加载到JVM时完成的。
     * --枚举不可以实现类，但是可以实现接口。
     */


    public class DemosClass {
    }

    public interface DemosInterface {
        void getDemos();
    }




    public enum DemosEnum {
        DISPASS1 {
            public DemosEnum getDISPASS() {
                return DISPASS1;
            }

            public DemosEnum getNext() {
                return DISPASS2;
            }
        },

        DISPASS2 {
            public DemosEnum getDISPASS2() {
                return DISPASS2;
            }

            public DemosEnum getNext() {
                return null;
            }
        };
    }

    /***
     * 使用枚举定义 流程：
     * */
    public  void destEnum(String[] args) {
        //  DemosEnum.DISPASS1



    }


}
