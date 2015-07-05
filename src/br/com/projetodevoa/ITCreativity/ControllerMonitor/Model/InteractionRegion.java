/*
 * 
 */
package br.com.projetodevoa.ITCreativity.ControllerMonitor.Model;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import br.com.projetodevoa.ITCreativity.Devices.control.DepthDevice;
import br.com.projetodevoa.ITCreativity.Devices.view.Devices;
import br.com.projetodevoa.ITCreativity.Events.model.EventListener;
import br.com.projetodevoa.ITCreativity.Events.model.Events;
/**
 * <h1> Interaction Region </h1>
 * <p>
 * A Classe InteractionRegion � respons�vel por cada �rea de intera��o. Portanto,
 * cada �rea desenhada em um componente ser� uma InteractionRegion. Esta classe
 * herda de Component.
 * </p>
 * 
 * @author Rodrigo Junior Utiyama
 * @version 1.0
 * @since 2014-11-30
 * 
 */
public abstract class InteractionRegion extends Component{

	/** Posi��o de clique na posi��o X da tela */
	protected int pivoX;

	/** Posi��o de clique na posi��o Y da tela */
	protected int pivoY;

	/** Profundidade de uma �rea */
	protected int pivoZ;
	
	/** Total de pixels mudados nesta �rea */
	protected int pixelTotalChange;
	
	/** Tempo m�nimo de cada clique */
	protected long minTime = 1500;
	
	/** Tempo que cada clique foi feito em milesegundos */
	protected long time;

	/** Tamanho m�nimo da �rea na posi��o X */
	private double minX;

	/** Tamanho m�ximo da �rea na posi��o X */
	private double maxX;

	/** Tamanho m�nimo da �rea na posi��o Y */
	private double minY;
	
	/** Tamanho m�ximo da �rea na posi��o Y */
	private double maxY;
	
	/** Cor padr�o da �rea */
	protected Color color = Color.RED;

	/** Atributo do tipo 'Devices' que receber� a refer�ncia do device instanciado 
	 * na classe InteractionMonitor.addInteractionRegion(..);
	 */
	private Devices device;
	
	/** Lista de EventListener(ouvidores) desta �rea */
	private List<EventListener> eventListenerList;
	
	/** Lista de eventos desta �rea */
	private List<Events> eventList;

	
	
	/**
	 * Retorna o tamanho m�nimo do desenho na posi��o X do componente. 
	 * @return  minX
	 * 
	*/
	public abstract double getMinX();
	
	/**
	 * Retorna o tamanho m�nimo do desenho na posi��o Y do componente. 
	 * @return  minY
	 * 
	*/
	public abstract double getMinY();
	
	/**
	 * Retorna o tamanho m�nimo do desenho na posi��o X do componente. 
	 * @return  maxX
	 * 
	*/
	public abstract double getMaxX();
	
	/**
	 * Retorna o tamanho m�nimo do desenho na posi��o X do componente. 
	 * @return  maxY
	 * 
	*/
	public abstract double getMaxY();
	
	/**
	 * Calcula o total de pixel desta �rea.
	 * @return getWidth()*getHeight()
	 */
	public int calculateArea(){
		return getWidth()*getHeight();
	}
	
	/**
	 * Adiciona a posi��o X de clique ao evento. Ou seja, se o evento for clicar em uma posi��o
	 * na tela, esta � a coordenada X
	 * @param pivoX  Coordenada X que ser� o clique
	 */
	public void addXClick(int pivoX) {
		this.pivoX = pivoX;
	}
 
	/**
	 * Adiciona a posi��o Y de clique ao evento. Ou seja, se o evento for clicar em uma posi��o
	 * na tela, esta � a coordenada Y
	 * @param pivoY  Coordenada Y que ser� o clique
	 */
	public void addYClick(int pivoY) {
		this.pivoY = pivoY;
	}
	
	/**
	 * Adiciona as posi��es de clique ao evento. Ou seja, se o evento for clicar em uma posi��o
	 * na tela, estas ser�o as coordenadas
	 * 
	 * @param pivoX  Coordenada X que ser� o clique
	 * @param pivoY  Coordenada Y que ser� o clique
	 * 
	 */
	public void addXYClick(int pivoX, int pivoY){
		this.pivoX = pivoX;
		this.pivoY = pivoY;
	}
	 
	/**
	 * Retorna a posi��o X do clique
	 * @return pivoX - Posi��o X do clique
	 */
	public int getXClick(){
		return this.pivoX;
	}
	
	/**
	 * Retorna a posi��o Y do clique
	 * @return pivoY - Posi��o Y do clique
	 */
	public int getYClick(){
		return this.pivoY;
	}
	
	/**
	 * Retorna a posi��o X em que a �rea est� desenhada
	 * @return x - Posi��o X da �rea no componente
	 */
	public abstract int getX();
	
	/**
	 * Retorna a posi��o Y em que a �rea est� desenhada
	 * @return y - Posi��o Y da �rea no componente
	 */
	public abstract int getY();
	
	
	/**
	 * Retorna a profundidade da �rea desenhada (somente para Shapes3D).
	 * Se a InteractionRegion n�o implementar Shapes3D, o retorno ser� 0.
	 * @return depth - Profundidade de uma �rea cadastrada. Caso for Shapes 2D, retorno ir� ser 0.
	 */
	public int getPivoZ(){
		if(device instanceof DepthDevice){
			return  pivoZ;
		}
		return 0;
	}
	
