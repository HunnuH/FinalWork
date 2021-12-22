package serial_rxtx_exam01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import android.control.SerialArduinoLEDControl;

public class ButtonReceiverThread extends Thread{
	Socket client;
	BufferedReader clientIn;
	PrintWriter clientOut;	
	ButtonSerailArduinoLEDControl serialObj;
	OutputStream serialOut;
	public ButtonReceiverThread(Socket client) {
		super();
		this.client = client;
		//�ʱ�ȭ
		//Ŭ���̾�Ʈ�� �������� �޽����� �б� ���� ��Ʈ������
		try {
			clientIn = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			//Ŭ���̾�Ʈ���� �޽�����
			clientOut = new PrintWriter(this.client.getOutputStream(), true);
			//�Ƶ��̳�� �ø�������� �ϱ� ���� ��ü
			serialObj = new ButtonSerailArduinoLEDControl();
			serialObj.connect("COM8");
			serialOut = serialObj.getOutput();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		//Ŭ���̾�Ʈ�� �޽����� �޾Ƽ� �Ƶ��̳�� ����
		while(true) {
			try {
				String msg = clientIn.readLine();
				System.out.println("Ŭ���̾�Ʈ�� ���� �޽���:"+msg);
				if(msg.equals("led_on")) {
					serialOut.write('o');
				}else {
					serialOut.write('f');
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
