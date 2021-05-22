package br.com.srcsoftware.manager.utilidades;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * 
 *
 * 
 * @author Diego Pêgo <diegodamascenopego@hotmail.com.br>
 * @since 10 de ago de 2018 22:14:55
 * @version 1.0
 */
public class Messages{

	public static ActionMessages createMessagesErrors( String key ) {
		ActionMessages errors = new ActionMessages();
		errors.add( Globals.ERROR_KEY, Messages.createMessage( key ) );
		return errors;
	}

	public static ActionMessages createMessages( String key ) {
		ActionMessages iMessages = new ActionMessages();
		iMessages.add( Globals.MESSAGE_KEY, Messages.createMessage( key ) );
		return iMessages;
	}

	public static ActionMessages createMessagesErrors( String key, String... values ) {
		ActionMessages iMessages = new ActionMessages();
		iMessages.add( Globals.ERROR_KEY, Messages.createMessage( key, values ) );
		return iMessages;
	}

	public static ActionMessages createMessages( String key, String... values ) {
		ActionMessages iMessages = new ActionMessages();
		iMessages.add( Globals.MESSAGE_KEY, Messages.createMessage( key, values ) );
		return iMessages;
	}

	public static ActionMessage createMessage( String key ) {
		return new ActionMessage( key );
	}

	public static ActionMessage createMessage( String key, String... values ) {
		return new ActionMessage( key, values );
	}
}
