package exceptions;

public class BoardComponentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "BOARD COMPONENT NOT FOUND EXCEPTION";
	}

}
