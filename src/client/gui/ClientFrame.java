package client.gui;

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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setVisible(true);
	}
}
