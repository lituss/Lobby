package test;

import java.awt.EventQueue;
import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.rmi.ObjectSpace;

import comunicaComu.IPlayer;
import comunicaComu.Network;

public class LobbyClient {
	Client client;
	IPlayer player;
	ProxyLobby proxyLobby;
	public boolean connected = false;

	public LobbyClient (WinServer winserver) {
		client = new Client();
		client.start();

		// Register the classes that will be sent over the network.
		Network.register(client);

		// Get the Player on the other end of the connection.
		// This allows the client to call methods on the server.
		player = ObjectSpace.getRemoteObject(client, Network.PLAYER, IPlayer.class);

		client.addListener(new Listener() {
			public void disconnected (Connection connection) {
				EventQueue.invokeLater(new Runnable() {
					public void run () {
						// Closing the frame calls the close listener which will stop the client's update thread.
						//chatFrame.dispose();
						// aqui el que hem de fer quan es desconecta 
					}
				});
			}
		});

		

		// The chat frame contains all the Swing stuff.
		proxyLobby = new ProxyLobby();
		// Register the chat frame so the server can call methods on it.
		new ObjectSpace(client).register(Network.PROXY_LOBBY, proxyLobby);
		// This listener is called when the send button is clicked.
		/*chatFrame.setSendListener(new Runnable() {
			public void run () {
				player.sendMessage(chatFrame.getSendText());
			}
		});
		// This listener is called when the chat window is closed.
		chatFrame.setCloseListener(new Runnable() {
			public void run () {
				client.stop();
			}
		});
		chatFrame.setVisible(true);
*/
		// We'll do the connect on a new thread so the ChatFrame can show a progress bar.
		// Connecting to localhost is usually so fast you won't see the progress bar.
		new Thread("Connect") {
			public void run () {
				try {
					client.connect(5000, "localhost", Network.port);
					// Server communication after connection can go here, or in Listener#connected().
					//player.registerName(name);
				} catch (IOException ex) {
					ex.printStackTrace();
					System.exit(1);
				}
			}
		}.start();
	}
}
/*
	// This is the JFrame for the client. It implments IChatFrame so the server can call methods on it.
	static private class ChatFrame extends JFrame implements IChatFrame {
		CardLayout cardLayout;
		JProgressBar progressBar;
		JList messageList;
		JTextField sendText;
		JButton sendButton;
		JList nameList;

		public ChatFrame (String host) {
			super("Chat RMI Client");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(640, 200);
			setLocationRelativeTo(null);

			Container contentPane = getContentPane();
			cardLayout = new CardLayout();
			contentPane.setLayout(cardLayout);
			{
				JPanel panel = new JPanel(new BorderLayout());
				contentPane.add(panel, "progress");
				panel.add(new JLabel("Connecting to " + host + "..."));
				{
					panel.add(progressBar = new JProgressBar(), BorderLayout.SOUTH);
					progressBar.setIndeterminate(true);
				}
			}
			{
				JPanel panel = new JPanel(new BorderLayout());
				contentPane.add(panel, "chat");
				{
					JPanel topPanel = new JPanel(new GridLayout(1, 2));
					panel.add(topPanel);
					{
						topPanel.add(new JScrollPane(messageList = new JList()));
						messageList.setModel(new DefaultListModel());
					}
					{
						topPanel.add(new JScrollPane(nameList = new JList()));
						nameList.setModel(new DefaultListModel());
					}
					DefaultListSelectionModel disableSelections = new DefaultListSelectionModel() {
						public void setSelectionInterval (int index0, int index1) {
						}
					};
					messageList.setSelectionModel(disableSelections);
					nameList.setSelectionModel(disableSelections);
				}
				{
					JPanel bottomPanel = new JPanel(new GridBagLayout());
					panel.add(bottomPanel, BorderLayout.SOUTH);
					bottomPanel.add(sendText = new JTextField(), new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					bottomPanel.add(sendButton = new JButton("Send"), new GridBagConstraints(1, 0, 1, 1, 0, 0,
						GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
				}
			}

			sendText.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					sendButton.doClick();
				}
			});
		}

		public void setSendListener (final Runnable listener) {
			sendButton.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent evt) {
					if (getSendText().length() == 0) return;
					listener.run();
					sendText.setText("");
					sendText.requestFocus();
				}
			});
		}

		public void setCloseListener (final Runnable listener) {
			addWindowListener(new WindowAdapter() {
				public void windowClosed (WindowEvent evt) {
					listener.run();
				}

				public void windowActivated (WindowEvent evt) {
					sendText.requestFocus();
				}
			});
		}

		public String getSendText () {
			return sendText.getText().trim();
		}

		// The server calls this method as needed.
		public void setNames (final String[] names) {
			EventQueue.invokeLater(new Runnable() {
				public void run () {
					cardLayout.show(getContentPane(), "chat");
					DefaultListModel model = (DefaultListModel)nameList.getModel();
					model.removeAllElements();
					for (String name : names)
						model.addElement(name);
				}
			});
		}

		// The server calls this method as needed.
		public void addMessage (final String message) {
			EventQueue.invokeLater(new Runnable() {
				public void run () {
					DefaultListModel model = (DefaultListModel)messageList.getModel();
					model.addElement(message);
					messageList.ensureIndexIsVisible(model.size() - 1);
				}
			});
		}	
}
}
*/