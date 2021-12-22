package event;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import android.control.SerialArduinoLEDControl;

public class LEDControlClient implements MqttCallback{
	private MqttClient mqttclient;
	private MqttConnectOptions mqttOptions;
	//�޽����� ���۵Ǿ� messageArrived�޼ҵ尡 ȣ��Ǹ� �����͸� �Ƶ��̳�� ����
	SerialArduinoLEDControl serialObj;
	OutputStream serialOut;
	public LEDControlClient() {
		//MQTT��ü�� ������ �� �ø��� ����� ���� �غ�
		serialObj = new SerialArduinoLEDControl();
		serialObj.connect("COM8");
		serialOut = serialObj.getOutput();
	}
	public LEDControlClient init(String server, String clientId) {
		//mqttŬ���̾�Ʈ�� �����ϱ� ���� �ʿ��� ���� ����
		mqttOptions = new MqttConnectOptions();
		mqttOptions.setCleanSession(true);
		mqttOptions.setKeepAliveInterval(30);
		try {
			//broker�� subscribe�ϱ� ���� Ŭ���̾�Ʈ��ü�� ����
			mqttclient = new MqttClient(server, clientId);
			//Ŭ���̾�Ʈ��ü�� �ݹ��� ���� - subscribe�ϸ鼭 ������ ������ �޼ҵ尡 ȣ��� �� �ִ�.
			mqttclient.setCallback(this);
			//mqttclient�� broker�� ������ �� �ֵ��� ����
			mqttclient.connect(mqttOptions);
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return this;
	}
	//������û�ϱ�
	public boolean subscribe(String topic) {
		try {
			if(topic!=null) {
				mqttclient.subscribe(topic, 0);//topic, Qos�� �޽����� �����ϰ� �����ϴ� ���(ǰ��)
			}
		}catch(MqttException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		LEDControlClient client = new LEDControlClient();	
		//MqttClient��ü�� broker�� ����ǰ� ������û
		client.init("tcp://192.168.0.7:1883", "myid").subscribe("led");
	}
	//Ŀ�ؼ��� ����Ǹ� ȣ�� - ��ſ����� ������ ������ ��� ȣ��
	@Override
	public void connectionLost(Throwable arg0) {
		
	}
	//�޽��� ����� �Ϸ�Ǹ� ȣ��
	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		
	}
	//�޽����� �����ϸ� ȣ��Ǵ� �޼ҵ� - topic(broker������û�� topic��), MqttMessage�� �޽���
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("===�޽�������===");
		System.out.println(message+","+"topic:"+topic+",id:"+message.getId()+",Payload:"+new String(message.getPayload()));
		String msg = new String(message.getPayload());
		new Thread(new Runnable() {	
			@Override
			public void run() {
				try {
					if(msg.equals("led_on")) {					
						serialOut.write('o');
					}else {
						serialOut.write('f');
					}
				}catch (IOException e) {
						e.printStackTrace();
					}
			}
		}).start();
	}
}
