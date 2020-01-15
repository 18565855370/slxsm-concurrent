package com.slxsm.thread;

/**
 * object.wait and object.notify/notifyAll
 * wait和notify/all是object独有的方法，用来配合多线程执行程序的
 * wait和notify/all必须配合synchronized，获取监听锁才可以
 * 在调用object.wait获取监听所，在释放，其他notify获取到监听锁，并且执行完成方法后释放，wait可以继续执行
 * object维护一个等待队列，调用wait后进入队列，notify会随机获取一个执行（非公平），notifyAll会释放所有的线程
 * *** objectd.wait会释放对象的锁，Thread.sleep(...)不会释放对象的锁
 * @author slxsm
 * @date 2020/1/15
 */
public class Demo2 {

    static Object object = new Object();

    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis() + ": T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ": T1 wait for object!");
                    object.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ": T1 end!");
            }
        }
    }

    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis() + ": T2 start, notify one thread!");
                object.notify();
                System.out.println(System.currentTimeMillis() + ": T2 end!");
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new T1().start();
        new T2().start();
    }
}
