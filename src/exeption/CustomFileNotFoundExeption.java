package exeption;

public class CustomFileNotFoundExeption extends Exception {
	
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public CustomFileNotFoundExeption(String msg) {
	        super(msg);
	    }
	    public CustomFileNotFoundExeption(String msg, Throwable e) {
	        super(msg, e);
	    }



}
