package proves;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.Rectangle;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class provaRoom {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					provaRoom window = new provaRoom();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public provaRoom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 426, 477);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane scrollPane_LobbyPlayers = new JScrollPane();
		scrollPane_LobbyPlayers.setBounds(303, 11, 97, 227);
		frame.getContentPane().add(scrollPane_LobbyPlayers);
		
		
		JList listLobbyPlayers = new JList();
		scrollPane_LobbyPlayers.setViewportView(listLobbyPlayers);
		JButton btnOmpla = new JButton("Ompla");
		btnOmpla.setBounds(36, 404, 63, 23);
		btnOmpla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Vector<String> llista_lobbyPlayers = new Vector();
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				llista_lobbyPlayers.add("Pere");
				listLobbyPlayers.setListData(llista_lobbyPlayers);
			}
			
		});
		frame.getContentPane().add(btnOmpla);
		frame.getContentPane().setLayout(null);
		
		
		JScrollPane scrollPane_rooms = new JScrollPane();
		scrollPane_rooms.setBounds(36, 11, 268, 227);
		frame.getContentPane().add(scrollPane_rooms);
		
		JList list_rooms = new JList();
		scrollPane_rooms.setViewportView(list_rooms);
		
		JList list = new JList();
		list.setBounds(0, 0, 1, 1);
		
		frame.getContentPane().add(list);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 266, 225);
		//frame.getContentPane().add(panel);
		panel.setLayout(null);
		list.add(panel);
		JLabel lblNomSala = new JLabel("Set i mig");
		lblNomSala.setBounds(10, 11, 46, 14);
		panel.add(lblNomSala);
		
		JLabel lblTipusSala = new JLabel("Cobalto");
		lblTipusSala.setBounds(10, 33, 46, 14);
		panel.add(lblTipusSala);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(175, 55, 81, 159);
		panel.add(scrollPane);
		
		JLabel lblMaxPlayers = new JLabel("max Players");
		lblMaxPlayers.setBounds(66, 11, 65, 14);
		panel.add(lblMaxPlayers);
		
		JLabel label = new JLabel("5");
		label.setBounds(129, 11, 19, 14);
		panel.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(303, 11, 1, 227);
		frame.getContentPane().add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 270, 268, 70);
		frame.getContentPane().add(panel_1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 268, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 70, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
	}
}
