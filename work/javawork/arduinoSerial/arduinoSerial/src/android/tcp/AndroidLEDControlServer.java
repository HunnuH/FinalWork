package android.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//�ȵ���̵�(Ŭ���̾�Ʈ)�� ��û�� ������ �Ƶ��̳� ��ġ�� ����ϱ� ���� �ø��� ��ü�� �����ϴ� ����
public class AndroidLEDControlServer {
	private ServerSocket server;
	SerialArduinoLEDControl serialObj;
	//������ü�� �����ǰ� ����� ��, �Ƶ��̳� ��Ʈ�� ���� ����� �غ� ��
	public AndroidLEDControlServer() {
		//�ʿ��ϸ� Ŭ���̾�Ʈ�� �����ϰ� accept�ϰ� �۾�
		//�Ƶ��̳�� �ø�������� �ϱ� ���� ��ü
		serialObj = new SerialArduinoLEDControl();
		serialObj.connect("COM8");
	}
	public void connect() {
		try {
			server = new ServerSocket(12345);
			//Ŭ���̾�Ʈ�� ������ ��ٸ��� ������
			Thread t1 = new Thread(new Runnable() {			
				@Override
				public void run() {
					while(true) {
						try {
							Socket client = server.accept();
							String ip = client.getInetAddress().getHostName();
							System.out.println(ip+"����� ����!");
							//�ø�������� ���� ��ü����
							//Ŭ���̾�Ʈ�� �޽����� �д� ������
							//�Ƶ��̳�� �ø�������� �� �� �ֵ��� �Է½�Ʈ��,
							//��½�Ʈ���� ������� �Ѱ���
							InputStream serialIn = serialObj.getInput();
							OutputStream serialOut = serialObj.getOutput();
							new ReceiverThread(client, serialOut).start();
							new SenderThread(client, serialIn).start();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}					
				}
			});
			t1.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new AndroidLEDControlServer().connect();
	}
}
