package comunicaComu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class VGame {

	private JFrame frame;
	private JList listPlayers_1;
	private DefaultListModel model;
	private JLabel lblPlayers;
	private JLabel lblEstat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String nomSala = "Shafire";
					String nomJoc = "Xomp";
					String tipusSala = "Set i mig";
					int maxPlayers = 8;
					VGame window = new VGame(nomSala, nomJoc, tipusSala,  maxPlayers);
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
	public VGame(String nomSala,String nomJoc,String tipusSala, int maxPlayers) {
		initialize( nomSala, nomJoc, tipusSala,  maxPlayers);
	}
	public void addPlayer (String player){
		model.addElement(player);
	}
	public void addPlayer (String[] players){
		int conta = 0;
		for (String aux : players) {
			model.addElement(aux);
			conta++;
		}
		numPlayers(conta);
	}
	public void delPlayer(String player){
		model.removeElement(player);
	}
	public void setEstat(String estat){
		lblEstat.setText(estat);
	}
	private void numPlayers(int num){
		lblPlayers.setText(String.valueOf(num));
	}
	private int getNumPlayers(){ return Integer.valueOf(lblPlayers.getText());}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String nomSala,String nomJoc,String tipusSala, int maxPlayers) {
		frame = new JFrame();
		frame.setBounds(100, 100, 349, 203);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNTipusJoc = new JLabel("Tipus Joc");
		
		JLabel lblNomJoc = new JLabel("Nom joc");
		
		JLabel lblNSala = new JLabel("Sala");
		
		JLabel lblMaximJugadors = new JLabel("Maxim Jugadors");
		
		JLabel lblNEstat = new JLabel("Estat");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNomSala = new JLabel(nomSala);
		
		JLabel lblTipusJoc = new JLabel(tipusSala);
		
		JLabel lblNewLabel = new JLabel(nomJoc);
		
		JLabel lblMaxPlayers = new JLabel(String.valueOf(maxPlayers));
		lblMaxPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblEstat = new JLabel("New label");
		
		JLabel lblJugadors = new JLabel("Jugadors");
		
		lblPlayers = new JLabel("New label");
		lblPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNSala)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblNomSala, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblMaximJugadors)
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNTipusJoc)
									.addComponent(lblNomJoc))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblTipusJoc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblMaxPlayers, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPlayers, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(lblJugadors))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNEstat)
							.addGap(18)
							.addComponent(lblEstat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNSala)
						.addComponent(lblNomSala)
						.addComponent(lblEstat)
						.addComponent(lblNEstat))
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNTipusJoc)
								.addComponent(lblTipusJoc))
							.addGap(8)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNomJoc)
								.addComponent(lblNewLabel))
							.addGap(12)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMaximJugadors)
								.addComponent(lblMaxPlayers))
							.addGap(8)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblJugadors)
								.addComponent(lblPlayers)))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		listPlayers_1 = new JList();
		scrollPane.setColumnHeaderView(listPlayers_1);
		panel.setLayout(gl_panel);
	}
}
