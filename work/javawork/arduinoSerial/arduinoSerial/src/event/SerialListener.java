package event;

import java.io.IOException;
import java.io.InputStream;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

//�̺�Ʈ�� ���� ó���� �ϴ� �����ʰ�ü�� �ۼ�
public class SerialListener implements SerialPortEventListener{
	//�����Ͱ� �ԷµǸ� �̺�Ʈ�� �߻��ϹǷ� �ø�������� ���� �����͸� ���� �� �ֵ��� input��ü��
	//�����ʰ� ���� �־�� �Ѵ�.
	private InputStream in;
	public SerialListener(InputStream in) {
		super();
		this.in = in;
	}
	//�̺�Ʈ�� �߻��ϸ� ȣ��Ǵ� �޼ҵ�
	@Override
	public void serialEvent(SerialPortEvent event) {
		/*switch(event.getEventType()) {
		 	case SerialPortEvent.BI:
		 	case SerialPortEvent.RI:
		}*/
		//���޵Ǵ� �����Ͱ� �ִ� ��� �߻��ϴ� �̺�ƮŸ��
		if(event.getEventType()==SerialPortEvent.DATA_AVAILABLE) {
			try {
				int data = in.available();//���޵Ǵ� ������ ������
				byte[] bytedata = new byte[data];//�� �����ŭ byte�迭�� �����
				in.read(bytedata, 0, data);//bytedata�迭�� ���� �����Ͱ� ����
				//����ϱ�
				System.out.print("���� ������:"+new String(bytedata));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
