import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class YoneticiEkle extends JFrame {

	private JPanel contentPane;
	private JTextField ad;
	private JTextField tc;
	private JTextField telefon;
	private JTextField adres;
	private JPasswordField sifre;
	private JTextField id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YoneticiEkle frame = new YoneticiEkle();
					frame.setVisible(true);
					frame.setTitle("Yonetici Ekle");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public YoneticiEkle() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Ad");
		label.setBounds(33, 16, 56, 16);
		contentPane.add(label);
		
		ad = new JTextField();
		ad.setColumns(10);
		ad.setBounds(80, 13, 193, 22);
		contentPane.add(ad);
		
		JLabel label_1 = new JLabel("Soyad");
		label_1.setBounds(12, 51, 56, 16);
		contentPane.add(label_1);
		
		final JTextField soyad = new JTextField();
		soyad.setColumns(10);
		soyad.setBounds(80, 45, 193, 22);
		contentPane.add(soyad);
		
		JLabel label_2 = new JLabel("Sifre");
		label_2.setBounds(22, 80, 56, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("TC");
		label_3.setBounds(33, 109, 56, 16);
		contentPane.add(label_3);
		
		tc = new JTextField();
		tc.setColumns(10);
		tc.setBounds(80, 109, 193, 22);
		contentPane.add(tc);
		
		JLabel label_4 = new JLabel("Telefon");
		label_4.setBounds(12, 144, 56, 16);
		contentPane.add(label_4);
		
		telefon = new JTextField();
		telefon.setColumns(10);
		telefon.setBounds(80, 141, 193, 22);
		contentPane.add(telefon);
		
		JLabel label_5 = new JLabel("Adres");
		label_5.setBounds(22, 176, 56, 16);
		contentPane.add(label_5);
		
		adres = new JTextField();
		adres.setColumns(10);
		adres.setBounds(80, 173, 193, 22);
		contentPane.add(adres);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection con = DatabaseConnection.dbConnector();
					if (con != null)
					{
						String sql = "Insert into Yonetici (adminid,ad,soyad,telefonno,sifre,TCNO,adres) values('"+id.getText().toString()+"','"+ad.getText().toString()+"','"+ soyad.getText().toString()+"','"+telefon.getText().toString()+"','"+sifre.getText().toString()+"','"+ tc.getText().toString()+"','"+adres.getText().toString()+"');";
						Statement st = con.createStatement();
						st.executeUpdate(sql);
						JOptionPane.showMessageDialog(null,"Added Succesfully!");
						con.close();
					}
						
					
				}
				catch (SQLException ex)
				{
					JOptionPane.showMessageDialog(null,ex.getMessage());
				}
			}});
		
		btnEkle.setBounds(80, 249, 97, 25);
		contentPane.add(btnEkle);
		
		sifre = new JPasswordField();
		sifre.setBounds(80, 80, 193, 22);
		contentPane.add(sifre);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(80, 214, 193, 22);
		contentPane.add(id);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(22, 217, 56, 16);
		contentPane.add(lblId);
	}
}
