package com.slxsm.thread;



import java.util.concurrent.TimeUnit;

/**
 * 停止线程操作,
 *stop立即中断，废弃
 * interrupt -> 中断线程，将中断标志位设置为true，表示这个线程已经被中断
 * isInterrupt -> 判断线程是否被中断，检查interrupt中的标志位，true为中断，false为非中断
 * interrupted -> 判断线程是否被中断，并清除中断状态
 * 通过变量控制
 * @author slxsm
 * @date 2020/1/15
 */
public class Demo1 {

    /**
     * 这里stop后查看线程状态还是runnable，隔一秒才会看到TERMINATED状态，大概是停止需要时间吧
     * @param args
     * @throws InterruptedException
     */
    /*public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("start");
                boolean flag = true;
                while (flag){}
                System.out.println("end");
            }
        };
        thread.setName("thread1");
        thread.start();
        //当前线程休眠一秒
        TimeUnit.SECONDS.sleep(1);
        //关闭线程thread1,废弃方法
        thread.stop();
        //输出thread1的状态
        System.out.println(String.format("%s -> %s",thread.getName(),thread.getState()));
        //当前线程休眠一秒
        TimeUnit.SECONDS.sleep(1);
        //输出线程thread1的状态
        System.out.println(String.format("%s -> %s",thread.getName(),thread.getState()));
    }*/

    /**
     * interrupted将标识符改为true
     * isInterrupted判断表示为true的话，触发break，结束线程运行
     * @param args
     * @throws InterruptedException
     */
    /*public static void main(String[] args) throws InterruptedException {
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                System.out.println("start ->");
                while (true){
                    if (this.isInterrupted()){
                        System.out.println(String.format("%s要退出了...",this.getName()));
                        break;
                    }
                }
            }
        };
        thread2.setName("thread2");
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.interrupt();
        System.out.println(String.format("%s -> %s",thread2.getName(),thread2.getState()));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(String.format("%s -> %s",thread2.getName(),thread2.getState()));
    }*/

    /**
     * 通过变量来标识出线程，这个变量要设置成volatile，每个线程要可见，否则多线程情况下会出现乱的问题
     */
    /*static volatile boolean isStop = false;
    public static void main(String[] args) throws InterruptedException {
        Thread thread3 = new Thread(){
            @Override
            public void run() {
                System.out.println("start -> ");
                while (true){
                    if (isStop){
                        System.out.println(String.format("我要退出了哦!"));
                        break;
                    }
                }
                System.out.println("end -> ");
            }
        };
        thread3.setName("thread-3");
        thread3.start();
        TimeUnit.SECONDS.sleep(1);
        isStop = true;
        System.out.println(String.format("%s -> %s",thread3.getName(),thread3.getState()));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(String.format("%s -> %s",thread3.getName(),thread3.getState()));
    }*/

    /**
     * sleep的时候，调用interrupted，sleep会抛异常，并将中断标志位恢复到false
     * @param args
     * @throws InterruptedException
     */
    /*public static void main(String[] args) throws InterruptedException{
        Thread thread4 = new Thread(){
            @Override
            public void run() {
                System.out.println("start -> ");
                while (true){
                    //休眠一百秒
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("我要退出了...");
                    break;
                }
            }
        };
        thread4.setName("thread-4");
        thread4.start();
        TimeUnit.SECONDS.sleep(1);
        thread4.interrupt();
        System.out.println(String.format("%s -> %s",thread4.getName(),thread4.getState()));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(String.format("%s -> %s",thread4.getName(),thread4.getState()));
    }*/

    public static void main(String[] args) throws InterruptedException {
        Thread thread5 = new Thread(){
            @Override
            public void run() {
                while (true){
                    //休眠一百秒
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                        this.interrupt();
                    }
                    if (this.isInterrupted()){
                        System.out.println("我要退出了。。。");
                        break;
                    }
                }
            }
        };
        thread5.setName("thread-5");
        thread5.start();
        TimeUnit.SECONDS.sleep(1);
        thread5.interrupt();
        System.out.println(String.format("%s -> %s",thread5.getName(),thread5.getState()));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(String.format("%s -> %s",thread5.getName(),thread5.getState()));

    }
}
