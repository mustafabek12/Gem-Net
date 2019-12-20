import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.DropMode;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Musteriler2 extends JFrame{

	private JFrame frame;
	private JTable table;
	private JButton btnAddNew;
	private JTextField ad;
	private JLabel lblNewLabel;
	private JLabel lblSoyad;
	private JTextField soyad;
	private JTextField id;
	private JLabel lblId;
	private JLabel lblTelefon;
	private JTextField telefon;
	private JScrollPane scrollPane;
	private JButton btnDelete;
	private JButton btnupdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Musteriler2 window = new Musteriler2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Musteriler2() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 629, 468);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection con = DatabaseConnection.dbConnector();
					if (con != null)
					{
						JOptionPane.showMessageDialog(null, "Connected!");
						String sql = "Select * from musteriler where (ad like '%"+ad.getText().toString()+"%' AND soyad like '%"+soyad.getText().toString()+"%' AND telefonno like '%"+telefon.getText().toString()+"%');";
						PreparedStatement st = con.prepareStatement(sql);
						ResultSet rs = st.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						con.close();
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Not Connected!");
					}
						
					
				}
				catch (Exception ex)
				{
					
				}
			}
		});
		btnNewButton.setBounds(489, 0, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 70, 587, 306);
		frame.getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEditingRow(0);
		table.setEditingColumn(0);
		table.setDropMode(DropMode.INSERT);
		table.setToolTipText("");
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
		try{
			Connection con = DatabaseConnection.dbConnector();
			if (con != null)
			{
				String sql = "Select * from musteriler;";
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				con.close();	
			}
		}
		catch (Exception ex){}
		
		btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection con = DatabaseConnection.dbConnector();
					if (con != null)
					{
						String sql = "Insert into musteriler (ad,soyad,telefonno) values('"+ad.getText().toString()+"','"+soyad.getText().toString()+"','"+telefon.getText().toString()+"');";
						
						Statement st = con.createStatement();
						st.executeUpdate(sql);
						
						sql = "Select * from Musteriler;";
						ResultSet rs = st.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
					}
						
					
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Not Added!");
				}
			}
		});
		btnAddNew.setBounds(489, 32, 97, 25);
		frame.getContentPane().add(btnAddNew);
		
		ad = new JTextField();
		ad.setToolTipText("Name");
		ad.setBounds(50, 1, 116, 22);
		frame.getContentPane().add(ad);
		ad.setColumns(10);
		
		lblNewLabel = new JLabel("Ad");
		lblNewLabel.setBounds(27, 4, 22, 16);
		frame.getContentPane().add(lblNewLabel);
		
		lblSoyad = new JLabel("Soyad");
		lblSoyad.setBounds(12, 35, 56, 16);
		frame.getContentPane().add(lblSoyad);
		
		soyad = new JTextField();
		soyad.setToolTipText("Name");
		soyad.setColumns(10);
		soyad.setBounds(50, 33, 116, 22);
		frame.getContentPane().add(soyad);
		
		id = new JTextField();
		id.setToolTipText("Name");
		id.setColumns(10);
		id.setBounds(278, 0, 116, 22);
		frame.getContentPane().add(id);
		try{
			Connection con = DatabaseConnection.dbConnector();
			if (con != null)
			{
				String sql = "SELECT currval('\"Müşteriler_MüşteriİD_seq\"')";
				
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				ResultSet rs = st.executeQuery(sql);
				rs.next();
				int x = rs.getInt(0);
				id.setText(""+x);
			}
				
			
		}
		catch (SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		id.setEditable(false);
		
		lblId = new JLabel("ID");
		lblId.setBounds(258, 4, 56, 16);
		frame.getContentPane().add(lblId);
		
		lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(230, 34, 56, 16);
		frame.getContentPane().add(lblTelefon);
		
		telefon = new JTextField();
		telefon.setToolTipText("Name");
		telefon.setColumns(10);
		telefon.setBounds(278, 32, 116, 22);
		frame.getContentPane().add(telefon);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int selected = Integer.parseInt(table.getValueAt(row, 0).toString());
				String sql = "Delete from musteriler where musteriid = " + selected ;
				Connection con = DatabaseConnection.dbConnector();
				Statement st;
				try {
					st = con.createStatement();
					st.executeUpdate(sql);
					sql = "Select * from musteriler";
					ResultSet rs = st.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnDelete.setBounds(502, 389, 97, 25);
		frame.getContentPane().add(btnDelete);
		
		btnupdate = new JButton("Update");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(row, 0).toString());
				String name = table.getValueAt(row, 1).toString();
				String surname = table.getValueAt(row, 2).toString();
				String telefon = table.getValueAt(row, 3).toString();
				
				
				String sql = "Update musteriler set telefonno = '" + telefon + "', ad = '" + name + "', soyad = '" +surname +"' Where musteriid = " + id +";";
				
				Statement st;
				Connection con = DatabaseConnection.dbConnector();
				try {
					st = con.createStatement();
					st.executeUpdate(sql);
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Some Problem Occured!");
				}
				
				

			}
		});
		btnupdate.setBounds(400, 389, 97, 25);
		frame.getContentPane().add(btnupdate);
	}
}
