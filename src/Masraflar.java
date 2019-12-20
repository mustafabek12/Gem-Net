import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Masraflar extends JFrame {

	private JPanel contentPane;
	private final JButton btnFaturalar = new JButton("Faturalar");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Masraflar frame = new Masraflar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Masraflar() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 501, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnFaturalar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new Fatura()).setVisible(true);
			}
		});
		btnFaturalar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnFaturalar.setBackground(Color.BLUE);
		btnFaturalar.setBounds(22, 13, 432, 52);
		contentPane.add(btnFaturalar);
		
		JButton btnKdv = new JButton("KDV");
		btnKdv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new KDV()).setVisible(true);
			}
		});
		btnKdv.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnKdv.setBackground(Color.BLUE);
		btnKdv.setBounds(22, 67, 432, 52);
		contentPane.add(btnKdv);
		
		JButton btnMaaslar = new JButton("Maaslar");
		btnMaaslar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new Maas()).setVisible(true);
			}
		});
		btnMaaslar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnMaaslar.setBackground(Color.BLUE);
		btnMaaslar.setBounds(22, 175, 432, 52);
		contentPane.add(btnMaaslar);
		
		JButton btnMalzeme = new JButton("Malzeme");
		btnMalzeme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new Malzeme()).setVisible(true);
			}
		});
		btnMalzeme.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnMalzeme.setBackground(Color.BLUE);
		btnMalzeme.setBounds(22, 121, 432, 52);
		contentPane.add(btnMalzeme);
	}

}
