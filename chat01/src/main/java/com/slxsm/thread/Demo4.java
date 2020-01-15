package com.slxsm.thread;

/**
 * join :等待其他线程执行完成在处理之后的逻辑
 * @author slxsm
 * @date 2020/1/15
 */
public class Demo4 {

    static int num = 0;

    public static class T1 extends Thread {
        public T1(String name){
            super(name);
        }

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ",start -> " + this.getName());
            for (int i = 0; i < 10; i++){
                num++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis() + ",end -> " + this.getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1("thread-1");
        t1.start();
        t1.join();
        System.out.println(System.currentTimeMillis() + ",num -> " + num);
    }
}
