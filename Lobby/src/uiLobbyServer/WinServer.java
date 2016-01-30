package uiLobbyServer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import comunicaComu.SGame;
import comunicaComu.SGameSetimig;
import comunicaComu.SPlayer;
import comunicaComu.SRoom;
import comunicaServer.LobbyServer;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import utils.Array;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class WinServer extends Thread{

	private JFrame frame;
	private JTextField txtUser;
	private JTextField txtPass;
	private LobbyServer lobbyServer;
	JList Players;
	ListModel listModelPlayers,listModelRooms,listModelGames;
	public BlockingQueue <MissatgeSwing> llista = new LinkedBlockingQueue<MissatgeSwing>();
	public static enum Operations{addGame,addPlayer,addRoom,delGame,delPlayer,delRoom;}
	public static class MissatgeSwing{
		public Operations operation;
		public Object data;
		public MissatgeSwing(Operations operation,Object data){
			this.operation = operation;
			this.data = data;
		}
	}
	
public boolean stop = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinServer window = new WinServer();
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
	public WinServer() throws IOException {
		lobbyServer = new LobbyServer(this);
		initialize();
		System.out.println("Hi ha vida despres de initcialitzar swing");
		start();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	public void para(){stop = true;}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 671, 761);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		listModelPlayers = new DefaultListModel();
		Players = new JList(listModelPlayers);
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
		btnLogin.setBounds(10, 585, 89, 23);
		panel.add(btnLogin);
		
		JButton btnJoin = new JButton("Join");
		btnJoin.setBounds(10, 619, 89, 23);
		panel.add(btnJoin);
		
		JButton btnToRoom = new JButton("To Room");
		btnToRoom.setBounds(10, 653, 89, 23);
		panel.add(btnToRoom);
		
		JButton btnToGame = new JButton("To Game");
		btnToGame.setBounds(10, 688, 89, 23);
		panel.add(btnToGame);
		
		JButton btnTestAddUser = new JButton("Test Add User");
		btnTestAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SPlayer sPlayer = new SPlayer(txtUser.getText());
				addPlayer(sPlayer);
			}
		});
		btnTestAddUser.setBounds(187, 585, 128, 23);
		panel.add(btnTestAddUser);
		
		JButton btnTestRemoveUser = new JButton("Test Remove User");
		btnTestRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delPlayer(txtUser.getText());
			}
		});
		btnTestRemoveUser.setBounds(187, 619, 128, 23);
		panel.add(btnTestRemoveUser);
		
		JLabel lblPlayers = new JLabel("Players");
		lblPlayers.setBounds(76, 11, 46, 14);
		panel.add(lblPlayers);
		
		JLabel lblRooms = new JLabel("Rooms");
		lblRooms.setBounds(309, 11, 46, 14);
		panel.add(lblRooms);
		
		JLabel lblGames = new JLabel("Games");
		lblGames.setBounds(461, 249, 46, 14);
		panel.add(lblGames);
	}
	private void addPlayer (SPlayer player){
		((DefaultListModel) listModelPlayers).addElement(player.nom);
	}
	private void delPlayer (String nom){
		((DefaultListModel) listModelPlayers).removeElement(nom);
		
	}
	private void addRoom (SRoom sRoom){
		((DefaultListModel) listModelRooms).addElement(sRoom.nom);
	}
	private void delRoom (String nom){
		((DefaultListModel) listModelRooms).removeElement(nom);
	}
	private void addGame(SGame sGame){
		((DefaultListModel) listModelGames).addElement(((SGameSetimig)sGame));
	}
	private void delGame(String nom){
		((DefaultListModel) listModelGames).removeElement(nom);
	}
	
	public void run(){
		while (!stop){
			try {
				MissatgeSwing e = llista.take();
				//public static enum operations{addGame,addPlayer,addRoom,delGame,delPlayer,delRoom;}
				switch (e.operation) {
					case addPlayer : addPlayer(((SPlayer)e.data));
					break;
					case delPlayer : delPlayer(((SPlayer)e.data).nom);
					break;
					case addGame : addGame(((SGame)e.data));
					break;
					case delGame : delGame (((SGame)e.data).nom);
					break;
					case addRoom : addRoom ((SRoom)e.data);
					break;
					case delRoom : delRoom ((String)e.data);
					break;
				} 
			}catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
	}
}
