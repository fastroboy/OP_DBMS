package java_test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 900;
	static final int HEIGHT = 600;
	private Image image = new ImageIcon("image\\logo.jpg").getImage();
	private Image bg = new ImageIcon("image\\bg.jpg").getImage();
	private JPanel contentPane = new BackgroundPanel(image);
	private JPanel p1 = new JPanel();
	public JLabel con_check = new JLabel();
	public JButton b1 = new JButton("Sail!");
	private JMenuBar mb = new JMenuBar();
	private JMenu option = new JMenu("Option");
	private JMenuItem admin_mode = new JMenuItem("Admin");
	private JMenuItem manual = new JMenuItem("Manual");
	private JMenuItem log = new JMenuItem("Log");
	private JMenuItem about = new JMenuItem("About..");
	private JFrame man_frame = new JFrame("Manual");
	private JFrame log_frame = new JFrame("Log");
	private JPanel manPane = new BackgroundPanel(bg);
	private JPanel logPane = new BackgroundPanel(bg);
	private JScrollPane mansp = new JScrollPane();
	private JScrollPane logsp = new JScrollPane();
	private JTextArea man_text = new JTextArea();
	private JTextArea log_text = new JTextArea();
	private String copyright = "Copyright: Eiichiro Oda/Shueisha, Toei Animation\n  & BANDAI NAMCO Entertainment Inc.\n";
	private String version = "Version: 1.0\n";
	private String author = "Contact us: 14307130013@fudan.edu.cn";
	public LoginFrame lf = new LoginFrame();
	private Font man_font = new Font("ËÎÌו",Font.PLAIN,15);
	private Font log_font = new Font("Times New Roman",Font.PLAIN,16);

	public MainFrame()
	{
		this.setTitle("OP");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(contentPane);
		this.setJMenuBar(mb);
		man_text.setFont(man_font);
		log_text.setFont(log_font);
		contentPane.setLayout(new BorderLayout());
		p1.setLayout(new BorderLayout());
		p1.setBackground(null);
		p1.setOpaque(false);
		contentPane.add(p1, "South");
		p1.add(con_check, "West");
		p1.add(b1, "East");
		mb.add(option);
		option.add(admin_mode);
		option.addSeparator();
		option.add(manual);
		option.add(log);
		option.add(about);
		man_frame.setSize(WIDTH, HEIGHT);
		man_frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		man_frame.setContentPane(manPane);
		manPane.setLayout(new BorderLayout());
		manPane.add(mansp, "Center");
		mansp.setOpaque(false);
		mansp.getViewport().setOpaque(false);
		mansp.setViewportView(man_text);
		man_text.setOpaque(false);
		log_frame.setSize(WIDTH, HEIGHT);
		log_frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		log_frame.setContentPane(logPane);
		logPane.setLayout(new BorderLayout());
		logPane.add(logsp, "Center");
		logsp.setOpaque(false);
		logsp.getViewport().setOpaque(false);
		logsp.setViewportView(log_text);
		log_text.setOpaque(false);

		admin_mode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lf.setVisible(true);
			}
		});

		manual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				man_frame.setVisible(true);
				man_text.setText("");
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader("manual.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String end = "";
				try {
					while ((end = br.readLine()) != null)
						man_text.append(end + "\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				man_text.setEnabled(false);
				man_text.setDisabledTextColor(Color.BLACK);
			}
		});

		log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log_frame.setVisible(true);
				log_text.setText("");
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader("log.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String end = "";
				try {
					while ((end = br.readLine()) != null)
						log_text.append(end + "\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				log_text.setEnabled(false);
				log_text.setDisabledTextColor(Color.BLACK);
			}
		});

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, copyright + version + author, "About",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
}
