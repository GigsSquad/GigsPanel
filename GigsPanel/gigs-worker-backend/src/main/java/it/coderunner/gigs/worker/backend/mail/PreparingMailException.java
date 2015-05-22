package it.coderunner.gigs.worker.backend.mail;

/**
 * Wyjątek zgłaszany przez każdy provider wysyłania wiadomości e-mail, informujący o błędzie zanim zostanie wysłana wiadomość
 *
 */
public class PreparingMailException extends Exception{

	private static final long serialVersionUID = -4035804327222285476L;

	public PreparingMailException(String message){
		super(message);
	}
	
	public PreparingMailException(Throwable t){
		super(t);
	}
}
