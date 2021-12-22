package final_latte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ReceiverThread extends Thread{
   Socket client;
   BufferedReader clientin;
   PrintWriter clientout;
   SendAndReceiveSerial serialobj;
   OutputStream serialout;
   
   public ReceiverThread(Socket client, OutputStream serialout) {
      super();
      this.client = client;
      this.serialout = serialout;
      try {
         clientin = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
         clientout = new PrintWriter(this.client.getOutputStream(),true);
         
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   @Override
   public void run() {
      super.run();
      while(true) {
         try {
        	 String msg = clientin.readLine();
            if(msg!=null) {
            	System.out.println("Ŭ���̾�Ʈ�� ���� �޼��� : "+msg);
            	if(msg.equals("X")) {
                    serialout.write('X');
                 }else if(msg.equals("Y")) {
                    serialout.write('Y');
                    System.out.println("�ø��� y");
                 }else if(msg.equals("Z")) {
                    serialout.write('Z');
                    System.out.println("�ø��� z");
                 }
            }
            
         } catch (IOException e) {
            e.printStackTrace();
         }
         
      }
   }
   
}

