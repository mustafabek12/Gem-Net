import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PlayingConsoles extends JFrame {
	
	private JLabel[] lables;
	int x = 0 ;
	int count ;
	private JButton[] buttons ;
	private JTextField[] texts ;
	int j = 0 ;
	int i = 0 ;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayingConsoles frame = new PlayingConsoles();
					frame.setVisible(true);
					frame.setTitle("Consoles");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlayingConsoles() {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1182, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ResultSet rs = null;
		try{
		Connection con = DatabaseConnection.dbConnector();
		if (con != null)
		{
			String sql = "Select COUNT(id) from konsol";
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
			lables = new JLabel[count];
			buttons = new JButton[3*count];
			texts = new JTextField[2*count];
			
			for (; x < count ; x++)
			{
				lables[x] = new JLabel("Konsol Numarasi");
				lables[x].setBounds(70+250*i, 10+170*j, 107, 16);
				contentPane.add(lables[x]);
				
				buttons[3*x] = new JButton("Bos");
				buttons[3*x].setBackground(Color.GREEN);
				buttons[3*x].setBounds(12+250*i, 10+170*j+32, 216, 25);
				contentPane.add(buttons[3*x]);
				
				
				
				texts[2*x] = new JTextField();
				texts[2*x].setBounds(121+250*i, 10+170*j+64, 107, 22);
				contentPane.add(texts[2*x]);
				texts[2*x].setColumns(10);
				
				buttons[3*x+1] = new JButton("Baslat");
				buttons[3*x+1].setBounds(12+250*i, 10+170*j+64, 97, 25);
				contentPane.add(buttons[3*x+1]);
				buttons[3*x+1].addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Date date = new Date();
						JButton button = (JButton)e.getSource();
			            int butonKonumu = konum(button)/3;
			            
			            texts[2*butonKonumu].setText(String.format("%02d:%02d", date.getHours(),date.getMinutes()));
			            buttons[3*butonKonumu].setBackground(Color.RED);
					}
					
					
				});
				
				texts[2*x+1] = new JTextField();
				texts[2*x+1].setColumns(10);
				texts[2*x+1].setBounds(121+250*i, 10+170*j+98, 107, 22);
				contentPane.add(texts[2*x+1]);
				
				buttons[3*x+2] = new JButton("Durdur");
				buttons[3*x+2].setBounds(12+250*i, 10+170*j+96, 97, 25);
				contentPane.add(buttons[3*x+2]);
				
				buttons[3*x+2].addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Date date = new Date();
						double hournow = date.getHours();
						double minutenow = date.getMinutes();
						
						int butonKonumu = konum((JButton)e.getSource())/3;
						
						double minutestarted = Integer.parseInt(texts[2*butonKonumu].getText().toString().substring(3,5));
						double hourStarted = Integer.parseInt(texts[2*butonKonumu].getText().toString().substring(0,2));
						
						double minutsplayed = minutenow - minutestarted ;
						double hoursplayed = hournow - hourStarted ;
						
						double totalplayed = hoursplayed + minutsplayed/60;
						
						
						texts[2*butonKonumu+1].setText(String.format("%.1f TL", totalplayed*6));
						buttons[3*butonKonumu].setBackground(Color.GREEN);
						
						
						
						
					
						
						
						
						
					}
					
					
				});
				
				i++;
				if (i % 3 == 0)
				{
					j++;
					i = 0 ;
				}
					

			}
			
		}
			
		
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null, ex.getMessage());
	}

	}
	public int konum(JButton button)
	{
		for (int n = 0; n < 3*count ; n++)
		{
			if (buttons[n].equals(button))
			{
				return n ;
			}
		}
		return -1 ;
	}

}
