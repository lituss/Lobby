package test;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JSplitPane;
import javax.swing.JTextField;

import comunicaComu.IPlayer;
import comunicaComu.SGame;
import comunicaComu.SGameSetimig;
import comunicaComu.SPlayer;
import comunicaComu.SRoom;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;


public class WinClient {

	private JFrame frame;
	private JTextField txtUser;
	private JTextField txtPass;
	private IPlayer LobbyPlayer ;
	private LobbyClient lobbyClient;
	private DefaultListModel listModelPlayers,listModelRooms,listModelGames;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinClient window = new WinClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public WinClient() throws IOException {
		lobbyClient = new LobbyClient(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 671, 761);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		listModelPlayers = new DefaultListModel();
		JList Players = new JList(listModelPlayers);
		Players.setName("Players");
		Players.setBounds(10, 28, 189, 200);
		panel.add(Players);
		
		listModelRooms = new DefaultListModel();
		JList Rooms = new JList(listModelRooms);
		Rooms.setBounds(232, 28, 204, 200);
		panel.add(Rooms);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 270, 277, 211);
		panel.add(textArea);
		
		listModelGames = new DefaultListModel();
		JList Games = new JList(listModelGames);
		Games.setBounds(378, 274, 217, 200);
		panel.add(Games);
		
		txtUser = new JTextField();
		txtUser.setText("User");
		txtUser.setBounds(54, 507, 86, 20);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setText("Pass");
		txtPass.setBounds(54, 538, 86, 20);
		panel.add(txtPass);
		txtPass.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lobbyClient.login(txtUser.getText(), txtPass.getText());
			}
		});
		btnLogin.setBounds(10, 585, 86, 23);
		panel.add(btnLogin);
		
		JButton btnJoin = new JButton("Join");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnJoin.setBounds(10, 619, 89, 23);
		panel.add(btnJoin);
		
		JButton btnToRoom = new JButton("To Room");
		btnToRoom.setBounds(10, 653, 89, 23);
		panel.add(btnToRoom);
		
		JButton btnToGame = new JButton("To Game");
		btnToGame.setBounds(10, 688, 89, 23);
		panel.add(btnToGame);
	}
	/*private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}*/
	
	public void addPlayer (SPlayer player){
		((DefaultListModel) listModelPlayers).addElement(player.nom);
	}
	public void delPlayer (String nom){
		((DefaultListModel) listModelPlayers).removeElement(nom);
		
	}
	public void addRoom (SRoom sRoom){
		((DefaultListModel) listModelRooms).addElement(sRoom.nom);
	}
	public void delRoom (String nom){
		((DefaultListModel) listModelRooms).removeElement(nom);
	}
	public void addGame(SGame sGame){
		((DefaultListModel) listModelGames).addElement(((SGameSetimig)sGame));
	}
	public void delGame(String nom){
		((DefaultListModel) listModelGames).removeElement(nom);
	}
	public void show (String text){
		JOptionPane.showMessageDialog(frame, text);
	}
}
