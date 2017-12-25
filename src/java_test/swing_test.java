package java_test;

import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class swing_test {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		MainFrame jf = new MainFrame();
		jf.setVisible(true);

		QueryFrame newjf = new QueryFrame();

		jf.b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newjf.setVisible(true);
			}
		});

		Connection con = DBControl.connect();
		if (con != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = df.format(new Date());
			jf.con_check.setText(" [" + time + "] 数据库连接成功");
		}

		newjf.search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement st = null;
				try {
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String query = newjf.GeneralQuery();

				ResultSet rs = null;
				try {
					rs = st.executeQuery(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				newjf.GeneralResult(rs);
				try {
					st.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		newjf.char_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = newjf.CharQuery();

				Statement st = null;
				try {
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ResultSet rs = null;
				try {
					rs = st.executeQuery(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				newjf.CharResult(rs);
				try {
					st.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		/*
		 * jf.admin_mode.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { jf.dispatchEvent(new
		 * WindowEvent(jf,WindowEvent.WINDOW_CLOSING) ); } });
		 */

		UpdateFrame uf = new UpdateFrame();
		jf.lf.confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jf.lf.GetId().equals(jf.lf.id_input.getText().toString())
						&& jf.lf.GetPw().equals(String.valueOf(jf.lf.pw_input.getPassword()))) {
					uf.setVisible(true);
					jf.lf.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！", "Error", JOptionPane.ERROR_MESSAGE);
					jf.lf.id_input.setText("");
					jf.lf.pw_input.setText("");
				}
			}
		});

		uf.insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement st = null;
				try {
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String check_insert = "select COUNT(*) from character where name='" + uf.GetInsertName() + "'";
				ResultSet rs = null;
				try {
					rs = st.executeQuery(check_insert);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int flag = 0;
				try {
					if (rs.next())
						flag = rs.getInt(1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (uf.GetInsertName().equals(""))
					JOptionPane.showMessageDialog(null, "请输入角色名！", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					if (flag == 1)
						JOptionPane.showMessageDialog(null, "角色已存在，新建失败！", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						String i_query = uf.InsertQuery();
						int succ = 0;
						try {
							succ = st.executeUpdate(i_query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (succ == 0)
							JOptionPane.showMessageDialog(null, "未知错误，新建失败！", "Error", JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "新建角色成功！", "Hint", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				try {
					st.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		uf.b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement st = null;
				try {
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String check_insert = "select COUNT(*) from character where name='" + uf.GetInsertName() + "'";
				ResultSet rs = null;
				try {
					rs = st.executeQuery(check_insert);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int flag = 0;
				try {
					if (rs.next())
						flag = rs.getInt(1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (flag == 0)
					JOptionPane.showMessageDialog(null, "请先新建角色！", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					st = null;
					try {
						st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String check_level = "select COUNT(*) from ability where name='" + uf.GetInsertName()
							+ "' and level='" + uf.GetInsertLevel() + "';";
					rs = null;
					try {
						rs = st.executeQuery(check_level);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int flag_2 = 0;
					try {
						if (rs.next())
							flag_2 = rs.getInt(1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (uf.GetInsertName().equals("") || uf.GetInsertPower().equals("")
							|| uf.GetInsertAgility().equals("") || uf.GetInsertWisdom().equals(""))
						JOptionPane.showMessageDialog(null, "角色名或能力不能为空！", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						if (flag_2 == 1)
							JOptionPane.showMessageDialog(null, "能力已存在，新建失败！", "Error", JOptionPane.ERROR_MESSAGE);
						else {
							String ia_query = uf.InsertAbility();
							int succ = 0;
							try {
								succ = st.executeUpdate(ia_query);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if (succ == 0)
								JOptionPane.showMessageDialog(null, "未知错误，新建失败！", "Error", JOptionPane.ERROR_MESSAGE);
							else
								JOptionPane.showMessageDialog(null, "新建能力成功！", "Hint", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				try {
					st.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		uf.display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement st = null;
				try {
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String check_char = "select COUNT(*) from character where name='" + uf.GetModifyName() + "';";
				ResultSet rs = null;
				try {
					rs = st.executeQuery(check_char);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int flag = 0;
				try {
					if (rs.next())
						flag = rs.getInt(1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (flag == 0)
					JOptionPane.showMessageDialog(null, "角色不存在！", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					String status = "select * from character where name='" + uf.GetModifyName() + "';";
					rs = null;
					try {
						rs = st.executeQuery(status);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					uf.GetStatus(rs);
					String a_status = "select * from ability where name='" + uf.GetModifyName() + "';";
					rs = null;
					try {
						rs = st.executeQuery(a_status);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					uf.AbilityStatus(rs);
				}
				try {
					st.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		uf.mb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement st = null;
				try {
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String check_char = "select COUNT(*) from character where name='" + uf.GetModifyName() + "';";
				ResultSet rs = null;
				try {
					rs = st.executeQuery(check_char);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int flag = 0;
				try {
					if (rs.next())
						flag = rs.getInt(1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (flag == 0)
					JOptionPane.showMessageDialog(null, "角色不存在！", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					st = null;
					try {
						st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String check_new = "select COUNT(*) from character where name='" + uf.GetNewName() + "';";
					rs = null;
					try {
						rs = st.executeQuery(check_new);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int flag_1 = 0;
					try {
						if (rs.next())
							flag_1 = rs.getInt(1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (flag_1 == 1)
						JOptionPane.showMessageDialog(null, "[" + uf.GetNewName() + "] 角色已存在，请重新输入！", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {
						st = null;
						try {
							st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String query = null;
						String twin_query = null;
						if (uf.GetNewName().equals(""))
							query = uf.SetCharacter_2();
						else {
							query = uf.SetCharacter_1();
							twin_query = "update ability set name='" + uf.GetNewName() + "' where name='"
									+ uf.GetModifyName() + "';";
							try {
								st.executeUpdate(twin_query);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						int succ = 0;
						try {
							succ = st.executeUpdate(query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (succ == 0)
							JOptionPane.showMessageDialog(null, "未知错误，修改失败！", "Error", JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "修改角色成功！", "Hint", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				try {
					st.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		uf.ab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement st = null;
				try {
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String check_char = "select COUNT(*) from ability where name='" + uf.GetModifyName() + "' and level='"
						+ uf.GetModifyLevel() + "';";
				ResultSet rs = null;
				try {
					rs = st.executeQuery(check_char);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int flag = 0;
				try {
					if (rs.next())
						flag = rs.getInt(1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (flag == 0)
					JOptionPane.showMessageDialog(null, "角色能力不存在！", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					st = null;
					try {
						st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String query = uf.SetAbility();
					if (uf.GetNewPower().equals("") || uf.GetNewAgility().equals("") || uf.GetNewWisdom().equals(""))
						JOptionPane.showMessageDialog(null, "能力不能为空！", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						int succ = 0;
						try {
							succ = st.executeUpdate(query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (succ == 0)
							JOptionPane.showMessageDialog(null, "未知错误，修改失败！", "Error", JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "修改能力成功！", "Hint", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				try {
					st.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		uf.d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement st = null;
				try {
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String check_char = "select COUNT(*) from character where name='" + uf.GetDeleteName() + "';";
				ResultSet rs = null;
				try {
					rs = st.executeQuery(check_char);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int flag = 0;
				try {
					if (rs.next())
						flag = rs.getInt(1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (flag == 0)
					JOptionPane.showMessageDialog(null, "角色不存在！", "Error", JOptionPane.ERROR_MESSAGE);
				else {
					int ok = JOptionPane.showConfirmDialog(null, "[" + uf.GetDeleteName() + "] 角色与能力将被删除，继续吗？",
							"Caution", JOptionPane.YES_NO_OPTION);
					if (ok == JOptionPane.NO_OPTION)
						;
					else {
						st = null;
						try {
							st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String query = uf.DeleteCharacter();
						String twin_query = uf.DeleteAbility();
						int succ = 0;
						try {
							succ = st.executeUpdate(query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							st.executeUpdate(twin_query);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (succ == 0)
							JOptionPane.showMessageDialog(null, "未知错误，删除失败！", "Error", JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "删除角色及能力成功！", "Hint", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				try {
					st.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
