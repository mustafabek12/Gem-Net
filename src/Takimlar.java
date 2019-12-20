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


public class Takimlar extends JFrame {

	private JPanel contentPane;
	private JTextField ad;
	private JTextField id;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Takimlar frame = new Takimlar();
					frame.setVisible(true);
					frame.setTitle("Takimlar");
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
	public Takimlar() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 618, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAd = new JLabel("Ad");
		lblAd.setBounds(1, 35, 56, 16);
		contentPane.add(lblAd);
		
		ad = new JTextField();
		ad.setToolTipText("Name");
		ad.setColumns(10);
		ad.setBounds(39, 33, 197, 22);
		contentPane.add(ad);
		
		id = new JTextField();
		id.setToolTipText("Name");
		id.setColumns(10);
		id.setBounds(39, 0, 197, 22);
		contentPane.add(id);
		
		JButton addnew = new JButton("Add New");
		addnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection con = DatabaseConnection.dbConnector();
					if (con != null)
					{
						String sql = "Insert into Takimlar (ad,takimid) values('"+ad.getText().toString()+"',"+id.getText().toString()+");";
						
						Statement st = con.createStatement();
						st.executeUpdate(sql);
						
						sql = "Select * from Takimlar;";
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
						String sql = "Select takimid,ad from Takimlar where ad like '%"+ad.getText().toString()+"%' AND takimid = "+id.getText().toString();
						if (id.getText().toString().isEmpty())
							sql = "Select takimid,ad from Takimlar where ad like '%"+ad.getText().toString()+"%' AND takimid IN (Select takimid from takimlar );";
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
				
				
				String sql = "Update Takimlar set takimid = " + id + ", ad = '" + name + "' Where takimid = " + id +";";
				
				Statement st;
				Connection con = DatabaseConnection.dbConnector();
				try {
					st = con.createStatement();
					st.executeUpdate(sql);
					sql = "Select takimid,ad from Takimlar;";
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
				String sql = "Delete from Takimlar where takimid = " + selected ;
				Connection con = DatabaseConnection.dbConnector();
				Statement st;
				try {
					st = con.createStatement();
					st.executeUpdate(sql);
					sql = "Select takimid,ad from Takimlar";
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
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(13, 3, 56, 16);
		contentPane.add(lblId);
		try{
			Connection con = DatabaseConnection.dbConnector();
			if (con != null)
			{
				String sql = "Select takimid,ad from Takimlar;";
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				con.close();	
			}
		}
		catch (Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

}
