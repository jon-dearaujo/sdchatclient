package client.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ClientFrame extends JFrame
{
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_INTERNAL_BORDER = 20;
	private JPanel panel;
	private JTextArea messagesOutputArea;
	private JTextArea messagesInputArea;
	private JButton sendMessage;
	
	public ClientFrame() 
	{
		basicConfiguration();
		layoutConfiguration();
		setVisible(true);
	}


	private void basicConfiguration()
	{
		setTitle("Chat Client");
		setSize(ClientFrame.FRAME_WIDTH, ClientFrame.FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	private void layoutConfiguration()
	{
		messagesOutputArea = new JTextArea(FRAME_WIDTH - FRAME_INTERNAL_BORDER, FRAME_HEIGHT - FRAME_INTERNAL_BORDER);
	}
}
