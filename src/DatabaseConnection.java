import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DatabaseConnection {
	private static Connection conn = null ;
	public static Connection dbConnector()
	{
		try{
			Class.forName("org.postgresql.Driver");
			conn =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/GameNet","postgres","Lovely6508");
			return conn ;
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex);
			return null;
		}
	}
}
