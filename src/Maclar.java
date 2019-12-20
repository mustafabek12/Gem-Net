import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.DropMode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;


public class Maclar extends JFrame {

	private JPanel contentPane;
	private JTextField birinci;
	private JTextField ikinci;
	private JTextField zaman;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Maclar frame = new Maclar();
					frame.setVisible(true);
					frame.setTitle("Maclar");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Maclar() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 618, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		birinci = new JTextField();
		birinci.setToolTipText("Name");
		birinci.setColumns(10);
		birinci.setBounds(97, 1, 116, 22);
		contentPane.add(birinci);
		
		JLabel Birinci_Takim = new JLabel("Birinci Takim");
		Birinci_Takim.setBounds(15, 4, 110, 16);
		contentPane.add(Birinci_Takim);
		
		JLabel lblIkinciTakim = new JLabel("Ikinci Takim");
		lblIkinciTakim.setBounds(15, 35, 80, 16);
		contentPane.add(lblIkinciTakim);
		
		ikinci = new JTextField();
		ikinci.setToolTipText("Name");
		ikinci.setColumns(10);
		ikinci.setBounds(97, 32, 116, 22);
		contentPane.add(ikinci);
		
		zaman = new JTextField();
		zaman.setToolTipText("Name");
		zaman.setColumns(10);
		zaman.setBounds(306, 13, 116, 22);
		contentPane.add(zaman);
		
		JLabel lblZaman = new JLabel("Zaman");
		lblZaman.setBounds(248, 16, 56, 16);
		contentPane.add(lblZaman);
		
		JButton addnew = new JButton("Add New");
		addnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection con = DatabaseConnection.dbConnector();
					if (con != null)
					{
						String sql = "Insert into Mac (birincitakim,ikincitakim,zaman) values('"+birinci.getText().toString()+"','"+ikinci.getText().toString()+"','"+zaman.getText().toString()+"');";
						
						Statement st = con.createStatement();
						st.executeUpdate(sql);
						
						sql = "Select * from Mac;";
						ResultSet rs = st.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
					}
						
					
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null,ex.getMessage());
				}
			}
		});
		addnew.setBounds(466, 13, 97, 25);
		contentPane.add(addnew);
		try{
			Connection con = DatabaseConnection.dbConnector();
			if (con != null)
			{
				String sql = "Select * from Mac;";
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				con.close();	
			}
		}
		catch (Exception ex){}
		
		addnew = new JButton("Add New");
		addnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection con = DatabaseConnection.dbConnector();
					if (con != null)
					{
						String sql = "Insert into Mac (ad,soyad,telefonno) values('"+birinci.getText().toString()+"','"+ikinci.getText().toString()+"','"+zaman.getText().toString()+"');";
						
						Statement st = con.createStatement();
						st.executeUpdate(sql);
						
						sql = "Select * from Mac;";
						ResultSet rs = st.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
					}
						
					
				}
				catch (SQLException ex)
				{
					JOptionPane.showMessageDialog(null,ex.getMessage());
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1, 71, 585, 304);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setToolTipText("");
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setEditingRow(0);
		table.setEditingColumn(0);
		table.setDropMode(DropMode.INSERT);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		try{
			Connection con = DatabaseConnection.dbConnector();
			if (con != null)
			{
				String sql = "SELECT takimlar.ad Home, a.ad Away, mac.zaman FROM mac JOIN takimlar ON mac.birincitakim = takimlar.takimid JOIN takimlar a ON mac.ikincitakim = a.takimid;";
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				con.close();	
			}
		}
		catch (Exception ex){}
	}

}
