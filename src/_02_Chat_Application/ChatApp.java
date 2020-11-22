package _02_Chat_Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame implements ActionListener{
		
	JTextField jtext = new JTextField();
	
	ChatServer server;
	ChatClient client;
	
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new ChatServer(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			jtext.addActionListener((e)->{
				String word = jtext.getText();
				System.out.println(word);

			});
			add(jtext);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new ChatClient(ipStr, port);
			jtext.addActionListener((e)->{
				String word = jtext.getText();
				client.sendClick(word);
			});
			add(jtext);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			client.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
