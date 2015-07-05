package br.com.projetodevoa.ITCreativity.ControllerMonitor.Model;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import br.com.projetodevoa.ITCreativity.ControllerMonitor.Controllers.ControllerWebCam;
import br.com.projetodevoa.ITCreativity.ControllerMonitor.Controllers.ControllerDepth;
import br.com.projetodevoa.ITCreativity.ControllerMonitor.State.InteractionMonitorState;
import br.com.projetodevoa.ITCreativity.ControllerMonitor.State.MonitorOptions;
import br.com.projetodevoa.ITCreativity.ControllerMonitor.State.States.CalibrateState;
import br.com.projetodevoa.ITCreativity.ControllerMonitor.State.States.PauseDeviceState;
import br.com.projetodevoa.ITCreativity.ControllerMonitor.State.States.PauseMonitoringState;
import br.com.projetodevoa.ITCreativity.ControllerMonitor.State.States.StartDeviceState;
import br.com.projetodevoa.ITCreativity.ControllerMonitor.State.States.StartMonitoringState;
import br.com.projetodevoa.ITCreativity.Devices.control.DepthDevice;
import br.com.projetodevoa.ITCreativity.Devices.view.Devices;
	
/**
 * <h1> Interaction Monitor </h1>
 * <p>
 * A Classe InteractionMonitor � respons�vel pelo controle todas as
 * �reas de intera��o. Ou seja, cada InteractionRegion � monitorada e controlada
 * pela InteractionMonitor.  
 * </p>
 * 
 * @author Rodrigo Junior Utiyama
 * @version 1.0
 * @since 2014-11-28
 * 
 */
public abstract class InteractionMonitor extends JPanel{

	/** Todas as �reas de intera��es */
	private List<InteractionRegion> allInteractionRegion;
	
	/** O dispositivo instanciado (DEPTH ou WEBCAM). */
	protected Devices device;
	
	/** O estado atual - calibrando, monitorando, etc. */
	protected InteractionMonitorState state;

	public InteractionMonitor(){
		allInteractionRegion = Collections.synchronizedList(new ArrayList<InteractionRegion>());
	}
	
	/**
	 * Cria o comportamento requisitado. 
	 *
	 * @param option Estado do monitoramento. 
	 * @return true, se o comportamento foi aceito.
	 */
	public boolean doAction(MonitorOptions option) {

		switch (option.getOption()) {
			case 1: // START_MONITORING
				state = new StartMonitoringState();
				break;
	
			case 2: // PAUSE_MONITORING
				state = new PauseMonitoringState();
				break;
	
			case 3: // STOP_DEVICE_CAPTURE
				state = new PauseDeviceState();
				break;
	
			case 4: // START_DEVICE_CAPTURE
				state = new StartDeviceState();
				break;
	
			case 5: // CALIBRATE
				state = new CalibrateState();
				break;
	
			default:
				state = new StartMonitoringState();
		}
		return state.stateOperation(this);
	}
	
	/**
	 * Retorna o estado atual da captura.
	 *
	 * @return state - O Estado atual (START_MONITORING,PAUSE_MONITORING,STOP_DEVICE_CAPTURE
	 *  START_DEVICE_CAPTURE ou CALIBRATE).
	 */
	public  InteractionMonitorState getState(){
		return this.state;
	}
	
	/**
	 * M�todo abstrato que retorna a captura de v�deo dos dispositivos em 
	 * um objeto do tipo Component
	 *
	 * @return the component
	 */
	public abstract Component getComponent();
	
	/**
	 * Cria um dispositivo com o tipo de dipositivo desejado (WebCam ou Depth)
	 *
	 * @param device Devices
	 * @return new InteractionMonitor 
	 */
	public static InteractionMonitor makeDevice(Devices device){
		if(device == null) 
			return null;
		
		if(device instanceof DepthDevice) 
			return new ControllerDepth(device);

		return new ControllerWebCam(device);
	}

	/**
	 * Retorna uma �rea de intera��o na posi��o index.
	 * @param interactionRegion InteractionRegion
	 * @param device Devices
	 * @throws IllegalArgumentException se os par�metros forem nulos
	 */
	public void addInteractionRegion(InteractionRegion interactionRegion, Devices device) throws IllegalArgumentException {
		if((interactionRegion == null) || (device == null))
			throw new IllegalArgumentException("Par�metros inv�lidos \"nulos\"");
		
		//Passa a refer�ncia do objeto do tipo Device para o objeto da classe abstrata INTERACTION REGION
		interactionRegion.setDevice(device);
		 
		if(!(allInteractionRegion.contains(interactionRegion))){
			allInteractionRegion.add(interactionRegion);
		} 	
	}
	
	/**
	 * Remove uma �rea de intera��o
	 *
	 * @param interactionRegion uma �nica �rea de intera��o para remover
	 * @throws IllegalArgumentException caso algum dos par�metros forem nulos
	 */
	public void removeInteractionRegion(InteractionRegion interactionRegion) throws IllegalArgumentException {
		if((interactionRegion == null))
			throw new IllegalAccessError("Par�metros inv�lidos \"nulos\"");
		 
		if((allInteractionRegion.contains(interactionRegion))){
			allInteractionRegion.remove(interactionRegion);
		} 	
	}
	
	
	/**
	 * Substitui todas as areas de intera��es.
	 *
	 * @param interactionRegions -  Uma lista de �reas de intera��es.
	 * @exception IllegalArgumentException caso o par�metro seja nulo
	 */
	public void replaceInteractionRegions(List<InteractionRegion> interactionRegions) throws IllegalArgumentException{
		
		if(interactionRegions == null) 
			throw new IllegalArgumentException();
		
		allInteractionRegion = interactionRegions;
	}

	/**
	 * Retorna uma �rea de intera��o na posi��o index.
	 * @param index - Posi��o da �rea de intera��o em uma lista.
	 * @return interactionRegion InteractionRegion
	 * 
	 */
	public InteractionRegion getInteractionRegion(int index){
		if(allInteractionRegion.size() < 0){
			throw  new IndexOutOfBoundsException("N�o existem �reas de intera��es cadastradas.");
		}	
		return allInteractionRegion.get(index);
	}
	
	/**
	 * Retorna uma lista de todas as �reas de intera��es cadastradas
	 *
	 * @return allInteractionRegion - Uma lista com todas as �reas de intera��es.
	 */
	public List<InteractionRegion> getInteractionRegions(){
		return allInteractionRegion;
	}
}
