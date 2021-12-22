package event;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
public class ArduinoSerialUsingEvent {
	public static void main(String[] args) {		
		try {
			CommPortIdentifier comPortIdentifier = CommPortIdentifier.getPortIdentifier("COM8");
			if(comPortIdentifier.isCurrentlyOwned()) {
				System.out.println("����� �� �����ϴ�");
			}else {
				System.out.println("��밡��");							
				CommPort commPort = comPortIdentifier.open("basic_serial", 3000);	
				System.out.println(commPort);				
				if(commPort instanceof SerialPort) {
					SerialPort serialPort = (SerialPort)commPort;
					serialPort.setSerialPortParams(
							9600, 
							SerialPort.DATABITS_8, 												 
							SerialPort.STOPBITS_1,
							SerialPort.PARITY_NONE);
					//�ø�������� ���� ������ ����� �ϱ� ���ؼ� ��Ʈ�� ��ü�� ���
					InputStream in = serialPort.getInputStream();
					OutputStream out = serialPort.getOutputStream();					
					
					//eventó���� ���� �Ƶ��̳뿡�� �������� �����͸� �б�
					serialPort.addEventListener(new SerialListener(in));
					serialPort.notifyOnDataAvailable(true);
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
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		}		 
	}
}