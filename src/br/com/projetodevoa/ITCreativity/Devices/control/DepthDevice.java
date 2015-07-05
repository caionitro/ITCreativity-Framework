package br.com.projetodevoa.ITCreativity.Devices.control;
import org.openni.Device;
import org.openni.OpenNI;
import org.openni.PixelFormat;
import org.openni.SensorType;
import org.openni.VideoMode;
import org.openni.VideoStream;

import br.com.projetodevoa.ITCreativity.Devices.DeviceResolutions.Depth.DepthDeviceResolution;
import br.com.projetodevoa.ITCreativity.Devices.view.Devices;

/**
 * <h1>Depth Device </h1>
 * Classe respons�vel pela estrutura do dispositivo com sensor de movimentos (DEPTH).
 * Esta classe possui suporte aos dispositivos: Microsoft Kinect [todos] e Asus Xtion [todos].
 * Asus Xtion somente suportar� o modo 'DEPTH', para usar em outros modos, ser� necess�rio a vers�o Xtion Pro ou Xtion ProLive.
 *  
 * @author Rodrigo Junior Utiyama
 * @version 1.0
 * @since 2014-11-30
 */

public class DepthDevice extends Devices {

	private VideoStream videoStream;
	private Device dispositivo;
	private static DepthDeviceResolution resolution;
	
	
	/**
	 * Construtor padr�o. N�o h� par�metros, portanto, o dispositivo iniciar� com resolu��o
	 * '640x480' no modo "DEPTH"
	 */
	public DepthDevice(){
		connect(DepthDeviceResolution.RES640_480, SensorType.DEPTH);
	}
	
	/**
	 * Construtor que instanciar� o dispositivo com os valores passados.
	 * 
	 * @param deviceResolution Resolu��o WIDTH x HEIGHT do stream
	 * @param sensorType 	   Tipo de imagem do sensor (IR [Kinect, Xtion Pro e ProLive], RGB[Kinect, Xtion Pro e Xtion ProLive] ou DEPTH [Todos os dispotivos])
	 */
	public DepthDevice(DepthDeviceResolution deviceResolution, SensorType sensorType){
		connect(deviceResolution, sensorType);
	}
	
	public DepthDeviceResolution getDeviceResolution(){
		return resolution;
	}
	/**
	 * M�todo privado que poder� ser chamado por ambos os construtores.
	 * 
	 * @param resolution Tipo da resolu��o
 	 * @param sensorType Tipo do sensor (IR, RGB ou DEPTH)
	 */
	private void connect(DepthDeviceResolution resolution, SensorType sensorType) {
		this.resolution = resolution;
		
		//A classe OpenNI � respons�vel por inicializar todas as configura��es do frameork
		//assim, � inicializado e carregado todo o framework
		OpenNI.initialize();
		
		//A classe Device � respons�vel por realizar a conex�o entre o dispositivo e a aplica��o
		//O m�todo open() abre a "conex�o" com o dispositivo f�sico, o m�todo open() pode conter uma URL
		//que permite indicar um arquivo .ONI (simula��o) ou a URI do dispotivo f�sico
		dispositivo = Device.open();
		instanceVideoStream(sensorType, resolution);
	}
	
	/**
	 * M�todo que ficar� respons�vel por iniciar o v�deo.
	 * Os par�metros ser�o os mesmos passados pelos construtores.
	 * 
	 * @param resolution Tipo da resolu��o
 	 * @param sensorType Tipo do sensor (IR, RGB ou DEPTH)
	 */
	private void instanceVideoStream(SensorType sensorType, DepthDeviceResolution resolution){
		//A classe SensorType.Tipo indica qual o tipo de imagem ser� captada, ou seja, DEPTH(profundidade)
		//ou COLOR (RGB comum). Ele retorna um valor inteiro constante:
		SensorType tipoDoSensor = sensorType;
		
		int sensorNativeType = 0;
		
		if(tipoDoSensor.equals(SensorType.COLOR) || tipoDoSensor.equals(SensorType.IR)){
			sensorNativeType = PixelFormat.RGB888.toNative();
		}else{
			sensorNativeType = PixelFormat.DEPTH_1_MM.toNative();
		} 
		//Aqui ser� setada uma nova videoStream, passando o dispositivo e o tipo de sensor:
		videoStream = VideoStream.create(dispositivo, tipoDoSensor);
		
		VideoMode modoVideo = new VideoMode(resolution.getX(), resolution.getY(), 30, sensorNativeType);
		videoStream.setVideoMode(modoVideo);
		videoStream.start();
		
//		depthController = new InteractionMonitorDepth();
//		depthController.setStream(videoStream);
//		
//		HandTracker handTracker = HandTracker.create();
//		handTracker.startGestureDetection(GestureType.HAND_RAISE);
//
//		depthController.setImage("src/images/dotRed.png");
//		depthController.setHandTracker(handTracker);
		
		
	}
	
	/**
	 * Indica o tipo de sensor utilizado e a sua resolu��o.
	 * @param type Tipo do sensor utilizado (IR, COLOR ou DEPTH), essas s�o
	 * constantes do Enum SensorType
	 * @param res DepthDeviceResolution � do tipo Enum, indicando 
	 * a resolu��o do dispositivo.
	 * {@link DepthDeviceResolution} 
	 */
	public void setSensorType(SensorType type, DepthDeviceResolution res){
		disconnect();
		connect(res, type);
	}
	
	/**
	 * M�todo respons�vel por finalizar o stream. Observa��o:  o dispositivo n�o ser� desconectado
	 * da porta, mas apenas o processamento de dados da c�mera.
	 */
	public void disconnect() {
		if(videoStream instanceof VideoStream){
			videoStream.stop();
			videoStream.destroy();
			videoStream = null;
		}else{
			return;
		}
	}

	/**
	 * M�todo que ser� respons�vel por retornar informa��es do dispositivo.
	 * @return Informa��es gerais do dispositivo
	 */
	public String getDeviceInfo() {
		// TODO Auto-generated method stub
		return  "Name: " + dispositivo.getDeviceInfo().getName() + 
				"\nURI: " + dispositivo.getDeviceInfo().getUri() +
				"\nVendor: " + dispositivo.getDeviceInfo().getVendor() +
				"\nUSB Product ID: " + dispositivo.getDeviceInfo().getUsbProductId() + 
				"\nUSB Vendor ID: "  + dispositivo.getDeviceInfo().getVendor(); 
	}

	/**
	 * M�todo 'get' que retorna um objeto DepthDevice.
	 * @return Objeto do tipo Devices
	 */
	public Device getDevice() {
		return dispositivo;
	}
	
	
	public VideoStream getVideoStream(){
		return videoStream;
	}
	
	public void setVideoStream(VideoStream videoStream){
		this.videoStream = videoStream;
	}
}
