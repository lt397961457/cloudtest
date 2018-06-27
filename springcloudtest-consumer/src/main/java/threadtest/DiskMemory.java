package threadtest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/1/3.
 */
public class DiskMemory {
    private long totalSize;
    private static List<String> diskNames = new ArrayList<String>();
    static {
        diskNames.add("c:");
        diskNames.add("d:");
        diskNames.add("e:");
        diskNames.add("f:");
    }

    private void setSize(long size) {
        this.totalSize  += size;
    }

    public long getSize(int index){
        if(index > 3){
            return 0;
        }
        File file = new File(diskNames.get(index));
        long totalbit = file.getTotalSpace();
        long totalG = (long)Math.ceil(totalbit/1024d/1024.00d/1024d);
        setSize(totalG);
        return totalG;
    }

    public long getTotalSize(){
        return  totalSize;
    }
}
