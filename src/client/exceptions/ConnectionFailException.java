package client.exceptions;

public class ConnectionFailException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	private String message;
	
	public ConnectionFailException(String message)
	{
		this.message = message;
	}
	
	@Override
	public String getMessage()
	{
		return this.message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
}