package br.com.projetodevoa.ITCreativity.Events.model;

import br.com.projetodevoa.ITCreativity.ControllerMonitor.Model.InteractionMonitor;

/**
 * <h1> Event Listener </h1>
 * <p>
 * EventListener � uma interface respons�vel pela assinatura do m�todo respons�vel
 * por disparar um determinado evento. Ela � um listener que ser� chamado quando
 * o observador {@link InteractionMonitor} disparar um evento.
 * </p>
 * Exemplo de notifica��o:
 * 	<pre>	
 * 		public void fireEvent(Events event){
 * 			if(event.detect()){
 * 				System.out.println("Evento disparado");
 * 			}
 * 		}
 * </pre>
 * 
 * @author Rodrigo Junior Utiyama
 * @version 1.0
 * @since 2014-11-30
 */
public interface EventListener {

	/**
	 * 
	 * @param event Evento que ser� disparado quando notoficado.
	 * 
	 */
	public abstract void fireEvent(Events event);

}
