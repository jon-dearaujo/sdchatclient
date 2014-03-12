package client;

import javax.swing.JTextArea;

public class MessageManager {
	private static MessageManager instance;
	
	private JTextArea messageOutput;
	private MessageManager()
	{}
	
	public void configure(JTextArea area)
	{
		this.messageOutput = area;
	}
	public static MessageManager getInstance()
	{
		if(instance == null)
		{
			instance = new MessageManager();
		}
		return instance;
	}
	
	public void writeMessage(String message)
	{
		this.messageOutput.append(message.concat("\n"));
	}
}
