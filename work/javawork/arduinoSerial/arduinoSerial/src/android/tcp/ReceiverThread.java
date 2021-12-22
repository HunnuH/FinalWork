package android.tcp;

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
	OutputStream serialOut;
	//�����尡 ������ ��, Ŭ���̾�Ʈ��ü�� �Ƶ��̳� �ø�����Ʈ�� ����� �� �ִ� ��Ʈ��
	//��ü�� �޾� ó��
	public ReceiverThread(Socket client, OutputStream serialOut) {
		super();
		this.client = client;
		this.serialOut = serialOut;
		//�ʱ�ȭ
		//Ŭ���̾�Ʈ�� �������� �޽����� �б� ���� ��Ʈ������
		try {
			clientIn = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			//Ŭ���̾�Ʈ���� �޽�����
			clientOut = new PrintWriter(this.client.getOutputStream(), true);
			//�Ƶ��̳�� �ø�������� �ϱ� ���� ��ü
			
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
