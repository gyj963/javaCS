package ztb;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Inspect extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jp;
	private JScrollPane sp;
	private JLabel lName,lSex,lBirth,lAcademic,lTerritory,lEducation,lSelf,lSelected,lScore;
	private JButton btReturn;
	private JTextArea ja;
//	private JTable jt;
	String ssex;
	String flagString,scoreString;
	
	
	public Inspect(){
		super("查看所填信息");
		this.setTitle("专家-查看信息");
		this.setSize(706, 528);
		this.setLocation(200, 100);
		this.setResizable(false);
		
		Font font =new Font("微软雅黑",Font.BOLD,14);		
		Font font1=new Font("幼圆",Font.ITALIC,12);
		Font font2=new Font("微软雅黑",Font.BOLD,36);
		String path=System.getProperty("user.dir")+"\\src\\ztb\\images\\";	
		
		jp=new MyPanel(path+"backgroundthird.jpg");
		jp.setLayout(null);
		
		if(Information.flag==1){
			flagString="已被招标商选中";
		}else if(Information.flag==0){
			flagString="未被招标商选中";
		}	
		lSelected= new JLabel("状      态：  "+flagString);
		lSelected.setFont(font1);
		lSelected.setForeground(Color.BLUE);
		
		if(Information.score !=0){
			scoreString=Information.score+"分";
		}else if((Object)Information.score==null){
			scoreString="";
		}
		lScore=new JLabel(scoreString);
		lScore.setFont(font2);
		lScore.setForeground(Color.BLUE);
		
		lName=new JLabel("姓       名：   "+Information.name);
		lName.setFont(font);
		lSex=new JLabel("性       别：   "+Information.sex);
		lSex.setFont(font);
		lBirth=new JLabel("出生年月：   "+Information.birth);
		lBirth.setFont(font);
		lAcademic=new JLabel("学       历：   "+Information.academic);
		lAcademic.setFont(font);
		lTerritory=new JLabel("专业领域：   "+Information.territory);
		lTerritory.setFont(font);
		lEducation=new JLabel("教育背景：   "+Information.education);
		lEducation.setFont(font);
		lSelf=new JLabel("自我推荐：   ");
		lSelf.setFont(font);
		ja=new JTextArea(Information.self);
		ja.setFont(font);
		ja.setLineWrap(true);
		ja.setWrapStyleWord(true);
		sp=new JScrollPane(ja);
		btReturn=new JButton("返  回");
		btReturn.setFont(font);

		btReturn.addActionListener(this);
		
		lSelected.setBounds(320,30,200,25);
		lScore.setBounds(550,60,100,100);
		lName.setBounds(320,70,150,25);
		lSex.setBounds(320,110,100,25);
		lBirth.setBounds(320,150,200,25);
		lAcademic.setBounds(320,190,150,25);
		lTerritory.setBounds(320,230,350,25);
		lEducation.setBounds(320,270,350,25);
		lSelf.setBounds(320, 310, 350, 25);
		sp.setBounds(320,345,290,90);
		btReturn.setBounds(440, 450, 70, 30);
		
		jp.add(lSelected);
		jp.add(lScore);
		jp.add(lName);
		jp.add(lSex);
		jp.add(lBirth);
		jp.add(lAcademic);
		jp.add(lTerritory);
		jp.add(lEducation);
		jp.add(lSelf);
		jp.add(sp);
		jp.add(btReturn);
		
		this.add(jp);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		Object click=e.getSource();
		
		if(click==btReturn){
			btReturnClick();
		}else{}
	}
	
	private void btReturnClick(){
		Choose iu=new Choose();
		iu.setVisible(true);
		this.setVisible(false);
	}
}
