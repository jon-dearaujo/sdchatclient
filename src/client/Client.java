package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import client.exceptions.ConnectionFailException;

public class Client
{
	private static final int SERVER_PORT = 2031;
	private static final String SERVER_ADDRESS = "";
	
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	
	public void connect()
	{
		try
		{
			this.socket = new Socket(Client.SERVER_ADDRESS, Client.SERVER_PORT);
			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.writer = new PrintWriter(socket.getOutputStream());
		} catch (IOException e)
		{
			throw new ConnectionFailException(e.getMessage());
		}
	}
	
	public void sendMessage(String message)
	{
		this.writer.write(message);
	}
	
	public BufferedReader getReader()
	{
		return this.reader;
	}
}