	/**
	 * Retorna a cor da borda do desenho.
	 * @return color
	 */
	public Color getShapeColor(){
		return color;
	}
	
	/**
	 * Especifica a cor da borda da �rea desenhada.
	 * @param color  Cor da borda da �rea.
	 */
	public void setShapeColor(Color color){
		this.color = color;
	}
 
	/**
	 * Retorna a quantidade de pixels alterado quando esta �rea for interagida.
	 * @return pixelTotalChange - Total de pixels alterado
	 */
	public int getPixelTotalChange() {
		return pixelTotalChange;
	}

	/**
	 * Retorna o tempo m�nimo para cada intera��o ocorrer.
	 * @return minTime
	 */
	
	public long getMinTime() {
		return minTime;
	}
	

	/**
	 * Especifica o tempo, em milisegundos, da intera��o realiza.
	 * @param time  Tempo atual da intera��o ocorrida.
	 */
	
	public void setTime(long time){
		this.time = time;
	}
	
	/**
	 * Retorna, em milisegundos, o tempo da �ltima intera��o.
	 * @return time - Retorna o tempo em milisegundos cada intera��o
	 */
	
	public long getTime(){
		return time;
	}
	
	/**
	 * Especifica o total de pixels mudados.
	 * @param pixelTotalChange  Total de pixels alterados.
	 */
	
	public void setPixelTotalChange(int pixelTotalChange) {
		this.pixelTotalChange = pixelTotalChange;
	}

	/**
	 *  Define o atributo device indicando qual o tipo de dispositivo (DEPTH ou WEBCAM) 
	 */
	protected void setDevice(Devices device){
		this.device = device;
	}
	
	/** 
	 * Retorna o dispositivo instanciado. 
	 * 
	 * */
	protected Devices getDevice(){
		return device;
	}

	
	/*
	 * M�todo que verifica se o atributo 'eventListenerList' (ArrayList) est� nulo ou vazio 
	 * */ 
	private boolean listsNotInitialized(){
		if((eventListenerList == null) || (eventListenerList.size() < 0) 
									   || eventList == null || eventList.size() < 0){
			return true;
		}
		return false;
	}

	/**
	 * Retorna todas as listas de eventos desta �rea.
	 * @return eventList - Lista de eventos desta �rea.
	 */
	public List getEvents(){ return eventList; }
	
	/**
	 * Adiciona um evento para esta �rea. Poder� ser cadastrado mais que um evento.
	 * @param eventlistener Tipo do Evento, � uma categoria para cada Events que ir� disparar os eventos.
	 * @param event		 Um evento a ser cadastrado. Os eventos poder�o ser desenvolvidos e extendidos.
	 * @exception NullPointerException - Caso algum dos par�metros forem nulos.
	 */
	public void addEventListener(EventListener eventlistener, Events event) {
		
		if((eventlistener == null) || event == null) 
			throw new NullPointerException("Par�metros nulos");
		
		
		if(listsNotInitialized()){
			eventListenerList = new ArrayList<EventListener>();
			eventList = new ArrayList<Events>();
		}
	
		try{
			eventList.add(event);
			eventListenerList.add(eventlistener);
		}catch(IndexOutOfBoundsException ex){
			System.out.println(ex);
		}catch(NullPointerException ex){
			System.out.println(ex);
		}
	}

	/**
	 * Remove um listener de um evento. 
	 * @param observer  EventListener a ser removido
	 */
	public void removeEventListener(EventListener observer) {
		if(!listsNotInitialized()){
			if(eventListenerList.contains(observer)){
				eventListenerList.remove(observer);
			}		
		}
	}

	/**
	 * Respons�vel por notificar todos os EventListener que esta �rea foi 'invadida'.
	 * Quando invadida, notifica todos os Listener dos eventos, que esta dispara seus eventos
	 * cadastrados.
	 */
	public void notifyAllEventListeners() {
		if(!listsNotInitialized()){
			for(EventListener eventListener : eventListenerList){	
				for(Events event : eventList){
					eventListener.fireEvent(event);
				}
			}
		}
	}
	
	/**
	 * Verifica se esta �rea est� na posi��o especificada.
	 * @param point - Objeto do tipo Point que ser� a localiza��o da �rea.
	 * @return true se esta �rea est� localizada na posi��o especificada pelo objeto Point, 
	 * ou n�o caso esta �rea n�o est� localizada na posi��o especificada pelo objeto Point.
	 */
	public abstract boolean containsShape(Point point);
	
	/**
	 * Verifica se esta �rea est� na posi��o especificada.
	 * @param x  posi��o X a ser verificada.
	 * @param y  posi��o Y a ser verificada.
	 * @return true se esta �rea est� localizada na posi��o especificada por X e Y, 
	 * ou n�o caso esta �rea n�o est� localizada na posi��o especificada por X e Y.
	 */
	public abstract boolean containsShape(int x, int y);
	
	/**
	 * Retorna uma descri��o da �rea
	 * @return retorna a descri��o desta �rea
	 */
	public abstract String  getShapeDescription();
	
	/**
	 * Indica a cor da borda desta �rea.
	 * @param color  Cor da �rea a ser alterada.
	 */
	public abstract void    setColor(Color color);
}
