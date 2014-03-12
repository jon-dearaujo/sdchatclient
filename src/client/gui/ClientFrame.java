package client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;
import client.Client;
import client.exceptions.ConnectionFailException;

public class ClientFrame extends JFrame
{
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 600;
	private JPanel panel;
	private JTextArea messagesOutputArea;
	private JScrollPane outputScroll;
	private JTextArea messagesInputArea;
	private JScrollPane inputScroll;
	private JButton sendMessage;
	private Client _client;
	
	public ClientFrame(Client client) 
	{
		super("JHChat");
		_client = client;
		basicConfiguration();
		configureComponentesLayout();
		this.pack();
		try
		{
			_client.connect();
		}catch(ConnectionFailException expt)
		{
			messagesOutputArea.append(expt.getMessage());
			sendMessage.setEnabled(false);
			messagesInputArea.setEnabled(false);
		}
		setVisible(true);
	}

	private void basicConfiguration() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
	}
	
	private void configureComponentesLayout()
	{
		this.panel = new JPanel(new MigLayout());
		this.messagesOutputArea = new JTextArea(10, 40);
		this.messagesOutputArea.setEditable(false);
		this.outputScroll = new JScrollPane(messagesOutputArea);
		panel.add(outputScroll, "wrap, push, grow");
		
		this.messagesInputArea = new JTextArea(5, 40);
		this.inputScroll = new JScrollPane(messagesInputArea);
		panel.add(inputScroll, "wrap, push, grow");
		
		sendMessage = new JButton("send message");
		sendMessage.addActionListener(new sendMessageActionListener(_client, messagesInputArea));
		panel.add(sendMessage, "center");
		this.add(panel);
	}
	
}

class sendMessageActionListener implements ActionListener
{
	private Client _client;
	private JTextArea _text;
	public sendMessageActionListener(Client client, JTextArea text) {
		_client = client;
		_text = text;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		_client.sendMessage(_text.getText().toString());
	}
	
}
