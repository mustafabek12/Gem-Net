import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPanel extends JFrame {

	private JPanel contentPane;
	private static AdminPanel frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AdminPanel();
					frame.setVisible(true);
					frame.setTitle("Admin Panel");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminPanel() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 623, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton log = new JButton("Log");
		log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Log log = new Log();
				log.setVisible(true);
			}
		});
		log.setFont(new Font("Dialog", Font.PLAIN, 25));
		log.setBounds(0, 256, 611, 55);
		contentPane.add(log);
		
		JButton Personel = new JButton("Personel");
		Personel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new Personel()).setVisible(true);
			}
		});
		Personel.setFont(new Font("Dialog", Font.PLAIN, 25));
		Personel.setBounds(0, 203, 611, 55);
		contentPane.add(Personel);
		
		JButton yonetici = new JButton("Yonetici Ekle");
		yonetici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new YoneticiEkle()).setVisible(true);
			}
		});
		yonetici.setFont(new Font("Dialog", Font.PLAIN, 25));
		yonetici.setBounds(0, 157, 611, 55);
		contentPane.add(yonetici);
		
		JButton oyunlar = new JButton("Oyunlar");
		oyunlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Oyunlar a = new Oyunlar();
				a.setVisible(true);
			}
		});
		oyunlar.setFont(new Font("Dialog", Font.PLAIN, 25));
		oyunlar.setBounds(0, 106, 611, 55);
		contentPane.add(oyunlar);
		
		JButton gideri = new JButton("Ayl\u0131k Gideri");
		gideri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Masraflar m = new Masraflar();
				m.setVisible(true);
			}
		});
		gideri.setFont(new Font("Dialog", Font.PLAIN, 25));
		gideri.setBounds(0, 53, 611, 55);
		contentPane.add(gideri);
		
		JButton musteriler = new JButton("M\u00FC\u015Fteriler");
		musteriler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Musteriler m = new Musteriler();
				m.setVisible(true);
			}
		});
		musteriler.setFont(new Font("Dialog", Font.PLAIN, 25));
		musteriler.setBounds(0, 0, 611, 55);
		contentPane.add(musteriler);
	}

}
