package App.Elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setFont(new Font("Khmer OS System", Font.PLAIN, 11));
		getContentPane().setBackground(new Color(255, 240, 88));
		setTitle("TP4 | ");
		setForeground(new Color(51, 51, 51));
		setBackground(new Color(255, 240, 88));
		setSize(300, 400);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
	}

	/**
	 * Establece el titulo en cada pantalla. Es protected para que cada clase
	 * derivada la vea como private.
	 * 
	 * @param title
	 */
	protected void setHeader(String title) {
		this.setTitle(this.getTitle() + title);
	}
}
