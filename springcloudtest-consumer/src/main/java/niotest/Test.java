package niotest;

import com.yly.consumer.entity.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/1/3.
 */
public class Test {
    public static String sss = new String("aaa");
    public static void method1() throws IOException {
       RandomAccessFile aFile =  new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt","rw");
        FileChannel fileChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);

        int bytesRead = fileChannel.read(buf);

        while (bytesRead != -1){
            System.out.println(bytesRead);
            buf.flip();
            while (buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.compact();
            bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
        }


    }

    public static void StringTest(){
        String s1 = new String("111");
        String s2 = "sss111";
        String s3 = "sss" + "111";
        String s4 = "sss" + s1;
        System.out.println(s2 == s3);
        System.out.println(s2 == s4);
        System.out.println(s2 == s4.intern());
    }

    public static void HashMapTest(){
        HashMap<String,String> map = new HashMap<String, String>();
        map.put(null,"aaa");
        map.put("aaa",null);

        System.out.print(map);
    }

    public static void main(String[] args) throws IOException {
//        method1();
        HashMapTest();

    }


}

