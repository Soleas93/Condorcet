package be.presentation;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.KeyEvent;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9047883467236372662L;
	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel HomePanel, ShowPanel, OrderPanel;
	private static Home frame;
	
	private HashMap<String, Integer> total = new HashMap<String, Integer>();
	private ArrayList<Spectacle> listeSpectacles = new ArrayList<Spectacle>(3);

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	private Spectacle spectacle1 = new Spectacle(1, dateFromString("14/11/2020"), "Je suis la description du spectacle 1");
	private Spectacle spectacle2 = new Spectacle(2, dateFromString("18/11/2020"), "Je suis la description du spectacle 2");
	private Spectacle spectacle3 = new Spectacle(3, dateFromString("12/12/2020"), "Je suis la description du spectacle 3");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void SwitchPanels(JPanel p) {
		layeredPane.removeAll();
		layeredPane.add(p);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 580, 400);
		contentPane = new JPanel();
		contentPane.setVisible(true);
		contentPane.setVisible(!isVisible());
		contentPane.setBorder(null);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 580, 371);
		contentPane.add(layeredPane);
		
			HomePanel = new JPanel();
			HomePanel.setOpaque(false);
			HomePanel.setBounds(0, 0, 580, 371);
			HomePanel.setLayout(null);
			layeredPane.add(HomePanel);
		
				JLabel title = new JLabel("Choisissez une option au dessus");
				title.setHorizontalTextPosition(SwingConstants.CENTER);
				title.setHorizontalAlignment(SwingConstants.CENTER);
				title.setForeground(Color.BLACK);
				title.setBounds((580-328)/2, 120, 328, 49);
				HomePanel.add(title);
			

			ShowPanel = new JPanel();
			ShowPanel.setOpaque(false);
			ShowPanel.setBounds(0, 0, 580, 371);
			ShowPanel.setLayout(null);
			layeredPane.add(ShowPanel);
			
				JLabel lblNewLabel = new JLabel("Choisissez un spectacle");
				lblNewLabel.setBounds(207, 36, 194, 29);
				ShowPanel.add(lblNewLabel);
			
				JButton btnNewButton = new JButton("Spectacle 1 : 50$");
				btnNewButton.setBounds(207, 104, 143, 34);
				btnNewButton.addActionListener((event) -> {
					total.put("Spectacle 1", 50);
					listeSpectacles.add(spectacle1);
					System.out.println(total);
				});
				ShowPanel.add(btnNewButton);
				
				JButton btnNewButton_1 = new JButton("Spectacle 2 : 35$");
				btnNewButton_1.setBounds(208, 166, 142, 34);
				btnNewButton_1.addActionListener((event) -> {
					total.put("Spectacle 2", 35);
					listeSpectacles.add(spectacle2);
					System.out.println(total);
				});
				ShowPanel.add(btnNewButton_1);
				
				JButton btnNewButton_2 = new JButton("Spectacle 3 : 40$");
				btnNewButton_2.setBounds(207, 227, 143, 34);
				btnNewButton_2.addActionListener((event) -> {
					total.put("Spectacle 3", 40);
					listeSpectacles.add(spectacle3);
					System.out.println(total);
				});
				ShowPanel.add(btnNewButton_2);
				
			OrderPanel = new JPanel();
			OrderPanel.setOpaque(false);
			OrderPanel.setBounds(0, 0, 580, 371);
			OrderPanel.setLayout(null);
			layeredPane.add(OrderPanel);

		CreateMenuBar();
	}
	
	private void RefreshOrder() {

		OrderPanel.removeAll();
		
		JLabel lblNewLabel2 = new JLabel("Liste de vos articles : ");
		lblNewLabel2.setBounds(207, 36, 194, 29);
		OrderPanel.add(lblNewLabel2);
		
		int height = 60;
		Integer tot = 0;
		
		for(@SuppressWarnings("rawtypes") Map.Entry sp: total.entrySet()) {
			JLabel c = new JLabel(sp.getKey().toString() + " Prix : " + sp.getValue() + "$");
			c.setBounds(220, height, 194, 29);
			OrderPanel.add(c);
			height += 20;
			tot += (Integer)sp.getValue();
		}
		
		JLabel c = new JLabel("Total à payer : " + tot.toString() + "$");
		c.setBounds(220, height+20, 194, 29);
		OrderPanel.add(c);
		
		if(tot != 0) {
			JButton b = new JButton("Imprimer vos tickets");
			b.setBounds(207, height+50, 160, 34);
			b.addActionListener((event) -> {
				Printer.print(listeSpectacles);
			});
			OrderPanel.add(b);
		}
	}
	
	private void CreateMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("Spectacle");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		JMenu editMenu = new JMenu("Espace personnel");
		editMenu.setMnemonic(KeyEvent.VK_F);
		
		JMenu exitMenu = new JMenu("Quitter");
		exitMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		exitMenu.setMnemonic(KeyEvent.VK_F);

		
		JMenuItem eMenuItem = new JMenuItem("Liste des spectacles");
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.addActionListener((event) -> {
			SwitchPanels(ShowPanel);
		});
		
		JMenuItem eEditItem = new JMenuItem("Mes commandes");
		eEditItem.setMnemonic(KeyEvent.VK_E);
		eEditItem.addActionListener((event) -> {
			SwitchPanels(OrderPanel);
			RefreshOrder();
		});
		

		fileMenu.add(eMenuItem);
		editMenu.add(eEditItem);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(exitMenu);

		setJMenuBar(menuBar);
		SwitchPanels(HomePanel);
	}
	
	private Date dateFromString(String dateString) {
		try {
			Date date = dateFormat.parse(dateString);
			return date;
		} catch (ParseException e1) {
			e1.printStackTrace();
			return new Date();
		}
		
	}
}