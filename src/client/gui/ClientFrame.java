package client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import client.Client;
import client.MessageManager;
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
			clientConnect();
		}catch(ConnectionFailException expt)
		{
			messagesOutputArea.append(expt.getMessage());
			sendMessage.setEnabled(false);
			messagesInputArea.setEnabled(false);
		}
		MessageManager.getInstance().configure(messagesOutputArea);
		new Thread(_client).start();
		
		this.addWindowListener(new onCloseWindowAdapter(_client));
		setVisible(true);
	}

	private void clientConnect(){
		_client.connect();
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
		this.messagesInputArea.addKeyListener(new onEnterClick(_client));
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
		_client.sendMessage(_text.getText().concat("\n"));
		_text.setText("");
	}
}

class onCloseWindowAdapter extends WindowAdapter
{
	private Client _client;
	public onCloseWindowAdapter(Client client)
	{
		_client = client;
	}
	@Override
	public void windowClosing(WindowEvent e) {
		_client.close();
		((JFrame)e.getSource()).dispose();
	}
}

class onEnterClick implements KeyListener
{
	private Client _client;
	public onEnterClick(Client client)
	{
		_client = client;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		JTextArea area = ((JTextArea)e.getSource());
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if(e.isShiftDown())
			{
				area.append("\n");
			}else
			{
				_client.sendMessage(area.getText().concat("\n"));
				area.setText("");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
