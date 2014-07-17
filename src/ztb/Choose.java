package ztb;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Choose extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jp;
	private JButton btInfo,btUpdate,btInspect,btExit,btSubmit;
	
	public Choose(){
		

		
		super("专家 - "+StaticInfo.USER_NICKNAME);
		this.setTitle("专家 - "+StaticInfo.USER_NICKNAME);
		this.setSize(706,528);
		this.setLocation(200,100);
		this.setResizable(false);
		Information.getInformation();
		String path_back=System.getProperty("user.dir")+"\\src\\ztb\\images\\";
		String path_btInfo=System.getProperty("user.dir")+"\\src\\ztb\\images\\";
		String path_btUpdate=System.getProperty("user.dir")+"\\src\\ztb\\images\\";
		String path_btInspect=System.getProperty("user.dir")+"\\src\\ztb\\images\\";

		jp=new MyPanel(path_back+"backgroundfirst.jpg");
		jp.setLayout(null);
		
		btInfo=new JButton(new ImageIcon(path_btInfo+"专家注册.jpg"));
		btUpdate=new JButton(new ImageIcon(path_btUpdate+"修改信息.jpg"));
		btInspect=new JButton(new ImageIcon(path_btInspect+"查看信息.jpg"));
//		btExit=new JButton(new ImageIcon(path_exit+"退出.jpg"));
		btExit=new JButton("注销登录");
		btExit.setFont(FontDemo.font);
		
		
		btInfo.addActionListener(this);
		btUpdate.addActionListener(this);
		btInspect.addActionListener(this);
		btExit.addActionListener(this);
		
		btInfo.setBounds(80,100, 170, 227);
		btUpdate.setBounds(270, 100, 170, 227);
		btInspect.setBounds(460,100,170,227);
		btExit.setBounds(80,400,170,30);
		
		jp.add(btInfo);
		jp.add(btUpdate);
		jp.add(btInspect);
		jp.add(btExit);

		this.add(jp);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e){
		Object click=e.getSource();
		
		if(click==btInfo){
			btInfoClick();
		}else if(click==btUpdate){
			btUpdateClick();
		}else if(click==btInspect){
			btInspectClick();
		}else if(click==btExit){
			btExitClick();
		}else{}
		
	}
	
	private void btInfoClick(){
		InfoUp iu=new InfoUp();
		iu.setVisible(true);
		UIManager.put("Label.font", FontDemo.font);
		JOptionPane.showMessageDialog(btSubmit, "每个用户只能注册一个专家信息，如果重复注册，以最后一次注册的信息为准！ ","注意",JOptionPane.WARNING_MESSAGE);
		this.setVisible(false);
	}
	
	private void btUpdateClick(){
		if(Information.name==null){
			UIManager.put("Label.font", FontDemo.font);
			JOptionPane.showMessageDialog(btSubmit, "请先注册信息！ ","注意",JOptionPane.WARNING_MESSAGE);
			InfoUp iu=new InfoUp();
			iu.setVisible(true);
			this.setVisible(false);
		}else{
			InfoUp iu=new InfoUp();
			iu.setVisible(true);
			this.setVisible(false);
			
			//		已解决：
			//		姓名和性别显示正常，其余项？
			
			iu.gettName().setText(Information.name);
			iu.gettEducation().setText(Information.education);
			iu.gettTerritory().setText(Information.territory);
			iu.getbYear().setSelectedItem(Information.year);
			iu.getbMonth().setSelectedItem(Information.month);
			iu.getbAcademic().setSelectedItem(Information.academic);
			iu.getaSelf().setText(Information.self);
	//		System.out.println("*");
			if(Information.sex.equals("女")){
				iu.getrFemale().setSelected(true);
			}else if(Information.sex.equals("男")){
				iu.getrMale().setSelected(true);
			}
		}
	}
	
	private void btInspectClick(){
		if(Information.name==null){
			UIManager.put("Label.font", FontDemo.font);
			JOptionPane.showMessageDialog(btSubmit, "请先注册信息！ ","注意",JOptionPane.WARNING_MESSAGE);
			InfoUp iu=new InfoUp();
			iu.setVisible(true);
			this.setVisible(false);
		}else{
			Inspect is=new Inspect();
			is.setVisible(true);
			this.setVisible(false);
		}
	}

	private void btExitClick(){
		this.setVisible(false);
		Login l=new Login();
		l.setVisible(true);
	}

}
