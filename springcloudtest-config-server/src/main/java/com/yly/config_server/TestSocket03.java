package com.yly.config_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TestSocket03 {

	public static void main(String[] args)  {
		try{
		 Socket socket = new Socket("182.138.4.76", 8088);
		 System.out.println("连接成功");
		 

         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter write = new PrintWriter(socket.getOutputStream());
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         String readline;
         readline = br.readLine();
         while (!readline.equals("end")) {
             write.println(readline);
             write.flush();
             System.out.println("Client:" + readline);
             System.out.println("Server:" + in.readLine());
             readline = br.readLine();
         }
         write.close();
         in.close();
         socket.close();
     } catch (Exception e) {
         System.out.println("can not listen to:" + e);
     }
	}
}
