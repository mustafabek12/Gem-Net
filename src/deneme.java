import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class deneme extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deneme frame = new deneme();
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
	public deneme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 948, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKonsolNumarasi = new JLabel("Konsol Numarasi");
		lblKonsolNumarasi.setBounds(48, 13, 125, 16);
		contentPane.add(lblKonsolNumarasi);
		
		JButton btnNewButton = new JButton("Durum");
		btnNewButton.setBounds(12, 42, 161, 25);
		contentPane.add(btnNewButton);
		
		JButton btnBaslat = new JButton("Baslat");
		btnBaslat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBaslat.setBounds(12, 80, 97, 25);
		contentPane.add(btnBaslat);
		
		JButton btnDurdur = new JButton("Durdur");
		btnDurdur.setBounds(12, 118, 97, 25);
		contentPane.add(btnDurdur);
		
		textField = new JTextField();
		textField.setBounds(117, 80, 56, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(117, 119, 56, 22);
		contentPane.add(textField_1);
		
		JButton button = new JButton("Durdur");
		button.setBounds(12, 287, 97, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Baslat");
		button_1.setBounds(12, 249, 97, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Durum");
		button_2.setBounds(12, 211, 161, 25);
		contentPane.add(button_2);
		
		JLabel label = new JLabel("Konsol Numarasi");
		label.setBounds(48, 182, 125, 16);
		contentPane.add(label);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(117, 249, 56, 22);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(117, 288, 56, 22);
		contentPane.add(textField_3);
		
		JButton button_3 = new JButton("Durdur");
		button_3.setBounds(269, 118, 97, 25);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Baslat");
		button_4.setBounds(269, 80, 97, 25);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("Durum");
		button_5.setBounds(269, 42, 161, 25);
		contentPane.add(button_5);
		
		JLabel label_1 = new JLabel("Konsol Numarasi");
		label_1.setBounds(305, 13, 125, 16);
		contentPane.add(label_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(374, 80, 56, 22);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(374, 119, 56, 22);
		contentPane.add(textField_5);
		
		JButton button_6 = new JButton("Durdur");
		button_6.setBounds(537, 118, 97, 25);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("Baslat");
		button_7.setBounds(537, 80, 97, 25);
		contentPane.add(button_7);
		
		JButton button_8 = new JButton("Durum");
		button_8.setBounds(537, 42, 161, 25);
		contentPane.add(button_8);
		
		JLabel label_2 = new JLabel("Konsol Numarasi");
		label_2.setBounds(573, 13, 125, 16);
		contentPane.add(label_2);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(642, 80, 56, 22);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(642, 119, 56, 22);
		contentPane.add(textField_7);
		
		JButton button_9 = new JButton("Durdur");
		button_9.setBounds(757, 118, 97, 25);
		contentPane.add(button_9);
		
		JButton button_10 = new JButton("Baslat");
		button_10.setBounds(757, 80, 97, 25);
		contentPane.add(button_10);
		
		JButton button_11 = new JButton("Durum");
		button_11.setBounds(757, 42, 161, 25);
		contentPane.add(button_11);
		
		JLabel label_3 = new JLabel("Konsol Numarasi");
		label_3.setBounds(793, 13, 125, 16);
		contentPane.add(label_3);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(862, 80, 56, 22);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(862, 119, 56, 22);
		contentPane.add(textField_9);
	}

}
