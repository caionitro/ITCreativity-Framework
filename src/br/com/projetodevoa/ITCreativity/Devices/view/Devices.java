package br.com.projetodevoa.ITCreativity.Devices.view;


/**
 * <h1> Webcam Device </h1>
 * <p>
 * A Classe Devices � uma classe abstrata respons�vel pela modelo de um dispositivo. � a classe
 * mais espec�fica dos dispositivos.
 * </p>
 * 
 * @author Rodrigo Junior Utiyama
 * @version 1.0
 * @since 2014-11-30
 */
public abstract class Devices {
 
 	
//	public abstract void disconnect();
	
//	public abstract void start();
	
//	public abstract void stop();

	/**
	 * Retorna as informa��es sobre o dispositivo, como resolu��o, URI entre outros.
	 * @return string informa��o do dispositivo atual.
	 */
	public abstract String getDeviceInfo();
 
//	public abstract Devices getDevice();
	
//	public abstract InteractionMonitor getMonitor();

}
