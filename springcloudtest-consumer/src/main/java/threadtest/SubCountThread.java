package threadtest;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/1/3.
 */
public class SubCountThread implements Runnable {
    private long disksize;
    private CountDownLatch countDownLatch;
    private int index;

    DiskMemory diskMemory ;

    public SubCountThread(CountDownLatch countDownLatch, DiskMemory diskMemory,int idex) {
        this.countDownLatch = countDownLatch;
        this.diskMemory = diskMemory;
        this.index = idex;
    }

    @Override
    public void run() {
        int timer = new Random().nextInt(5);
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        disksize =diskMemory.getSize(index);
        System.out.println(Thread.currentThread().getName() +"--->" +disksize);
        countDownLatch.countDown();

    }
}
