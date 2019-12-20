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

public class Oyunlar extends JFrame {

	private JPanel contentPane;
	private JTextField ad;
	private JTextField platform;
	private JTextField aciklama;
	private JTextField id;
	private JTable table;
	private JLabel lblId;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Oyunlar frame = new Oyunlar();
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
	public Oyunlar() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 605, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ad = new JTextField();
		ad.setToolTipText("Name");
		ad.setColumns(10);
		ad.setBounds(68, 4, 116, 22);
		contentPane.add(ad);
		
		JLabel label = new JLabel("Ad");
		label.setBounds(34, 7, 22, 16);
		contentPane.add(label);
		
		JLabel lblPlatform = new JLabel("Platform");
		lblPlatform.setBounds(12, 39, 56, 16);
		contentPane.add(lblPlatform);
		
		platform = new JTextField();
		platform.setToolTipText("Name");
		platform.setColumns(10);
		platform.setBounds(68, 36, 116, 22);
		contentPane.add(platform);
		
		aciklama = new JTextField();
		aciklama.setToolTipText("Name");
		aciklama.setColumns(10);
		aciklama.setBounds(266, 32, 116, 22);
		contentPane.add(aciklama);
		
		id = new JTextField();
		id.setToolTipText("Name");
		id.setEditable(false);
		id.setColumns(10);
		id.setBounds(266, 0, 116, 22);
		contentPane.add(id);
		
		JLabel lblAklama = new JLabel("A\u00E7\u0131klama");
		lblAklama.setBounds(198, 36, 56, 16);
		contentPane.add(lblAklama);
		
		JButton addnew = new JButton("Add New");
		addnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection con = DatabaseConnection.dbConnector();
					if (con != null)
					{
						String sql = "Insert into Oyun (ad,platformid,aciklama) values('"+ad.getText().toString()+"',"+platform.getText().toString()+",'"+aciklama.getText().toString()+"');";
						
						Statement st = con.createStatement();
						st.executeUpdate(sql);
						
						sql = "Select * from Oyun;";
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
		addnew.setBounds(477, 32, 97, 25);
		contentPane.add(addnew);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection con = DatabaseConnection.dbConnector();
					if (con != null)
					{
						
						String sql = "Select * from Oyun where ad like '%"+ad.getText().toString()+"%' AND platformid in ( "+platform.getText().toString() + ")";
						
						if (platform.getText().toString().isEmpty())
						{
							sql = "Select * from Oyun where ad like '%"+ad.getText().toString()+"%' AND platformid in (Select id from platform)";
						}
						
						PreparedStatement st = con.prepareStatement(sql);
						ResultSet rs = st.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						con.close();
						
					}		
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		search.setBounds(477, 0, 97, 25);
		contentPane.add(search);
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(row, 0).toString());
				String name = table.getValueAt(row, 1).toString();
				String platformid = table.getValueAt(row, 2).toString();
				String description = table.getValueAt(row, 3).toString();
				
				
				String sql = "Update Oyun set platformid = '" + platformid + "', ad = '" + name + "', aciklama = '" +description +"' Where oyunid = " + id +";";
				
				Statement st;
				Connection con = DatabaseConnection.dbConnector();
				try {
					st = con.createStatement();
					st.executeUpdate(sql);
					sql = "Select * from Oyun;";
					ResultSet rs = st.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Some Problem Occured!");
				}
			}
		});
		update.setBounds(376, 375, 97, 25);
		contentPane.add(update);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int selected = Integer.parseInt(table.getValueAt(row, 0).toString());
				String sql = "Delete from Oyun where oyunid = " + selected ;
				Connection con = DatabaseConnection.dbConnector();
				Statement st;
				try {
					st = con.createStatement();
					st.executeUpdate(sql);
					sql = "Select * from Oyun";
					ResultSet rs = st.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		delete.setBounds(478, 375, 97, 25);
		contentPane.add(delete);
		try{
			Connection con = DatabaseConnection.dbConnector();
			if (con != null)
			{
				String sql = "Select * from Oyun;";
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
						String sql = "Insert into Oyun (ad,platform,aciklama) values('"+ad.getText().toString()+"','"+platform.getText().toString()+"','"+aciklama.getText().toString()+"');";
						
						Statement st = con.createStatement();
						st.executeUpdate(sql);
						
						sql = "Select * from Oyun;";
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
		
		scrollPane = new JScrollPane();
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
				String sql = "Select * from oyun;";
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				con.close();	
			}
		}
		catch (Exception ex){JOptionPane.showMessageDialog(null, ex.getMessage());}
		
		lblId = new JLabel("ID");
		lblId.setBounds(232, 4, 22, 16);
		contentPane.add(lblId);
	}

}
