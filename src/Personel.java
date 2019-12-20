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


public class Personel extends JFrame {

	private JPanel contentPane;
	private JTextField ad;
	private JTextField maas;
	private JTextField telefon;
	private JTextField soyad;
	private JTable table;
	private JTextField tc;
	private JTextField adres;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personel frame = new Personel();
					frame.setVisible(true);
					frame.setTitle("Musteriler");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Personel() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 631, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ad = new JTextField();
		ad.setToolTipText("Name");
		ad.setColumns(10);
		ad.setBounds(53, 1, 116, 22);
		contentPane.add(ad);
		
		JLabel label = new JLabel("Ad");
		label.setBounds(15, 4, 22, 16);
		contentPane.add(label);
		
		JLabel lblAdres = new JLabel("Maas");
		lblAdres.setBounds(15, 39, 56, 16);
		contentPane.add(lblAdres);
		
		maas = new JTextField();
		maas.setToolTipText("Name");
		maas.setColumns(10);
		maas.setBounds(53, 37, 116, 22);
		contentPane.add(maas);
		
		telefon = new JTextField();
		telefon.setToolTipText("Name");
		telefon.setColumns(10);
		telefon.setBounds(281, 36, 116, 22);
		contentPane.add(telefon);
		
		soyad = new JTextField();
		soyad.setToolTipText("Name");
		soyad.setColumns(10);
		soyad.setBounds(281, 1, 116, 22);
		contentPane.add(soyad);
		
		JLabel label_2 = new JLabel("Telefon");
		label_2.setBounds(233, 38, 56, 16);
		contentPane.add(label_2);
		
		JButton addnew = new JButton("Add New");
		addnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection con = DatabaseConnection.dbConnector();
					if (con != null)
					{
						String sql = "Insert into Personel (ad,soyad,telefonno,tcno,adres,maas) values('"+ad.getText().toString()+"','"+soyad.getText().toString()+"','"+telefon.getText().toString()+"','"+tc.getText().toString()+"','"+adres.getText().toString()+"',"+maas.getText().toString()+");";
						
						Statement st = con.createStatement();
						st.executeUpdate(sql);
						
						sql = "Select * from Personel;";
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
		addnew.setBounds(477, 45, 97, 25);
		contentPane.add(addnew);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection con = DatabaseConnection.dbConnector();
					if (con != null)
					{
						String sql = "Select * from Personel where (ad like '%"+ad.getText().toString()+"%' AND soyad like '%"+maas.getText().toString()+"%' AND tcno like '%"+telefon.getText().toString()+"%');";
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
		search.setBounds(477, 13, 97, 25);
		contentPane.add(search);
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(row, 0).toString());
				String name = table.getValueAt(row, 1).toString();
				String surname = table.getValueAt(row, 2).toString();
				String telefon = table.getValueAt(row, 3).toString();
				String TC = table.getValueAt(row, 4).toString();
				String address = table.getValueAt(row, 5).toString();
				int salary = Integer.parseInt(table.getValueAt(row, 6).toString());
				
				
				String sql = "Update Personel set ad = '" + name + "', soyad = '" + surname + "', telefonno = '" +telefon +"', tcno = '" + TC + "', adres = '" + address + "', maas = " +salary +" Where personalid = " + id +";";
				
				Statement st;
				Connection con = DatabaseConnection.dbConnector();
				try {
					st = con.createStatement();
					st.executeUpdate(sql);
					sql = "Select * from Personel;";
					ResultSet rs = st.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
			}
		});
		update.setBounds(389, 455, 97, 25);
		contentPane.add(update);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int selected = Integer.parseInt(table.getValueAt(row, 0).toString());
				String sql = "Delete from Personel where personalid = " + selected ;
				Connection con = DatabaseConnection.dbConnector();
				Statement st;
				try {
					st = con.createStatement();
					st.executeUpdate(sql);
					sql = "Select * from Personel";
					ResultSet rs = st.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		delete.setBounds(491, 455, 97, 25);
		contentPane.add(delete);
		try{
			Connection con = DatabaseConnection.dbConnector();
			if (con != null)
			{
				String sql = "Select * from Personel;";
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
						String sql = "Insert into Personel (ad,soyad,telefonno) values('"+ad.getText().toString()+"','"+maas.getText().toString()+"','"+telefon.getText().toString()+"');";
						
						Statement st = con.createStatement();
						st.executeUpdate(sql);
						
						sql = "Select * from Personel;";
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
		
		
		
		JLabel lblId = new JLabel("Soyad");
		lblId.setBounds(233, 5, 56, 16);
		contentPane.add(lblId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 136, 600, 311);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		try{
			Connection con = DatabaseConnection.dbConnector();
			if (con != null)
			{
				String sql = "Select * from Personel;";
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				con.close();	
			}
		}
		catch (Exception ex){}
		
		JLabel lblTc = new JLabel("TC");
		lblTc.setBounds(15, 75, 56, 16);
		contentPane.add(lblTc);
		
		tc = new JTextField();
		tc.setToolTipText("Name");
		tc.setColumns(10);
		tc.setBounds(53, 73, 116, 22);
		contentPane.add(tc);
		
		JLabel lblAdres_1 = new JLabel("Adres");
		lblAdres_1.setBounds(233, 74, 56, 16);
		contentPane.add(lblAdres_1);
		
		adres = new JTextField();
		adres.setToolTipText("Name");
		adres.setColumns(10);
		adres.setBounds(281, 72, 116, 22);
		contentPane.add(adres);
	}
}
