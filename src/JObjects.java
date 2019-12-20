import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JObjects {
	
	public static int count1 ;
	public static JLabel[] lables;
	public static JButton[] buttons;
	public static JTextField[] texts;
	public JObjects(int count) {
		// TODO Auto-generated constructor stub
		count1 = count ;
		lables = new JLabel[count1];
		buttons = new JButton[3*count1];
		texts = new JTextField[count1];
	}

}
