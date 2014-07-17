package ztb;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

class Entrust{
	//慢慢写吧骚年
	static String year[]={"1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977",
		"1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992"};
	static String month[]={"01","02","03","04","05","06","07","08","09","10","11","12"};
	static String academic[]={"博士","硕士","本科","高中","初中"};

}

 class InfoUp extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//组件声明
	private JPanel jp;
	private JScrollPane sp;
	private JLabel lName,lTerritory,lEducation,lBirth,lBirthyear,lBirthmonth,lSex,lAcademic,lSelf;
	private JButton btSubmit,btReplace,btReturn;
	private static JTextField tName,tTerritory,tEducation;
	private static JComboBox bYear,bMonth,bAcademic;
	private static JRadioButton rMale,rFemale;
	private static JTextArea aSelf;

	//构造函数
	public InfoUp(){
		
		super("专家信息录入");
		this.setTitle("专家-添加/修改信息");
		this.setSize(706, 528);
		this.setLocation(200, 100);
		this.setResizable(false);
		
		String path=System.getProperty("user.dir")+"\\src\\ztb\\images\\";
		
		jp=new MyPanel(path+"background.jpg");
		jp.setLayout(null);
		

		
		//设置组件
		lName=new JLabel("姓       名：");
		lName.setFont(FontDemo.font);
		tName=new JTextField(20);
		tName.setFont(FontDemo.font);
		lSex=new JLabel("性       别：");
		lSex.setFont(FontDemo.font);
		rMale=new JRadioButton("男");
		rMale.setFont(FontDemo.font);
//		rMale.setSelected(true);
		rFemale=new JRadioButton("女");
		rFemale.setFont(FontDemo.font);
		ButtonGroup bgsex=new ButtonGroup();
		bgsex.add(rMale);
		bgsex.add(rFemale);
		lBirth=new JLabel("出生年月：");
		lBirth.setFont(FontDemo.font);
		bYear=new JComboBox(Entrust.year);  		//这里只能用对象类型数组做参数
		bYear.setFont(FontDemo.font);
		lBirthyear=new JLabel("年");
		lBirthyear.setFont(FontDemo.font);
		bMonth=new JComboBox(Entrust.month);
		bMonth.setFont(FontDemo.font);
		lBirthmonth=new JLabel("月");
		lBirthmonth.setFont(FontDemo.font);
		lAcademic=new JLabel("学       历：");
		lAcademic.setFont(FontDemo.font);
		bAcademic=new JComboBox(Entrust.academic);
		bAcademic.setFont(FontDemo.font);
		lTerritory=new JLabel("专业领域：");
		lTerritory.setFont(FontDemo.font);
		tTerritory=new JTextField(20);
//		tTerritory.setText("如风险评估");
		tTerritory.setFont(FontDemo.font);
		lEducation=new JLabel("教育背景：");
		lEducation.setFont(FontDemo.font);
		tEducation=new JTextField(20);
		tEducation.setFont(FontDemo.font);
		lSelf=new JLabel("自我评价：");
		lSelf.setFont(FontDemo.font);
		aSelf=new JTextArea(5,25);
//		aSelf.setText("请填写您的自我推荐信息");
		aSelf.setFont(FontDemo.font);
		aSelf.setBackground(Color.WHITE);
		aSelf.setLineWrap(true);
		aSelf.setWrapStyleWord(true);
		sp=new JScrollPane(aSelf);
//		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		btSubmit=new JButton("提 交");
		btSubmit.setFont(FontDemo.font);
		btReplace=new JButton("重 置"); 
		btReplace.setFont(FontDemo.font);
		btReturn=new JButton("返 回");
		btReturn.setFont(FontDemo.font);
	
		btSubmit.addActionListener(this);
		btReplace.addActionListener(this);
		btReturn.addActionListener(this);
		
		//设置组件位置
		lName.setBounds(190, 40, 75, 25);
		tName.setBounds(280, 40, 200, 25);
		lSex.setBounds(190, 75, 75, 25);
		rMale.setBounds(280, 75, 55, 25);
		rFemale.setBounds(390, 75, 55, 25);
		lBirth.setBounds(190, 110, 75, 25);
		bYear.setBounds(280, 110,85, 25);
		lBirthyear.setBounds(370, 110, 30, 25);
		bMonth.setBounds(390, 110, 60, 25);
		lBirthmonth.setBounds(455, 110, 30, 25);
		lAcademic.setBounds(190, 145, 85, 25);
		bAcademic.setBounds(280, 145, 85, 25);
		lTerritory.setBounds(190,180, 75, 25);
		tTerritory.setBounds(280, 180, 200, 25);
		lEducation.setBounds(190, 215, 75, 25);
		tEducation.setBounds(280,215, 200, 25);
		lSelf.setBounds(190, 250, 75, 25);
//		aSelf.setBounds(90, 275, 290, 80);
		sp.setBounds(190, 285, 290, 100);
		btSubmit	.setBounds(210, 425, 70, 30);
		btReplace.setBounds(290, 425, 70, 30);
		btReturn.setBounds(370,425,70,30);
		
		//添加组件
		jp.add(lName);
		jp.add(tName);
		jp.add(lSex);
		jp.add(rMale);
		jp.add(rFemale);
		jp.add(lBirth);
		jp.add(bYear);
		jp.add(lBirthyear);
		jp.add(bMonth);
		jp.add(lBirthmonth);
		jp.add(lAcademic);
		jp.add(bAcademic);
		jp.add(lTerritory);
		jp.add(tTerritory);
		jp.add(lEducation);
		jp.add(tEducation);
		jp.add(lSelf);
//		jp.add(aSelf);
		jp.add(sp);
		jp.add(btSubmit	);
		jp.add(btReplace);
		jp.add(btReturn);

		//添加中间容器
		this.add(jp);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JTextField  gettName(){
		return InfoUp.tName;
	}
	public JTextField gettEducation(){
		return InfoUp.tEducation;
	}
	public JTextField gettTerritory(){
		return InfoUp.tTerritory;
	}
	public JComboBox getbYear(){
		return InfoUp.bYear;
	}
	public JComboBox getbMonth(){
		return InfoUp.bMonth;
	}
	public JComboBox getbAcademic(){
		return InfoUp.bAcademic;
	}
	public JRadioButton getrFemale(){
		return InfoUp.rFemale;
	}
	public JRadioButton getrMale(){
		return InfoUp.rMale;
	}
	public JTextArea getaSelf(){
		return InfoUp.aSelf;
	}
	
	public void actionPerformed(ActionEvent e){
		Object click=e.getSource();
		
		if(click==btSubmit){
			btSubmitClick();
		}else if(click==btReplace){
			btReplaceClick();
		}else if(click==btReturn){
			btReturnClick();
		}		
	}
	
	private void btSubmitClick(){
		Information.name=tName.getText();
		Information.birth=Information.year+"年 "+Information.month+"月";
//		Information.year=(String)bYear.getSelectedItem();
		Information.year=(String)bYear.getSelectedItem();
		System.out.println(Information.year);
		Information.month=(String)bMonth.getSelectedItem();
		System.out.println(Information.month);
		Information.academic=(String)bAcademic.getSelectedItem();
		Information.territory=tTerritory.getText();
		Information.education=tEducation.getText();
		Information.self=aSelf.getText();
		//把所有属性都放在静态类里，一次更新。
		
		//男0女1
		if(rMale.isSelected()){
			Information.sex="男";
		}else {
			Information.sex="女";
		}
		
		UIManager.put("Label.font", FontDemo.font);
		//非空提示
		if(Information.name.equals("")){
			JOptionPane.showMessageDialog(btSubmit, "姓名不能为空","注意",JOptionPane.WARNING_MESSAGE);
			return;
		}else if(rFemale.isSelected()==false&&rMale.isSelected()==false){
			JOptionPane.showMessageDialog(btSubmit, "请选择性别","注意",JOptionPane.WARNING_MESSAGE);
			return;
		}else if(Information.territory.equals("")){
			JOptionPane.showMessageDialog(btSubmit, "专业领域必填","注意",JOptionPane.WARNING_MESSAGE);
			return;
		}else if(Information.education.equals("")){
			JOptionPane.showMessageDialog(btSubmit, "教育背景必填","注意",JOptionPane.WARNING_MESSAGE);
			return;
		}else{
			
			JDBC.putIntoDB(); 			//调用方法更新数据库信息
			JOptionPane.showMessageDialog(btSubmit, "提交成功","提示",JOptionPane.WARNING_MESSAGE);
			Choose c=new Choose();
			c.setVisible(true);
			this.setVisible(false);
		}
	}
	
	private void btReplaceClick(){
		tName.setText(null);
		bYear.setSelectedIndex(0);
		bMonth.setSelectedIndex(0);
		bAcademic.setSelectedIndex(0);
		tTerritory.setText(null);
		tEducation.setText(null);
		aSelf.setText(null);
	}
	
	private void btReturnClick(){
		Choose c=new Choose();
		c.setVisible(true);
		this.setVisible(false);

	}

}

 