package android.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SenderThread extends Thread{
	Socket client;
	BufferedReader clientIn;//Ŭ���̾�Ʈ�� ������ �޽����� �д� ��Ʈ��
	PrintWriter clientOut;//Ŭ���̾�Ʈ���� �޽����� ������ ��Ʈ��
	InputStream serialIn;
	//�����尡 ������ ��, Ŭ���̾�Ʈ��ü�� �Ƶ��̳� �ø�����Ʈ�� ����� �� �ִ� ��Ʈ��
	//��ü�� �޾� ó��
	public SenderThread(Socket client, InputStream serialIn) {
		super();
		this.client = client;
		this.serialIn = serialIn;
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
		byte[] buffer = new byte[1024];
		int len = -1;
		try {
			while((len = serialIn.read(buffer))>-1) {
				String mydata = new String(buffer,0,len);
				System.out.print(mydata);
				if(mydata.trim().length()>0) {
					clientOut.println(mydata);
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
