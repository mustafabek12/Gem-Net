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


public class Maas extends JFrame {

	private JPanel contentPane;
	private JTextField ad;
	private JTextField soyad;
	private JTextField telefon;
	private JTextField id;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Maas frame = new Maas();
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
	public Maas() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 618, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ad = new JTextField();
		ad.setToolTipText("Name");
		ad.setColumns(10);
		ad.setBounds(38, 1, 116, 22);
		contentPane.add(ad);
		
		JLabel label = new JLabel("Ad");
		label.setBounds(15, 4, 22, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Soyad");
		label_1.setBounds(0, 35, 56, 16);
		contentPane.add(label_1);
		
		soyad = new JTextField();
		soyad.setToolTipText("Name");
		soyad.setColumns(10);
		soyad.setBounds(38, 33, 116, 22);
		contentPane.add(soyad);
		
		telefon = new JTextField();
		telefon.setToolTipText("Name");
		telefon.setColumns(10);
		telefon.setBounds(266, 32, 116, 22);
		contentPane.add(telefon);
		
		id = new JTextField();
		id.setToolTipText("Name");
		id.setEditable(false);
		id.setColumns(10);
		id.setBounds(266, 0, 116, 22);
		contentPane.add(id);
		
		JLabel label_2 = new JLabel("Telefon");
		label_2.setBounds(218, 34, 56, 16);
		contentPane.add(label_2);
		
		JButton addnew = new JButton("Add New");
		addnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection con = DatabaseConnection.dbConnector();
					if (con != null)
					{
						String sql = "Insert into Maas (ad,soyad,telefonno) values('"+ad.getText().toString()+"','"+soyad.getText().toString()+"','"+telefon.getText().toString()+"');";
						
						Statement st = con.createStatement();
						st.executeUpdate(sql);
						
						sql = "Select * from Maas;";
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
						String sql = "Select * from Maas where (ad like '%"+ad.getText().toString()+"%' AND soyad like '%"+soyad.getText().toString()+"%' AND telefonno like '%"+telefon.getText().toString()+"%');";
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
				String surname = table.getValueAt(row, 2).toString();
				String telefon = table.getValueAt(row, 3).toString();
				
				
				String sql = "Update Maas set telefonno = '" + telefon + "', ad = '" + name + "', soyad = '" +surname +"' Where musteriid = " + id +";";
				
				Statement st;
				Connection con = DatabaseConnection.dbConnector();
				try {
					st = con.createStatement();
					st.executeUpdate(sql);
					sql = "Select * from Maas;";
					ResultSet rs = st.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,e1.getMessage());
				}
			}
		});
		update.setBounds(388, 389, 97, 25);
		contentPane.add(update);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int selected = Integer.parseInt(table.getValueAt(row, 0).toString());
				String sql = "Delete from Maas where musteriid = " + selected ;
				Connection con = DatabaseConnection.dbConnector();
				Statement st;
				try {
					st = con.createStatement();
					st.executeUpdate(sql);
					sql = "Select * from Maas";
					ResultSet rs = st.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		delete.setBounds(490, 389, 97, 25);
		contentPane.add(delete);
		
		table = new JTable();
		table.setToolTipText("");
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setEditingRow(0);
		table.setEditingColumn(0);
		table.setDropMode(DropMode.INSERT);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(1, 71, 585, 304);
		try{
			Connection con = DatabaseConnection.dbConnector();
			if (con != null)
			{
				String sql = "Select * from Maas;";
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
						String sql = "Insert into Maas (ad,soyad,telefonno) values('"+ad.getText().toString()+"','"+soyad.getText().toString()+"','"+telefon.getText().toString()+"');";
						
						Statement st = con.createStatement();
						st.executeUpdate(sql);
						
						sql = "Select * from Maas;";
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
		contentPane.add(table);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(237, 4, 56, 16);
		contentPane.add(lblId);
	}

}
