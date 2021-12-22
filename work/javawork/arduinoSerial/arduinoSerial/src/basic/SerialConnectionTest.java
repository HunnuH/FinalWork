package basic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.ParallelPort;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class SerialConnectionTest {
	public void connect(String portName) {
		//COM��Ʈ�� ���� �����ϰ� ��밡���� �������� Ȯ��		
		try {
			CommPortIdentifier comPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
			if(comPortIdentifier.isCurrentlyOwned()) {
				System.out.println("����� �� �����ϴ�");
			}else {
				System.out.println("��밡��");
				//port�� ��밡���ϸ� ��Ʈ�� ���� ��Ʈ��ü�� ��������
				//=> ��Ʈ�� ���� ����� �� �ִ� input/output��ü�� ������ �� �ִ�.
				//=> �Ű�����1 : ��Ʈ�� ���� ����ϴ� ���α׷��� �̸�
				//=> �Ű�����2 : ��Ʈ�� ���� ����ϱ� ���ؼ� ��ٸ��� �ð�(mills)				
				CommPort commPort = comPortIdentifier.open("basic_serial", 3000);	
				System.out.println(commPort);
				//comm��Ʈ�� ������ �� ����
				//=> Serial ��Ʈ���� Parallel��Ʈ���� Ȯ��
				//SerialPort, ParallelPort
				if(commPort instanceof SerialPort) {
					SerialPort serialPort = (SerialPort)commPort;
					//SerialPort�� ���� ����
					//=> ��żӵ�, �����ͱ���, stop bit, parity bit�� ����
					serialPort.setSerialPortParams(
							9600, //Serial port��żӵ�
							SerialPort.DATABITS_8, //�����ͱ��� 
												   //�� ���� 8bit�� �����Ͱ� ����
							SerialPort.STOPBITS_1,//stop bit(���� ǥ��)
							SerialPort.PARITY_NONE);//�ø�����ſ��� �ۼ��ŵǴ� �������� ������ �����ϱ� ���� ���
					//�ø�����Ʈ�� ����� �� �ֵ��� input/output��Ʈ����ü ���ϱ�
					InputStream in = serialPort.getInputStream();
					OutputStream out = serialPort.getOutputStream();
					//�ø�������� �ϱ� ���ؼ� �����带 start
					//=> ������ ���ο��� �ø�����Ʈ�� ����� �� �ֵ��� ��Ʈ����ü�� 
					//�Ű������� ����
					new Thread(new SerialReaderThread(in)).start();
					new Thread(new SerialWriterThread(out)).start();
				}
			}
		} catch (NoSuchPortException e) {
			e.printStackTrace();
		} catch (PortInUseException e) {
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		 
	}
	public static void main(String[] args) {
		new SerialConnectionTest().connect("COM8");
	}
}