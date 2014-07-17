package ztb;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;



public class Register extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p;
	private JLabel lblName, lblNickname, lblPwd, lblRepwd, lblMailbox, lblIdentity, lblSex, lblBirthday,lblYear,lblMonth,lblDay;
	private JTextField txtName, txtNickname, txtMailbox;
	private JPasswordField txtPwd, txtRePwd;
	private JComboBox cbIdentity;
	private JButton btnOk, btnCancle, btnReturn;
	String identity[]={"专家"};
	String year[]={"1992","1993"};
	String month[]={"1","2","31"};
	String day[]={"1","2","3"};
	String insertSql = "INSERT INTO expert (id,yonghuming,nicheng,mima,youxiang,shenfen)VALUES(id.nextval,?,?,?,?,?)";
	String selectSql = "SELECT yonghuming,nicheng,mima,youxiang,shenfen FROM expert";
	public Register(){
		
		super("用户注册");
		String path=System.getProperty("user.dir")+"\\src\\ztb\\images\\";
		p = new MyPanel(path+"注册.jpg");
		p.setLayout(null);
		
		Font font=new Font("微软雅黑",Font.BOLD,14);
		
		lblName = new JLabel("用  户  名");
		lblName.setFont(font);
		lblNickname = new JLabel("昵       称");
		lblNickname.setFont(font);
		lblPwd = new JLabel("密       码");
		lblPwd.setFont(font);
		lblRepwd = new JLabel("确认密码");
		lblRepwd.setFont(font);
		lblMailbox = new JLabel("常用邮箱");
		lblMailbox.setFont(font);
		lblIdentity = new JLabel("身       份");
		lblIdentity.setFont(font);
		lblSex = new JLabel("性       别");
		lblSex.setFont(font);
		lblBirthday = new JLabel("出生日期");
		lblBirthday.setFont(font);
		lblYear = new JLabel("年");
		lblYear.setFont(font);
		lblMonth = new JLabel("月");
		lblMonth.setFont(font);
		lblDay = new JLabel("日");
		lblDay.setFont(font);
		txtName = new JTextField(20);
		txtNickname = new JTextField(20);
		txtMailbox = new JTextField(20);
		txtPwd = new JPasswordField(20);
		txtRePwd = new JPasswordField(20);
		cbIdentity = new JComboBox(identity);
		btnOk = new JButton("确  定");
		btnOk.setFont(font);
		btnCancle = new JButton("取  消");
		btnCancle.setFont(font);
		btnReturn = new JButton("返  回");
		btnReturn.setFont(font);
		
		btnOk.addActionListener(this);
		btnCancle.addActionListener(this);
		btnReturn.addActionListener(this);
		
		lblName.setBounds(90,100,75,25);
		txtName.setBounds(180,100,170,25);
		lblNickname.setBounds(90,135,75,25);
		txtNickname.setBounds(180,135,170,25);
		lblPwd.setBounds(90,170,75,25);
		txtPwd.setBounds(180,170,170,25);
		lblRepwd.setBounds(90,205,75,25);
		txtRePwd.setBounds(180,205,170,25);
		lblMailbox.setBounds(90,240,75,25);
		txtMailbox.setBounds(180,240,170,25);
		lblIdentity.setBounds(90,275,75,25);
		cbIdentity.setBounds(180,275,170,25);
		btnOk.setBounds(110,410,70,30);
		btnCancle.setBounds(190,410,70,30);
		btnReturn.setBounds(270,410,70,30);
		p.add(lblName);
		p.add(txtName);
		p.add(lblNickname);
		p.add(txtNickname);
		p.add(lblPwd);
		p.add(txtPwd);
		p.add(lblRepwd);
		p.add(txtRePwd);
		p.add(lblMailbox);
		p.add(txtMailbox);
		p.add(lblIdentity);
		p.add(cbIdentity);
		p.add(btnOk);
		p.add(btnCancle);
		p.add(btnReturn);
		this.add(p);
		this.setSize(460,510);
		this.setLocation(500,100);
	}
	public void actionPerformed(ActionEvent e)
	{
		Object source=e.getSource();
		if(source == btnOk)
		{
			btnOkClick();
		}
		if(source == btnCancle)
		{
			btnCancleClick();
		}
		if(source == btnReturn)
		{
			btnReturnClick();
		}
	}
	private void btnOkClick()
	{
		String name = txtName.getText();
		String nickName = txtNickname.getText();
		String  pwd = new String(txtPwd.getPassword());
		String rePwd = new String(txtRePwd.getPassword());
		String mailBox = txtMailbox.getText();

		UIManager.put("Label.font", FontDemo.font);
		if(name.equals("")){
			JOptionPane.showMessageDialog(btnOk, "用户名不能为空","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(nickName.equals("")){
			JOptionPane.showMessageDialog(btnOk, "昵称不能为空","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(pwd.equals("")){
			JOptionPane.showMessageDialog(btnOk, "密码不能为空","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(pwd.length()<6||pwd.length()>15){
			JOptionPane.showMessageDialog(btnOk, "密码长度范围在6~15之间","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(rePwd.equals("")){
			JOptionPane.showMessageDialog(btnOk, "确认密码不能为空","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(!rePwd.equals(pwd)){
			JOptionPane.showMessageDialog(btnOk, "确认密码和密码必须相同","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(mailBox.equals("")){
			JOptionPane.showMessageDialog(btnOk, "常用邮箱不能为空","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (!Regex.verifyEmail(mailBox)) {
			JOptionPane.showMessageDialog(btnOk, "Email不合法");
			return;
		}
		try {
			// 通过工具类获取数据库连接
			Connection conn = DBUtil.getConnection();
			System.out.println("连接成功！");
			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, name);
			pstmt.setString(2, nickName);
			pstmt.setString(3, pwd);
			pstmt.setString(4, mailBox);
			pstmt.setString(5, "专家");
			int count = pstmt.executeUpdate();
			System.out.println("添加" + count + "行！");
			pstmt = conn.prepareStatement(selectSql);
			ResultSet rs = pstmt.executeQuery();
			rs = pstmt.executeQuery();
			System.out.println(" 操作后查询成功！");
			while (rs.next()) {
				System.out.println("行 " + rs.getRow() + ":" + rs.getString(1)
						+ "," + rs.getString(2) + "," + rs.getString(3) + ","
						+ rs.getString(4) + "," + rs.getString(5));
			}
			 // 关闭连接
			DBUtil.closeConnection(conn);
			JOptionPane.showMessageDialog(btnOk, "注册成功!返回登录 ","提示",
					JOptionPane.INFORMATION_MESSAGE);
			Login lg=new Login();
			this.setVisible(false);
			lg.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(btnOk, "注册失败,已有此用户","提示",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private void btnCancleClick()
	{
		txtName.setText("");
		txtNickname.setText("");
		txtPwd.setText("");
		txtRePwd.setText("");
		txtMailbox.setText("");
	}
	private void btnReturnClick()
	{
		Login lg=new Login();
		this.setVisible(false);
		lg.setVisible(true);
	}
}
