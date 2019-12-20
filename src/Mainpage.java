import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Mainpage extends JFrame {

	private JPanel contentPane;
	private static Mainpage frame ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Mainpage();
					frame.setVisible(true);
					frame.setTitle("AnaSayfa");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mainpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Konsollar");
		button.setBounds(0, 0, 0, 0);
		button.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(button);
		
		JButton button_1 = new JButton("Admin Panel");
		button_1.setBounds(0, 0, 0, 0);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Kiralar");
		button_2.setBounds(0, 0, 0, 0);
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Kiralar");
		button_3.setBounds(0, 0, 0, 0);
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(button_3);
		
		JButton konsollar = new JButton("Konsollar");
		konsollar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayingConsoles p = new PlayingConsoles();
				p.setVisible(true);
			}
		});
		konsollar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		konsollar.setBounds(0, 0, 121, 382);
		contentPane.add(konsollar);
		
		JButton adminpanel = new JButton("Admin Panel");
		adminpanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel admin = new AdminPanel();
				admin.setVisible(true);
			}
		});
		adminpanel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		adminpanel.setBounds(121, 0, 121, 382);
		contentPane.add(adminpanel);
		
		JButton maclar = new JButton("Maclar");
		maclar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Maclar mac = new Maclar();
				mac.setVisible(true);
			}
		});
		maclar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		maclar.setBounds(244, 0, 121, 382);
		contentPane.add(maclar);
		
		JButton btnTakimlar = new JButton("Takimlar");
		btnTakimlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Takimlar takim = new Takimlar();
				takim.setVisible(true);
			}
		});
		btnTakimlar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTakimlar.setBounds(358, 0, 121, 382);
		contentPane.add(btnTakimlar);
	}

}
