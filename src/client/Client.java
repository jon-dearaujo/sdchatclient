package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import client.exceptions.ConnectionFailException;

public class Client implements Runnable
{
	private static final int SERVER_PORT = 2031;
	private static final String SERVER_ADDRESS = "192.168.101.144";
	
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	
	public void connect()
	{
		try
		{
			this.socket = new Socket(Client.SERVER_ADDRESS, Client.SERVER_PORT);
			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e)
		{
			throw new ConnectionFailException(e.getMessage());
		}
	}
	
	public void sendMessage(String message)
	{
		this.writer.println(message);
		this.writer.flush();
	}
	
	public BufferedReader getReader()
	{
		return this.reader;
	}
	
	@Override
	public void run()
	{
		if(this.socket != null && this.reader != null)
		{
			while(!this.socket.isClosed())
			{
				try {
					String message = this.reader.readLine();
					if(message != null && !message.isEmpty())
					{
						MessageManager.getInstance().writeMessage(message);
					}
				} catch (IOException e) {
					MessageManager.getInstance().writeMessage(e.getMessage());
					close();
				}
			}
			MessageManager.getInstance().writeMessage("disconnected!");
			close();
		}
	}

	public void close() {
		try {
			if(this.socket != null && !this.socket.isClosed())
			{
				this.socket.close();
			}
			if(this.reader != null)
			{
				this.reader.close();
			}
			if(this.writer!= null)
			{
				this.writer.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
