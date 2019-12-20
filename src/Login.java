import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;

import com.sun.xml.internal.bind.v2.runtime.Name;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login {
	public static int AdminID ;
	private JFrame frame;
	private JTextField id;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);

					window.frame.setTitle("Login");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 337, 321);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLoginPage = new JLabel("Login Page");
		lblLoginPage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLoginPage.setBounds(94, 30, 139, 35);
		frame.getContentPane().add(lblLoginPage);
		
		id = new JTextField();
		id.setBounds(162, 96, 116, 22);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		JLabel lblId = new JLabel("AdminID");
		lblId.setBounds(79, 99, 56, 16);
		frame.getContentPane().add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(79, 143, 56, 16);
		frame.getContentPane().add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(162, 140, 116, 22);
		frame.getContentPane().add(password);
		final JLabel name = new JLabel("");
		name.setBounds(410, 77, 56, 16);
		frame.getContentPane().add(name);
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						Connection con = DatabaseConnection.dbConnector();
						String sql = "Select * from yonetici where adminid="+id.getText().toString()+"and sifre='"+password.getText().toString()+"';";
						PreparedStatement st = con.prepareStatement(sql);
						ResultSet rs = st.executeQuery();
						if (rs.next())
						{
							AdminID = Integer.parseInt(id.getText().toString());
							frame.dispose();
							Mainpage main = new Mainpage();
							main.setVisible(true);
						}
						else 
							JOptionPane.showMessageDialog(null, "Wrong Login!");
							con.close();
				}catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Wrong Login!");
				}
						

			}
		});
		btnLogin.setBounds(172, 184, 97, 25);
		frame.getContentPane().add(btnLogin);
		
		
	}
}
