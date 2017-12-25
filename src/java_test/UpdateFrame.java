package java_test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class UpdateFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 900;
	static final int HEIGHT = 600;
	private Image bg1 = new ImageIcon("image\\luffy.jpg").getImage();
	private Image bg2 = new ImageIcon("image\\nami.jpg").getImage();
	private Image bg3 = new ImageIcon("image\\sanji.jpg").getImage();
	private String[] rank = { "SSS", "SS", "S", "A", "B" };
	private String[] type = { "防御", "物攻", "法攻", "治疗" };
	private String[] league = { "四皇", "七武海", "海军总部", "草帽海贼团", "白胡子海贼团", "黑胡子海贼团", "阿龙海贼团", "CP9", "其他" };
	private String[] level = { "0", "1", "2", "3", "4", "5" };
	private JTabbedPane tp = new JTabbedPane();
	private JPanel insert_p = new BackgroundPanel(bg1);
	private JPanel modify_p = new BackgroundPanel(bg2);
	private JPanel delete_p = new BackgroundPanel(bg3);
	private JLabel char_input = new JLabel("新建角色：");
	private JLabel choose_rank = new JLabel("评级：");
	private JLabel choose_type = new JLabel("类型：");
	private JLabel choose_league = new JLabel("同盟：");
	private JTextField i_name = new JTextField(8);
	private JComboBox c1 = new JComboBox(rank);
	private JComboBox c2 = new JComboBox(type);
	private JComboBox c3 = new JComboBox(league);
	private JComboBox c4 = new JComboBox(level);
	public JButton insert = new JButton("Go!");
	private JLabel l = new JLabel("觉醒等级：");
	private JLabel l_p = new JLabel("武力：");
	private JLabel l_a = new JLabel("敏捷：");
	private JLabel l_w = new JLabel("智力：");
	private JTextField p = new JTextField(4);
	private JTextField a = new JTextField(4);
	private JTextField w = new JTextField(4);
	public JButton b = new JButton("Go!");
	private JLabel char_modify = new JLabel("修改角色：");
	private JTextField m_name = new JTextField(8);
	public JButton display = new JButton("Go!");
	private JLabel new_char = new JLabel("修改为：");
	private JTextField new_name = new JTextField(8);
	private JComboBox c5 = new JComboBox(rank);
	private JComboBox c6 = new JComboBox(type);
	private JComboBox c7 = new JComboBox(league);
	private JComboBox c8 = new JComboBox(level);
	public JButton mb = new JButton("Go!");
	private JLabel n_p = new JLabel("武力：");
	private JLabel n_a = new JLabel("敏捷：");
	private JLabel n_w = new JLabel("智力：");
	private JTextField np = new JTextField(4);
	private JTextField na = new JTextField(4);
	private JTextField nw = new JTextField(4);
	private JLabel nl = new JLabel("等级：");
	public JButton ab = new JButton("Go!");
	private JScrollPane sp = new JScrollPane();
	private JTextArea yet = new JTextArea();
	private JLabel char_delete = new JLabel("删除角色：");
	private JTextField d_name = new JTextField(8);
	public JButton d = new JButton("Go!");

	public String GetInsertName() {
		return i_name.getText();
	}

	public int GetInsertLevel() {
		return c4.getSelectedIndex();
	}

	public String GetInsertPower() {
		return p.getText();
	}

	public String GetInsertAgility() {
		return a.getText();
	}

	public String GetInsertWisdom() {
		return w.getText();
	}

	public String GetModifyName() {
		return m_name.getText();
	}

	public String GetNewName() {
		return new_name.getText();
	}

	public String GetNewPower() {
		return np.getText();
	}

	public String GetNewAgility() {
		return na.getText();
	}

	public String GetNewWisdom() {
		return nw.getText();
	}

	public int GetModifyLevel() {
		return c8.getSelectedIndex();
	}

	public String GetDeleteName() {
		return d_name.getText();
	}

	public String InsertQuery() {
		String rank_val = c1.getSelectedItem().toString();
		String type_val = c2.getSelectedItem().toString();
		String league_val = c3.getSelectedItem().toString();
		String query = "insert into character values('" + i_name.getText() + "','" + rank_val + "','" + type_val + "','"
				+ league_val + "')";

		return query;
	}

	public String InsertAbility() {
		String query = "insert into ability values('" + i_name.getText() + "','" + c4.getSelectedIndex() + "','"
				+ Float.parseFloat(p.getText()) + "','" + Float.parseFloat(a.getText()) + "','"
				+ Float.parseFloat(w.getText()) + "');";

		return query;
	}

	public String SetCharacter_1() {
		String rank_val = c5.getSelectedItem().toString();
		String type_val = c6.getSelectedItem().toString();
		String league_val = c7.getSelectedItem().toString();
		String query = "update character set name='" + new_name.getText() + "',rank='" + rank_val + "',type='"
				+ type_val + "',c_league='" + league_val + "' where name='" + m_name.getText() + "';";

		return query;
	}

	public String SetCharacter_2() {
		String rank_val = c5.getSelectedItem().toString();
		String type_val = c6.getSelectedItem().toString();
		String league_val = c7.getSelectedItem().toString();
		String query = "update character set rank='" + rank_val + "',type='" + type_val + "',c_league='" + league_val
				+ "' where name='" + m_name.getText() + "';";

		return query;
	}

	public String SetAbility() {
		String query = "update ability set power='" + Float.parseFloat(np.getText()) + "',agility='"
				+ Float.parseFloat(na.getText()) + "',wisdom='" + Float.parseFloat(nw.getText()) + "' where name='"
				+ m_name.getText() + "' and level='" + c8.getSelectedIndex() + "';";

		return query;
	}

	public String DeleteCharacter() {
		String query = "delete from character where name='" + d_name.getText() + "';";

		return query;
	}

	public String DeleteAbility() {
		String query = "delete from ability where name='" + d_name.getText() + "';";

		return query;
	}

	public void GetStatus(ResultSet rs) {
		yet.setText("");
		try {
			if (!rs.next())
				yet.append("N/A");
			else {
				rs.first();
				while (!rs.isAfterLast()) {
					yet.append(rs.getString(1));
					yet.append("\t");
					yet.append(rs.getString(2));
					yet.append("\t");
					yet.append(rs.getString(3));
					yet.append("\t");
					yet.append(rs.getString(4));
					yet.append("\n");
					rs.next();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AbilityStatus(ResultSet rs) {
		try {
			if (!rs.next())
				yet.append("N/A\n");
			else {
				rs.first();
				while (!rs.isAfterLast()) {
					yet.append(rs.getString(1));
					yet.append("\t");
					yet.append("觉醒：");
					yet.append(rs.getString(2));
					yet.append("\t");
					yet.append("武力：");
					yet.append(rs.getString(3));
					yet.append("\t");
					yet.append("敏捷：");
					yet.append(rs.getString(4));
					yet.append("\t");
					yet.append("智力：");
					yet.append(rs.getString(5));
					yet.append("\n");
					rs.next();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		yet.setCaretPosition(0);
	}

	public UpdateFrame() {
		this.setTitle("Update");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setContentPane(tp);
		tp.addTab("Insert", insert_p);
		tp.setEnabledAt(0, true);
		tp.addTab("Modify", modify_p);
		tp.setEnabledAt(1, true);
		tp.addTab("Delete", delete_p);
		tp.setEnabledAt(2, true);

		Box main_bar = Box.createHorizontalBox();
		main_bar.add(char_input);
		main_bar.add(i_name);
		main_bar.add(Box.createHorizontalStrut(5));
		main_bar.add(choose_rank);
		main_bar.add(c1);
		main_bar.add(Box.createHorizontalStrut(5));
		main_bar.add(choose_type);
		main_bar.add(c2);
		main_bar.add(Box.createHorizontalStrut(5));
		main_bar.add(choose_league);
		main_bar.add(c3);
		main_bar.add(Box.createHorizontalStrut(5));
		main_bar.add(insert);
		Box bar = Box.createHorizontalBox();
		bar.add(l);
		bar.add(c4);
		bar.add(Box.createHorizontalStrut(15));
		bar.add(l_p);
		bar.add(p);
		bar.add(Box.createHorizontalStrut(15));
		bar.add(l_a);
		bar.add(a);
		bar.add(Box.createHorizontalStrut(15));
		bar.add(l_w);
		bar.add(w);
		bar.add(Box.createHorizontalStrut(40));
		bar.add(b);
		Box vbox = Box.createVerticalBox();
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(main_bar);
		vbox.add(Box.createVerticalStrut(15));
		vbox.add(bar);
		insert_p.add(vbox, BorderLayout.CENTER);

		Box modi_bar = Box.createHorizontalBox();
		modi_bar.add(Box.createHorizontalStrut(110));
		modi_bar.add(char_modify);
		modi_bar.add(m_name);
		modi_bar.add(Box.createHorizontalStrut(10));
		modi_bar.add(display);
		modi_bar.add(Box.createHorizontalStrut(110));
		Box char_bar = Box.createHorizontalBox();
		char_bar.add(new_char);
		char_bar.add(new_name);
		char_bar.add(Box.createHorizontalStrut(10));
		char_bar.add(c5);
		char_bar.add(Box.createHorizontalStrut(10));
		char_bar.add(c6);
		char_bar.add(Box.createHorizontalStrut(10));
		char_bar.add(c7);
		char_bar.add(Box.createHorizontalStrut(10));
		char_bar.add(mb);
		Box a_bar = Box.createHorizontalBox();
		a_bar.add(nl);
		a_bar.add(c8);
		a_bar.add(Box.createHorizontalStrut(5));
		a_bar.add(n_p);
		a_bar.add(np);
		a_bar.add(Box.createHorizontalStrut(5));
		a_bar.add(n_a);
		a_bar.add(na);
		a_bar.add(Box.createHorizontalStrut(5));
		a_bar.add(n_w);
		a_bar.add(nw);
		a_bar.add(Box.createHorizontalStrut(10));
		a_bar.add(ab);
		Box mbox = Box.createVerticalBox();
		mbox.add(Box.createVerticalStrut(10));
		mbox.add(modi_bar);
		mbox.add(Box.createVerticalStrut(10));
		mbox.add(char_bar);
		mbox.add(Box.createVerticalStrut(10));
		mbox.add(a_bar);
		mbox.add(Box.createVerticalStrut(10));
		mbox.add(sp);
		mbox.add(Box.createVerticalGlue());
		sp.setViewportView(yet);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		yet.setOpaque(false);
		modify_p.add(mbox, BorderLayout.CENTER);

		Box dele_bar = Box.createHorizontalBox();
		dele_bar.add(char_delete);
		dele_bar.add(d_name);
		dele_bar.add(Box.createHorizontalStrut(10));
		dele_bar.add(d);
		Box dbox = Box.createVerticalBox();
		dbox.add(Box.createVerticalStrut(10));
		dbox.add(dele_bar);
		delete_p.add(dbox, BorderLayout.CENTER);
	}
}
