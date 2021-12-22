package android.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ReceiverThread extends Thread{
	Socket client;
	BufferedReader clientIn;//Ŭ���̾�Ʈ�� ������ �޽����� �д� ��Ʈ��
	PrintWriter clientOut;//Ŭ���̾�Ʈ���� �޽����� ������ ��Ʈ��
	SerialArduinoLEDControl serialObj;//�ø�������� ���� ��ü
	OutputStream serialOut;//�ø�����ſ��� �Ƶ��̳�� �����͸� �������� ���� ��Ʈ��
	public ReceiverThread(Socket client) {
		super();
		this.client = client;
		//�ʱ�ȭ
		//Ŭ���̾�Ʈ�� �������� �޽����� �б� ���� ��Ʈ������
		try {
			clientIn = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			//Ŭ���̾�Ʈ���� �޽�����
			clientOut = new PrintWriter(this.client.getOutputStream(), true);
			//�Ƶ��̳�� �ø�������� �ϱ� ���� ��ü
			serialObj = new SerialArduinoLEDControl();
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
				if(msg.equals("led1_on")) {
					serialOut.write('a');
				}
				if(msg.equals("led2_on")) {
					serialOut.write('b');
				}
				if(msg.equals("led3_on")) {
					serialOut.write('c');
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
