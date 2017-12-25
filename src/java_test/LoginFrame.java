package java_test;

import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 360;
	static final int HEIGHT = 240;
	private String id = "admin";
	private String pw = "d_roger";
	private JPanel p = new JPanel();
	private JLabel _id = new JLabel("’À∫≈£∫");
	private JLabel _pw = new JLabel("√‹¬Î£∫");
	public JTextField id_input = new JTextField(10);
	public JPasswordField pw_input = new JPasswordField(10);
	public JButton confirm = new JButton("Go!");

	public String GetId() {
		return id;
	}

	public String GetPw() {
		return pw;
	}

	public LoginFrame() {
		this.setTitle("Login");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setContentPane(p);
		id_input.setMaximumSize(id_input.getPreferredSize());
		pw_input.setMaximumSize(pw_input.getPreferredSize());
		Box id_bar = Box.createHorizontalBox();
		Box pw_bar = Box.createHorizontalBox();
		id_bar.add(_id);
		id_bar.add(Box.createHorizontalStrut(20));
		id_bar.add(id_input);
		pw_bar.add(_pw);
		pw_bar.add(Box.createHorizontalStrut(20));
		pw_bar.add(pw_input);
		Box buttom_bar = Box.createHorizontalBox();
		buttom_bar.add(confirm);
		Box vbox = Box.createVerticalBox();
		vbox.add(Box.createVerticalStrut(30));
		vbox.add(id_bar);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(pw_bar);
		vbox.add(Box.createVerticalStrut(15));
		vbox.add(Box.createVerticalGlue());
		vbox.add(buttom_bar);
		p.add(vbox, BorderLayout.CENTER);
	}
}
