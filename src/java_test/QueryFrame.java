package java_test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class QueryFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 900;
	static final int HEIGHT = 600;
	private String[] rank = { "", "SSS", "SS", "S", "A", "B" };
	private String[] type = { "", "防御", "物攻", "法攻", "治疗" };
	private String[] league = { "", "四皇", "七武海", "海军总部", "草帽海贼团", "白胡子海贼团", "黑胡子海贼团", "阿龙海贼团", "CP9", "其他" };
	private String[] level = { "", "0", "1", "2", "3", "4", "5" };
	private Image bg = new ImageIcon("image\\bg.jpg").getImage();
	private JPanel p2 = new BackgroundPanel(bg);
	private JPanel p3 = new JPanel();
	private JPanel p4 = new JPanel();
	private JComboBox c1 = new JComboBox(rank);
	private JComboBox c2 = new JComboBox(type);
	private JComboBox c3 = new JComboBox(league);
	public JButton search = new JButton("Go!");
	private JScrollPane result = new JScrollPane();
	public JTextArea output = new JTextArea();
	private JLabel char_query = new JLabel("角色查询：");
	private JLabel lv = new JLabel("觉醒等级：");
	private JTextField char_input = new JTextField(8);
	public JButton char_search = new JButton("Go!");
	private JButton clear = new JButton("Clear!");
	private JComboBox c4 = new JComboBox(level);

	public String GeneralQuery() {
		String rank_val = c1.getSelectedItem().toString();
		String type_val = c2.getSelectedItem().toString();
		String league_val = c3.getSelectedItem().toString();
		String query = null;
		if (rank_val == "" && type_val == "" && league_val == "")
			query = "select a.name,a.rank,a.type,a.c_league,b.rate from character as a,league as b where a.c_league=b.name;";
		if (rank_val != "" && type_val != "" && league_val != "")
			query = "select a.name,a.rank,a.type,a.c_league,b.rate from character as a,league as b where a.c_league=b.name and rank='"
					+ rank_val + "' and type='" + type_val + "' and c_league='" + league_val + "';";
		if (rank_val != "" && type_val == "" && league_val != "")
			query = "select a.name,a.rank,a.type,a.c_league,b.rate from character as a,league as b where a.c_league=b.name and rank='"
					+ rank_val + "' and c_league='" + league_val + "';";
		if (rank_val != "" && type_val != "" && league_val == "")
			query = "select a.name,a.rank,a.type,a.c_league,b.rate from character as a,league as b where a.c_league=b.name and rank='"
					+ rank_val + "' and type='" + type_val + "';";
		if (rank_val == "" && type_val != "" && league_val != "")
			query = "select a.name,a.rank,a.type,a.c_league,b.rate from character as a,league as b where a.c_league=b.name and type='"
					+ type_val + "' and c_league='" + league_val + "';";
		if (rank_val == "" && type_val == "" && league_val != "")
			query = "select a.name,a.rank,a.type,a.c_league,b.rate from character as a,league as b where a.c_league=b.name and c_league='"
					+ league_val + "';";
		if (rank_val == "" && type_val != "" && league_val == "")
			query = "select a.name,a.rank,a.type,a.c_league,b.rate from character as a,league as b where a.c_league=b.name and type='"
					+ type_val + "';";
		if (rank_val != "" && type_val == "" && league_val == "")
			query = "select a.name,a.rank,a.type,a.c_league,b.rate from character as a,league as b where a.c_league=b.name and rank='"
					+ rank_val + "';";

		return query;
	}

	public String CharQuery() {
		String lv = c4.getSelectedItem().toString();
		String name_val = char_input.getText();
		String query = null;
		if (lv == "")
			query = "select * from ability where name like '%" + name_val + "%';";
		else {
			int lv_value = Integer.parseInt(lv);
			query = "select * from ability where name like '%" + name_val + "%' and level='" + lv_value + "';";
		}

		return query;
	}

	public void GeneralResult(ResultSet rs) {
		output.setText("");
		output.append("角色名\t评级\t类型\t同盟\t评价\n");
		try {
			if (!rs.next())
				output.append("N/A");
			else {
				rs.first();
				while (!rs.isAfterLast()) {
					output.append(rs.getString(1));
					output.append("\t");
					output.append(rs.getString(2));
					output.append("\t");
					output.append(rs.getString(3));
					output.append("\t");
					output.append(rs.getString(4));
					output.append("\t");
					output.append(rs.getString(5));
					output.append("\n");
					rs.next();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void CharResult(ResultSet rs) {
		try {
			if (!rs.next())
				output.append("N/A\n");
			else {
				rs.first();
				while (!rs.isAfterLast()) {
					output.append(rs.getString(1));
					output.append("\t");
					output.append("觉醒：");
					output.append(rs.getString(2));
					output.append("\t");
					output.append("武力：");
					output.append(rs.getString(3));
					output.append("\t");
					output.append("敏捷：");
					output.append(rs.getString(4));
					output.append("\t");
					output.append("智力：");
					output.append(rs.getString(5));
					output.append("\n");
					rs.next();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public QueryFrame() {
		this.setTitle("Query");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setContentPane(p2);
		p2.setLayout(new BorderLayout());
		p3.setLayout(new FlowLayout());
		p4.setLayout(new FlowLayout());
		p2.add(p3, "North");
		p2.add(p4, "South");
		p3.add(new JLabel("评级："));
		p3.add(c1);
		p3.add(new JLabel("类型："));
		p3.add(c2);
		p3.add(new JLabel("同盟："));
		p3.add(c3);
		p3.add(search);
		p4.add(char_query);
		p4.add(char_input);
		p4.add(lv);
		p4.add(c4);
		p4.add(char_search);
		p4.add(clear);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText("");
			}
		});
		p2.add(result, "Center");
		result.setViewportView(output);
		result.setOpaque(false);
		result.getViewport().setOpaque(false);
		output.setOpaque(false);
	}
}
