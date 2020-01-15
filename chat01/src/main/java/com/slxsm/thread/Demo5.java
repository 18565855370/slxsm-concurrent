package com.slxsm.thread;

/**
 * volatile 保证了共享资源在多线程中可见性和有序性
 * 被volatile修饰的变量修改后，将修改后的值强制刷新到主内存，其他线程使用了该变量后，会强制重写到主内存中重新刷新到工作内存，完成线程的交互
 * @author slxsm
 * @date 2020/1/15
 */
public class Demo5 {

    public volatile static boolean flag = true;

    public static class T1 extends Thread {
        public T1(String name){
            super(name);
        }

        @Override
        public void run() {
            System.out.println(String.format("线程：%s in",this.getName()));
            while (flag){
                System.out.println("我不停止，咋地...");
            }
            System.out.println(String.format("线程：%s 停止了",this.getName()));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new T1("t1").start();
        //休眠了一秒
        Thread.sleep(1000);
        //将flag置为false，本机器无法实现死循环，但是确实是存在不安全的情况，需要修改为volatile
        flag = false;
    }
}
