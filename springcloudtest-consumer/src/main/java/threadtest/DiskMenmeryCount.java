package threadtest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程统计磁盘大小
 * Created by Administrator on 2018/1/3.
 */
public class DiskMenmeryCount {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        DiskMemory diskMemory = new DiskMemory();

        int allSize = 0;

        ExecutorService service = Executors.newFixedThreadPool(6);
        for(int i = 0 ;i<4;i++){
            SubCountThread subCountThread = new SubCountThread(countDownLatch,diskMemory,i);
            Thread thread = new Thread(subCountThread);
            thread.setName("sub_thread_" + i);
            thread.start();
        }
        countDownLatch.await();

        System.out.println("total"+diskMemory.getTotalSize());

    }

}
