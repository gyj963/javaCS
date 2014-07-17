package ztb;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class Login extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p;
	private JLabel lblName, lblPwd;
	private JTextField txtName;
	private JPasswordField txtPwd;
	private JButton btnLog, btnReg;
	String selectSql = "SELECT yonghuming,mima,shenfen,nicheng FROM expert where yonghuming=?";
	
	public Login() {
		
		super("登录");
		String path=System.getProperty("user.dir")+"\\src\\ztb\\images\\";
		p = new MyPanel(path+"登陆界面.jpg");
		p.setLayout(null);
		
		Font font=new Font("微软雅黑",Font.BOLD,14);
		
		lblName = new JLabel("用户名");
		lblPwd = new JLabel("密   码");
		txtName = new JTextField(20);
		txtPwd = new JPasswordField(20);
		//txtPwd.setEchoChar('*');
		btnLog = new JButton("登  陆");
		btnReg = new JButton("注  册");

		lblName.setFont(font);
		lblPwd.setFont(font);
		btnLog.setFont(font);
		btnReg.setFont(font);
		
		lblName.setBounds(90, 120, 60, 25);
		txtName.setBounds(155, 120, 150, 25);
		lblPwd.setBounds(90, 150, 60, 25);
		txtPwd.setBounds(155, 150, 150, 25);
		btnLog.setBounds(90, 200, 100, 29);
		btnReg.setBounds(203, 200, 100, 29);

		btnLog.addActionListener(this);
		btnReg.addActionListener(this);
		
		p.add(lblName);
		p.add(txtName);
		p.add(lblPwd);
		p.add(txtPwd);
		p.add(btnLog);
		p.add(btnReg);

		this.add(p);
		this.setSize(400, 300);
		this.setLocation(500, 200);
		// 设置窗体不可改变大小
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		Object source=e.getSource();
		String name=txtName.getText();
		String pwd=new String(txtPwd.getPassword());
		UIManager.put("Label.font", FontDemo.font);
		if(source==btnLog){
			if(name.equals("")||pwd.equals(""))//这里面还要加数据库里有无此用户和用户密码是否匹配的判断
			{
				JOptionPane.showMessageDialog(btnLog, "用户名或密码不能为空","提示",
						JOptionPane.WARNING_MESSAGE);
			}
			else//数据库里有无此用户和用户密码是否匹配的判断
			{
				try {
					// 通过工具类获取数据库连接
					Connection conn = DBUtil.getConnection();
					System.out.println("连接成功！");
					PreparedStatement pstmt = conn.prepareStatement(selectSql);
					pstmt.setString(1, name);
					ResultSet rs = pstmt.executeQuery();
					rs = pstmt.executeQuery();
					System.out.println(" 操作后查询成功！");
					int flag=0;
					if(!rs.next())
					{
						JOptionPane.showMessageDialog(btnLog, "用户不存在！","提示",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else{
							if(name.equals(rs.getString(1))&&pwd.equals(rs.getString(2)))
							{
								flag=1;
								if(rs.getString(3).equals("专家"))
								{
//									JOptionPane.showMessageDialog(btnLog, "登陆成功！","提示",
//											JOptionPane.INFORMATION_MESSAGE);
									StaticInfo.USER_NAME=name;
									StaticInfo.USER_NICKNAME=rs.getString(4);
									this.setVisible(false);
									Choose s=new Choose();
									s.setVisible(true);
	
								}
								else if(rs.getString(3).equals("招标商"))
								{
//									JOptionPane.showMessageDialog(btnLog, "登陆成功！","提示",
//											JOptionPane.INFORMATION_MESSAGE);
									StaticInfo.USER_NICKNAME=rs.getString(4);
									this.setVisible(false);
									Tender t = new Tender();
									t.setVisible(true);
								}
							}
							else{
								JOptionPane.showMessageDialog(btnLog, "密码错误！","提示",
										JOptionPane.INFORMATION_MESSAGE);
							}
					}
					 // 关闭连接
					if(flag==1){
						DBUtil.closeConnection(conn);
						this.setVisible(false);
					}
				} catch (Exception h) {
					h.printStackTrace();
					JOptionPane.showMessageDialog(btnLog, "异常","提示",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		else if(source==btnReg)
		{
			Register rg=new Register();
			this.setVisible(false);
			rg.setVisible(true);
		}
	}
	public static void main(String[] args) {
		Login f = new Login();
		f.setVisible(true);
	}
}

