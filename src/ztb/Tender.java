package ztb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;



public class Tender extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	  private JMenu fileMenu,helpMenu;
	  private JMenuItem exitItem,helpMenuItem;
	  private JScrollPane lscrollPane,rscrollPane1,rscrollPane2,rscrollPane3,rscrollPane4;
	  private Container container;
	  private JTable tabCheckAll,tabCheckselected,tabCheckEvaluate,tabSubmitEvaluate;
	  private JPanel p,pr1,pr2,pr3,pr4,prd,prwel;
	  private JButton btnCheckall,btnCheckselected,btnCheckEvaluate,btnSubmitEvaluate,btnReturn,btnSelect,btnGroup,btnEvaluate;
	  private final String path=System.getProperty("user.dir")+"\\src\\ztb\\images\\";
	  Integer[] zuhao={new Integer(1),new Integer(2),new Integer(3),new Integer(4),new Integer(5),new Integer(6),new Integer(7),new Integer(8),new Integer(9),new Integer(10)};
	  Integer[] pingjia={new Integer(100),new Integer(95),new Integer(90),new Integer(85),new Integer(80),new Integer(75),new Integer(70),new Integer(65),new Integer(60),new Integer(55)
	  ,new Integer(50),new Integer(45),new Integer(40),new Integer(35),new Integer(30),new Integer(25),new Integer(20),new Integer(15),new Integer(10),new Integer(5)};
	  private JComboBox boxZuhao=new JComboBox(zuhao);
	  private JComboBox boxPingjia=new JComboBox(pingjia);
	  Cursor cs=new Cursor(Cursor.HAND_CURSOR);
	  String[] sCheckAll={"专家编号","专家姓名","性别","出生年月","学历","教育背景","专业领域","是否选择"};
	  String[] sCheckSelected={"专家编号","专家姓名","性别","出生年月","学历","教育背景","专业领域","小组编号"};
	  String[] sCheckEvaluate={"小组编号","专家编号","专家姓名","性别","出生年月","学历","教育背景","专业领域","小组评价"};
	  String[] sSubmitEvaluate={"小组编号","专家姓名","小组评价"};
	  Vector<String> vCheckAll=new Vector<String>();
	  Vector<String> vCheckSelected=new Vector<String>();
	  Vector<String> vCheckEvaluate=new Vector<String>();
	  Vector<String> vSubmitEvaluate=new Vector<String>();
	  String selectSql1 = "SELECT id,xingming,xingbie,shengri,xueli,jiaoyubeijing,zhuanyelingyu,beixuanfou FROM expert order by id asc";
	  String selectSql2 = "SELECT id,xingming,xingbie,shengri,xueli,jiaoyubeijing,zhuanyelingyu,zuhao,beixuanfou FROM expert order by id asc";
	  String selectSql3 = "SELECT zuhao,id,xingming,xingbie,shengri,xueli,jiaoyubeijing,zhuanyelingyu,pingjia,beixuanfou FROM expert order by zuhao asc,id asc";
	  String selectSql4 = "SELECT zuhao,wmsys.wm_concat(xingming) as xm,pingjia,beixuanfou FROM expert group by zuhao,pingjia,beixuanfou";
	  String updateSql1 = "update expert set beixuanfou=? where id=?";
	  String updateSql2 = "update expert set zuhao=? where id=?";
	  String updateSql4 = "update expert set pingjia=? where zuhao=?";
	  String selectSqlconfirm = "SELECT pingjia From expert where id=?";
	  Vector<Vector<Object>> rows1 = new Vector<Vector<Object>>();
	  Vector<Vector<Object>> rows2 = new Vector<Vector<Object>>();
	  Vector<Vector<Object>> rows3 = new Vector<Vector<Object>>();
	  Vector<Vector<Object>> rows4 = new Vector<Vector<Object>>();
	  Vector<Integer[]> changeRow1=new Vector<Integer[]>();
	  Vector<Integer[]> changeRow2=new Vector<Integer[]>();
//	  Vector changeRow3=new Vector();
	  Vector<Integer[]> changeRow4=new Vector<Integer[]>();
	  private void refreshPr1(){
		  try {
			  	rows1.clear();
			  	changeRow1.clear();
				// 通过工具类获取数据库连接
				Connection conn = DBUtil.getConnection();
				System.out.println("连接成功！");
//				查看所有
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql1);
				ResultSet rs1 = pstmt1.executeQuery();
				System.out.println(" 操作后查询成功！");
				ResultSetMetaData rsmd1 = rs1.getMetaData();
				while (rs1.next()) {
					Vector<Object> currentRow = new Vector<Object>();
					if(rs1.getObject(1)!=null&&rs1.getObject(2)!=null)
					{
						System.out.println("行 " + rs1.getRow() + ":" + rs1.getString(1)
								+ "," + rs1.getString(2) + "," + rs1.getString(3) + ","
								+ rs1.getString(4) + "," + rs1.getString(5)+ "," + rs1.getString(6)
								+ "," + rs1.getString(7)+ "," + rs1.getString(8));
						for(int i=1;i<rsmd1.getColumnCount();i++)
				    	{
				    		currentRow.addElement(rs1.getObject(i));
				    	}
				    	if(rs1.getInt(rsmd1.getColumnCount())==0)
				    	{
				    		currentRow.addElement(new Boolean(false));
				    	}
				    	else if(rs1.getInt(rsmd1.getColumnCount())==1)
				    	{
				    		currentRow.addElement(new Boolean(true));
				    	}
						rows1.addElement(currentRow);
					}
			    	
				}
				
				 // 关闭连接
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }
	  private void refreshPr2(){
		  try {
			  	rows2.clear();
			  	changeRow2.clear();
				// 通过工具类获取数据库连接
				Connection conn = DBUtil.getConnection();
				System.out.println("连接成功！");
//				查看已选
				PreparedStatement pstmt2 = conn.prepareStatement(selectSql2);
				ResultSet rs2 = pstmt2.executeQuery();
				System.out.println(" 操作后查询成功！");
				ResultSetMetaData rsmd2 = rs2.getMetaData();
				while (rs2.next()) {
					Vector<Object> currentRow2 = new Vector<Object>();
					if(rs2.getObject(1)!=null&&rs2.getInt(9)==1)
					{
						System.out.println("行 " + rs2.getRow() + ":" + rs2.getString(1)
								+ "," + rs2.getString(2) + "," + rs2.getString(3) + ","
								+ rs2.getString(4) + "," + rs2.getString(5)+ "," + rs2.getString(6)
								+ "," + rs2.getString(7)+ "," + rs2.getString(8)+ "," + rs2.getString(9));
						for(int i=1;i<rsmd2.getColumnCount();i++)
				    	{
				    		currentRow2.addElement(rs2.getObject(i));
				    	}
						rows2.addElement(currentRow2);
					}
			    	
				}
				 // 关闭连接
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }
	  private void refreshPr3(){
		  try {
			  	rows3.clear();
			  	
				// 通过工具类获取数据库连接
				Connection conn = DBUtil.getConnection();
				System.out.println("连接成功！");
//				查看所有
				PreparedStatement pstmt3 = conn.prepareStatement(selectSql3);
				ResultSet rs3 = pstmt3.executeQuery();
				System.out.println(" 操作后查询成功！");
				ResultSetMetaData rsmd3 = rs3.getMetaData();
				while (rs3.next()) {
					Vector<Object> currentRow = new Vector<Object>();
					if(rs3.getObject(1)!=null&&rs3.getObject(2)!=null&&rs3.getInt(10)==1)
					{
						System.out.println("行 " + rs3.getRow() + ":" + rs3.getString(1)
								+ "," + rs3.getString(2) + "," + rs3.getString(3) + ","
								+ rs3.getString(4) + "," + rs3.getString(5)+ "," + rs3.getString(6)
								+ "," + rs3.getString(7)+ "," + rs3.getString(8)+ "," 
								+ rs3.getString(9)+ "," + rs3.getString(10));
						for(int i=1;i<rsmd3.getColumnCount();i++)
				    	{
				    		currentRow.addElement(rs3.getObject(i));
				    	}
						rows3.addElement(currentRow);
					}
			    	
				}
				 // 关闭连接
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
	  } 
	  private void refreshPr4(){
		  try {
			  	rows4.clear();
			  	changeRow4.clear();
				// 通过工具类获取数据库连接
				Connection conn = DBUtil.getConnection();
				System.out.println("连接成功！");
//				提交评价
				PreparedStatement pstmt4 = conn.prepareStatement(selectSql4);
				ResultSet rs4 = pstmt4.executeQuery();
				System.out.println(" 操作后查询成功！");
				ResultSetMetaData rsmd4 = rs4.getMetaData();
				while (rs4.next()) {
					Vector<Object> currentRow4 = new Vector<Object>();
					if(rs4.getObject(1)!=null&&rs4.getInt(4)==1)
					{
						System.out.println("行 " + rs4.getRow() + ":" + rs4.getString(1)
								+ "," + rs4.getString(2) + "," + rs4.getString(3) + ","
								+ rs4.getString(4));
						for(int i=1;i<=rsmd4.getColumnCount();i++)
				    	{
				    		currentRow4.addElement(rs4.getObject(i));
				    	}
						rows4.addElement(currentRow4);
					}
			    	
				}
				 // 关闭连接
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }
	  private void initPr1(){
		  try {
				// 通过工具类获取数据库连接
				Connection conn = DBUtil.getConnection();
				System.out.println("连接成功！");
//				查看所有
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql1);
				ResultSet rs1 = pstmt1.executeQuery();
				System.out.println(" 操作后查询成功！");
				ResultSetMetaData rsmd1 = rs1.getMetaData();
				while (rs1.next()) {
					Vector<Object> currentRow = new Vector<Object>();
					if(rs1.getObject(1)!=null)
					{
						System.out.println("行 " + rs1.getRow() + ":" + rs1.getString(1)
								+ "," + rs1.getString(2) + "," + rs1.getString(3) + ","
								+ rs1.getString(4) + "," + rs1.getString(5)+ "," + rs1.getString(6)
								+ "," + rs1.getString(7)+ "," + rs1.getString(8));
						for(int i=1;i<rsmd1.getColumnCount();i++)
				    	{
				    		currentRow.addElement(rs1.getObject(i));
				    	}
				    	if(rs1.getInt(rsmd1.getColumnCount())==0)
				    	{
				    		currentRow.addElement(new Boolean(false));
				    	}
				    	else if(rs1.getInt(rsmd1.getColumnCount())==1)
				    	{
				    		currentRow.addElement(new Boolean(true));
				    	}
						rows1.addElement(currentRow);
					}
			    	
				}
				vCheckAll.addAll(Arrays.asList(sCheckAll));
				
				 // 关闭连接
				DBUtil.closeConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    pr1 = new JPanel();
		    pr1.setLayout(new BorderLayout());
		    pr1.setBackground(Color.WHITE);
		    tabCheckAll=new JTable(new CustomModel(rows1,vCheckAll));
		    tabCheckAll.setPreferredScrollableViewportSize(new Dimension(550,30));
		    changeRow1.clear();
		    tabCheckAll.getModel().addTableModelListener(new TableModelListener()
					{
						@Override
						public void tableChanged(TableModelEvent e)
						{
							System.out.println(e.getColumn());
							int tr = e.getFirstRow();
							Integer middle[] = new Integer[2];
							middle[0] = tr;
							middle[1] = Integer.parseInt(tabCheckAll.getValueAt(tr, 0).toString());
							changeRow1.add(middle);
						}
					});
		    rscrollPane1 = new JScrollPane(tabCheckAll);
		    pr1.add(rscrollPane1, BorderLayout.CENTER);
		    prd=new JPanel();
		    prd.setBackground(Color.white);
		    prd.setLayout(new FlowLayout());
		    btnSelect=new JButton("保存选择");
		    btnSelect.setFont(FontDemo.font);
		    btnSelect.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
//							遍历JTable是否选择列
							try {
								// 通过工具类获取数据库连接
								Connection conn = DBUtil.getConnection();
								System.out.println("连接成功！");
								PreparedStatement pstmt = conn.prepareStatement(updateSql1);
								System.out.println(changeRow1);
								Iterator<Integer[]> iter=changeRow1.iterator();
								
								while(iter.hasNext())
								{
									Integer[] a = (Integer[]) iter.next();
									if((Boolean)tabCheckAll.getValueAt(a[0], 7)==true)
									{
										
										pstmt.setInt(1,1);
									}
									else if((Boolean)tabCheckAll.getValueAt(a[0], 7)==false)
									{
										pstmt.setInt(1,0);
									}
									pstmt.setInt(2, a[1]);
									int count = pstmt.executeUpdate();
									System.out.println(count);
								}
								pstmt = conn.prepareStatement(selectSql1);
								ResultSet rs = pstmt.executeQuery();
								System.out.println(" 操作后查询成功！");
								while (rs.next()) {
									if(rs.getObject(1)!=null&&rs.getObject(2)!=null)
									{
										System.out.println("行 " + rs.getRow() + ":" + rs.getString(1)
											+ "," + rs.getString(2) + "," + rs.getString(3) + ","
											+ rs.getString(4) + "," + rs.getString(5)+ "," + rs.getString(6)
											+ "," + rs.getString(7)+ "," + rs.getString(8));
									}
								}
								 // 关闭连接
								JOptionPane.showMessageDialog(btnSelect, "保存选择成功！","提示",
										JOptionPane.INFORMATION_MESSAGE);
								DBUtil.closeConnection(conn);
							} catch (Exception h) {
								h.printStackTrace();
								JOptionPane.showMessageDialog(btnGroup, "保存选择失败！","提示",
										JOptionPane.INFORMATION_MESSAGE);
							}
							
						}
					});	
		    btnReturn=new JButton("返回");
		    btnReturn.setFont(FontDemo.font);
		    btnReturn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					prwel.setVisible(true);
					pr1.setVisible(false);
					pr2.setVisible(false);
					pr3.setVisible(false);
		 	    	pr4.setVisible(false);
					container.add(prwel,BorderLayout.CENTER);
					
				}
			});	
		    prd.add(btnSelect);
		    prd.add(btnReturn);
		    pr1.add(prd, BorderLayout.SOUTH);
		    ImageIcon icpr1 = new ImageIcon(path+"标签-所有.jpg");
		    JLabel lpr1=new JLabel(icpr1);
		    pr1.add(lpr1,BorderLayout.NORTH);
	  }
	private void initPr2(){
		try {
			// 通过工具类获取数据库连接
			Connection conn = DBUtil.getConnection();
			System.out.println("连接成功！");
//			查看已选
			PreparedStatement pstmt2 = conn.prepareStatement(selectSql2);
			ResultSet rs2 = pstmt2.executeQuery();
			System.out.println(" 操作后查询成功！");
			ResultSetMetaData rsmd2 = rs2.getMetaData();
			while (rs2.next()) {
				Vector<Object> currentRow2 = new Vector<Object>();
				if(rs2.getObject(1)!=null&&rs2.getInt(9)==1)
				{
					System.out.println("行 " + rs2.getRow() + ":" + rs2.getString(1)
							+ "," + rs2.getString(2) + "," + rs2.getString(3) + ","
							+ rs2.getString(4) + "," + rs2.getString(5)+ "," + rs2.getString(6)
							+ "," + rs2.getString(7)+ "," + rs2.getString(8)+ "," + rs2.getString(9));
					for(int i=1;i<rsmd2.getColumnCount();i++)
			    	{
			    		currentRow2.addElement(rs2.getObject(i));
			    	}
//					rows2.clear();
					rows2.addElement(currentRow2);
				}
		    	
			}
			vCheckSelected.addAll(Arrays.asList(sCheckSelected));
			 // 关闭连接
			DBUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    pr2 = new JPanel();
	    //AB
	    pr2.setLayout(new BorderLayout());
	    pr2.setBackground(Color.WHITE);
	    /*表 begin*/
	    tabCheckselected=new JTable(new ComboModel(rows2,vCheckSelected));
//	          初始化下拉列表框
	    TableColumn d2=tabCheckselected.getColumn("小组编号");
	    DefaultCellEditor dce2=new DefaultCellEditor(boxZuhao);
	    d2.setCellEditor(dce2);
	    tabCheckselected.setPreferredScrollableViewportSize(new Dimension(550,30));	
	    changeRow2.clear();
	    tabCheckselected.getModel().addTableModelListener(new TableModelListener()
				{
					@Override
					public void tableChanged(TableModelEvent e)
					{
						System.out.println(e.getColumn());
						int tr = e.getFirstRow();
						Integer middle[] = new Integer[2];
						middle[0] = tr;
						middle[1] = Integer.parseInt(tabCheckselected.getValueAt(tr, 0).toString());
						changeRow2.add(middle);
					}
				});
	    rscrollPane2 = new JScrollPane(tabCheckselected);  
	    pr2.add(rscrollPane2, BorderLayout.CENTER);
	    /*表 end*/
	    /*下面放按钮 begin*/
	    prd=new JPanel();
	    prd.setBackground(Color.white);
	    prd.setLayout(new FlowLayout());
	    btnGroup=new JButton("保存分组");
	    btnGroup.setFont(FontDemo.font);
	    btnGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				遍历JTable是否选择列
				try {
					// 通过工具类获取数据库连接
					Connection conn = DBUtil.getConnection();
					System.out.println("连接成功！");
					
					PreparedStatement pstmt = conn.prepareStatement(updateSql2);
					Iterator<Integer[]> iter=changeRow2.iterator();
					
					
					while(iter.hasNext())
					{
						Integer[] a = (Integer[]) iter.next();
						pstmt.setInt(1,(Integer) tabCheckselected.getValueAt(a[0], 7));
						pstmt.setInt(2, a[1]);
						int count = pstmt.executeUpdate();
						System.out.println(count);
					}
					pstmt = conn.prepareStatement(selectSql2);
					ResultSet rs = pstmt.executeQuery();
					System.out.println(" 操作后查询成功！");
					while (rs.next()) {
						if(rs.getObject(1)!=null&&rs.getInt(9)==1)
						{
							System.out.println("行 " + rs.getRow() + ":" + rs.getString(1)
									+ "," + rs.getString(2) + "," + rs.getString(3) + ","
									+ rs.getString(4) + "," + rs.getString(5)+ "," + rs.getString(6)
									+ "," + rs.getString(7)+ "," + rs.getString(8)+ "," + rs.getString(9));
						}
					}
					 // 关闭连接
					JOptionPane.showMessageDialog(btnGroup, "保存分组成功！","提示",
							JOptionPane.INFORMATION_MESSAGE);
					DBUtil.closeConnection(conn);
				} catch (Exception h) {
					h.printStackTrace();
					JOptionPane.showMessageDialog(btnGroup, "保存分组失败！","提示",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
	    btnReturn=new JButton("返回");
	    btnReturn.setFont(FontDemo.font);
	    btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				prwel.setVisible(true);
				pr1.setVisible(false);
				pr2.setVisible(false);
				pr3.setVisible(false);
	 	    	pr4.setVisible(false);
				container.add(prwel,BorderLayout.CENTER);
				
			}
		});
	    prd.add(btnGroup);
	    prd.add(btnReturn);
	    pr2.add(prd, BorderLayout.SOUTH);
	    /*下面放按钮 end*/	    
	    /*上面放图片标签 begin*/
	    ImageIcon icpr2 = new ImageIcon(path+"标签-已选.jpg");
	    JLabel lpr2=new JLabel(icpr2);
	    pr2.add(lpr2,BorderLayout.NORTH);
	    /*上面放图片标签 end*/
	}
	private void initPr3(){
		try {
			// 通过工具类获取数据库连接
			Connection conn = DBUtil.getConnection();
			System.out.println("连接成功！");
//			查看所有
			PreparedStatement pstmt3 = conn.prepareStatement(selectSql3);
			ResultSet rs3 = pstmt3.executeQuery();
			System.out.println(" 操作后查询成功！");
			ResultSetMetaData rsmd3 = rs3.getMetaData();
			while (rs3.next()) {
				Vector<Object> currentRow = new Vector<Object>();
				if(rs3.getObject(1)!=null&&rs3.getObject(2)!=null&&rs3.getInt(10)==1)
				{
					System.out.println("行 " + rs3.getRow() + ":" + rs3.getString(1)
							+ "," + rs3.getString(2) + "," + rs3.getString(3) + ","
							+ rs3.getString(4) + "," + rs3.getString(5)+ "," + rs3.getString(6)
							+ "," + rs3.getString(7)+ "," + rs3.getString(8)+ "," 
							+ rs3.getString(9)+ "," + rs3.getString(10));
					for(int i=1;i<rsmd3.getColumnCount();i++)
			    	{
			    		currentRow.addElement(rs3.getObject(i));
			    	}
					rows3.addElement(currentRow);
				}
		    	
			}
			vCheckEvaluate.addAll(Arrays.asList(sCheckEvaluate));
			
			 // 关闭连接
			DBUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		pr3 = new JPanel();
	    pr3.setLayout(new BorderLayout());
	    pr3.setBackground(Color.white);
	    /*表 begin*/
	    tabCheckEvaluate=new JTable(rows3,vCheckEvaluate);
	    tabCheckEvaluate.setEnabled(false);
	    tabCheckEvaluate.setPreferredScrollableViewportSize(new Dimension(550,30));
	    rscrollPane3 = new JScrollPane(tabCheckEvaluate);
	    pr3.add(rscrollPane3, BorderLayout.CENTER);
	    /*表 end*/
	    /*下面放按钮 begin*/
	    prd=new JPanel();
	    prd.setBackground(Color.white);
	    prd.setLayout(new FlowLayout());
	    btnReturn=new JButton("返回");
	    btnReturn.setFont(FontDemo.font);
	    btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				prwel.setVisible(true);
				pr1.setVisible(false);
				pr2.setVisible(false);
				pr3.setVisible(false);
	 	    	pr4.setVisible(false);
				container.add(prwel,BorderLayout.CENTER);
				
			}
		});
	    prd.add(btnReturn);
	    pr3.add(prd, BorderLayout.SOUTH);
	    /*下面放按钮 end*/
	    /*上面放图片标签 begin*/
	    ImageIcon icpr3 = new ImageIcon(path+"标签-已评.jpg");
	    JLabel lpr3=new JLabel(icpr3);
	    pr3.add(lpr3,BorderLayout.NORTH);
	    /*上面放图片标签 end*/
	}
	private void initPr4(){
		
		try {
			// 通过工具类获取数据库连接
			Connection conn = DBUtil.getConnection();
			System.out.println("连接成功！");
//			提交评价
			PreparedStatement pstmt4 = conn.prepareStatement(selectSql4);
			ResultSet rs4 = pstmt4.executeQuery();
			System.out.println(" 操作后查询成功！");
			ResultSetMetaData rsmd4 = rs4.getMetaData();
			while (rs4.next()) {
				Vector<Object> currentRow4 = new Vector<Object>();
				if(rs4.getObject(1)!=null&&rs4.getInt(4)==1)
				{
					System.out.println("行 " + rs4.getRow() + ":" + rs4.getString(1)
							+ "," + rs4.getString(2) + "," + rs4.getString(3) + ","
							+ rs4.getString(4));
					for(int i=1;i<=rsmd4.getColumnCount();i++)
			    	{
			    		currentRow4.addElement(rs4.getObject(i));
			    	}
					rows4.addElement(currentRow4);
				}
		    	
			}
			vSubmitEvaluate.addAll(Arrays.asList(sSubmitEvaluate));
			 // 关闭连接
			DBUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    pr4 = new JPanel();
	    pr4.setLayout(new BorderLayout());
	    pr4.setBackground(Color.white);
	    /*表 begin*/
	    tabSubmitEvaluate=new JTable(new ComboModel(rows4,vSubmitEvaluate));
//        初始化下拉列表框
	    TableColumn d4=tabSubmitEvaluate.getColumn("小组评价");
	    DefaultCellEditor dce4=new DefaultCellEditor(boxPingjia);
	    d4.setCellEditor(dce4);
	    tabSubmitEvaluate.setPreferredScrollableViewportSize(new Dimension(550,30));	
	    changeRow4.clear();
	    tabSubmitEvaluate.getModel().addTableModelListener(new TableModelListener()
				{
					@Override
					public void tableChanged(TableModelEvent e)
					{
						System.out.println(e.getColumn());
						int tr = e.getFirstRow();
						Integer middle[] = new Integer[2];
						middle[0] = tr;
						middle[1] = Integer.parseInt(tabSubmitEvaluate.getValueAt(tr, 0).toString());
						changeRow4.add(middle);
					}
				});
	    rscrollPane4 = new JScrollPane(tabSubmitEvaluate);  
	    pr4.add(rscrollPane4, BorderLayout.CENTER);
	    /*表 end*/
	    /*下面放按钮 begin*/
	    prd=new JPanel();
	    prd.setBackground(Color.white);
	    prd.setLayout(new FlowLayout());
	    btnEvaluate=new JButton("保存评价");
	    btnEvaluate.setFont(FontDemo.font);
	    btnEvaluate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				遍历JTable是否选择列
				try {
					// 通过工具类获取数据库连接
					Connection conn = DBUtil.getConnection();
					System.out.println("连接成功！");
					PreparedStatement pstmt = conn.prepareStatement(updateSql4);
					Iterator<Integer[]> iter=changeRow4.iterator();
					
					while(iter.hasNext())
					{
						Integer[] a = (Integer[]) iter.next();
						pstmt.setInt(1,(Integer) tabSubmitEvaluate.getValueAt(a[0], 2));
						pstmt.setInt(2, a[1]);
						int count = pstmt.executeUpdate();
						System.out.println(count);
					}
					pstmt = conn.prepareStatement(selectSql4);
					ResultSet rs = pstmt.executeQuery();
					System.out.println(" 操作后查询成功！");
					while (rs.next()) {
						if(rs.getObject(1)!=null&&rs.getInt(4)==1)
						{
							System.out.println("行 " + rs.getRow() + ":" + rs.getString(1)
									+ "," + rs.getString(2) + "," + rs.getString(3) + ","
									+ rs.getString(4));
						}
					}
					 // 关闭连接
					JOptionPane.showMessageDialog(btnGroup, "保存评价成功！","提示",
							JOptionPane.INFORMATION_MESSAGE);
					DBUtil.closeConnection(conn);
				} catch (Exception h) {
					h.printStackTrace();
					JOptionPane.showMessageDialog(btnGroup, "保存评价失败！","提示",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
	    btnReturn=new JButton("返回");
	    btnReturn.setFont(FontDemo.font);
	    btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				prwel.setVisible(true);
				pr1.setVisible(false);
				pr2.setVisible(false);
				pr3.setVisible(false);
	 	    	pr4.setVisible(false);
				container.add(prwel,BorderLayout.CENTER);
				
			}
		});
	    prd.add(btnEvaluate);
	    prd.add(btnReturn);
	    pr4.add(prd, BorderLayout.SOUTH);
	    /*下面放按钮 end*/
	    /*上面放图片标签 begin*/
	    ImageIcon icpr4 = new ImageIcon(path+"标签-评价.jpg");
	    JLabel lpr4=new JLabel(icpr4);
	    pr4.add(lpr4,BorderLayout.NORTH);
	    /*上面放图片标签 end*/
	}
	public Tender(){
		super("招标商 - "+StaticInfo.USER_NICKNAME);
		container = getContentPane();
	    p = new JPanel();
	    p.setLayout(new GridLayout(4, 1));
	    p.setBackground(Color.white);
//	    final String path=System.getProperty("user.dir")+"\\src\\ztb\\images\\";
	    
	    /*btnCheckall 查看所有专家信息 begin*/
	    ImageIcon bca = new ImageIcon(path+"查看所有.jpg");
	    btnCheckall = new JButton(bca);
	    /*该段代码将 图片充满整个按钮 begin*/
	    btnCheckall.setOpaque(false);  
	    btnCheckall.setContentAreaFilled(false);  
	    btnCheckall.setMargin(new Insets(0, 0, 0, 0));  
	    btnCheckall.setFocusPainted(false);  
	    btnCheckall.setBorderPainted(false);  
	    btnCheckall.setBorder(null); 
	    btnCheckall.setCursor(cs);
	    /*该段代码将 图片充满整个按钮 end*/
	    /*设置按下鼠标时图片 begin*/
	    Icon icon1=new ImageIcon(path+"查看所有.gif") ;
	    btnCheckall.setPressedIcon(icon1); //设置按钮图标
	    /*设置按下鼠标时图片 end*/
	    btnCheckall.addActionListener(this);
	    /*btnCheckall 查看所有专家信息 end*/
	   
	    /*btnCheckselected 查看已选专家信息 begin*/
	    ImageIcon bcs = new ImageIcon(path+"查看已选.jpg");
	    btnCheckselected = new JButton(bcs);
	    btnCheckselected.setOpaque(false);  
	    btnCheckselected.setContentAreaFilled(false);  
	    btnCheckselected.setMargin(new Insets(0, 0, 0, 0));  
	    btnCheckselected.setFocusPainted(false);  
	    btnCheckselected.setBorderPainted(false);  
	    btnCheckselected.setBorder(null); 
	    btnCheckselected.setCursor(cs);
	    /*设置按下鼠标时图片 begin*/
	    Icon icon2=new ImageIcon(path+"查看已选.gif") ;
	    btnCheckselected.setPressedIcon(icon2); //设置按钮图标
	    /*设置按下鼠标时图片 end*/
	    btnCheckselected.addActionListener(this);
	    /*btnCheckselected 查看已选专家信息 end*/
	   
	    /*btnCheckEvaluate 查看评价 begin*/
	    ImageIcon bce = new ImageIcon(path+"查看评价.jpg");
	    btnCheckEvaluate = new JButton(bce);
	    btnCheckEvaluate.setOpaque(false);  
	    btnCheckEvaluate.setContentAreaFilled(false);  
	    btnCheckEvaluate.setMargin(new Insets(0, 0, 0, 0));  
	    btnCheckEvaluate.setFocusPainted(false);  
	    btnCheckEvaluate.setBorderPainted(false);  
	    btnCheckEvaluate.setBorder(null); 
	    btnCheckEvaluate.setCursor(cs);
	    /*设置按下鼠标时图片 begin*/
	    Icon icon3=new ImageIcon(path+"查看评价.gif") ;
	    btnCheckEvaluate.setPressedIcon(icon3); //设置按钮图标
	    /*设置按下鼠标时图片 end*/
	    btnCheckEvaluate.addActionListener(this);
	    /*btnCheckEvaluate 查看评价 end*/
	    
	    /*btnSubmitEvaluate 提交评价 begin*/
	    ImageIcon bse = new ImageIcon(path+"提交评价.jpg");
	    btnSubmitEvaluate = new JButton(bse);
	    btnSubmitEvaluate.setOpaque(false);  
	    btnSubmitEvaluate.setContentAreaFilled(false);  
	    btnSubmitEvaluate.setMargin(new Insets(0, 0, 0, 0));  
	    btnSubmitEvaluate.setFocusPainted(false);  
	    btnSubmitEvaluate.setBorderPainted(false);  
	    btnSubmitEvaluate.setBorder(null); 
	    btnSubmitEvaluate.setCursor(cs);
	    /*设置按下鼠标时图片 begin*/
	    Icon icon4=new ImageIcon(path+"提交评价.gif") ;
	    btnSubmitEvaluate.setPressedIcon(icon4); //设置按钮图标
	    /*设置按下鼠标时图片 end*/
	    btnSubmitEvaluate.addActionListener(this);
	    /*btnSubmitEvaluate 提交评价 end */
	    
	    p.add(btnCheckall);
	    p.add(btnCheckselected);
	    p.add(btnCheckEvaluate);
	    p.add(btnSubmitEvaluate);
	    lscrollPane = new JScrollPane(p);
	    container.add(lscrollPane, BorderLayout.WEST);
	    
	    /*prwel 欢迎界面 begin*/
	    prwel = new  MyPanel(path+"招标商欢迎界面.jpg");
	    prwel.setBackground(Color.white);
	    /*prwel end*/
	 
	    
	    /*pr1 查看所有专家信息 begin*/
	    initPr1();
	    /*pr1 end*/
	    
	    /*pr2 查看已选专家信息 begin*/
	    initPr2();
	    /*pr2 end*/
	    
	    /*pr3 查看专家评价 begin*/
	    initPr3();
	    /*pr3 end*/
	    
	    /*pr4 提交专家评价 begin*/
	    initPr4();
	    /*pr4 end*/
	    
	    container.add(prwel, BorderLayout.CENTER);
	    
	    /*菜单 begin*/
	    menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    fileMenu = new JMenu("文件(F)");
	    fileMenu.setMnemonic('F'); //设置菜单快捷键
	    menuBar.add(fileMenu);
	    exitItem = new JMenuItem("退出 (E)");
	    exitItem.setMnemonic('E'); //设置菜单项快捷键
	    exitItem.addActionListener( //响应菜单项事件
	        new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	        System.exit(0);
	      }
	    }
	    );
	    fileMenu.add(exitItem);
	    helpMenu = new JMenu("帮助(H)");
	    helpMenu.setMnemonic('H'); //设置菜单快捷键
	    helpMenuItem = new JMenuItem("关于 (A)");
	    helpMenuItem.setMnemonic('A'); //设置菜单项快捷键
	    helpMenuItem.addActionListener( //响应菜单项事件
	        new ActionListener() {
	      public void actionPerformed(ActionEvent event) {
	        JOptionPane.showMessageDialog(null, "这是招标商界面！");
	      }
	    }
	    );
	    helpMenu.add(helpMenuItem);
	    menuBar.add(helpMenu);
	    /*菜单 end*/
	    
	    this.setSize(880,550);
	    this.setLocation(300, 100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {//按钮事件
		// TODO Auto-generated method stub
		if(e.getSource()==btnCheckall){
			this.refreshPr1();
			tabCheckAll=new JTable(new ComboModel(rows1,vCheckAll));
			rscrollPane1 = new JScrollPane(tabCheckAll); 
		    prwel.setVisible(false);
			pr1.setVisible(true);
			pr2.setVisible(false);
 	    	pr3.setVisible(false);
 	    	pr4.setVisible(false);
			container.add(pr1, BorderLayout.CENTER);		
		}
		if(e.getSource()==btnCheckselected){
			this.refreshPr2();
			tabCheckselected=new JTable(new ComboModel(rows2,vCheckSelected));
			rscrollPane2 = new JScrollPane(tabCheckselected); 
			prwel.setVisible(false);
			pr1.setVisible(false);
			pr2.setVisible(true);
			pr3.setVisible(false);
 	    	pr4.setVisible(false);
			container.add(pr2,BorderLayout.CENTER);
		}
		if(e.getSource()==btnCheckEvaluate){
			this.refreshPr3();
			tabCheckEvaluate=new JTable(new ComboModel(rows3,vCheckEvaluate));
			rscrollPane3 = new JScrollPane(tabCheckEvaluate); 
			prwel.setVisible(false);
			pr1.setVisible(false);
			pr2.setVisible(false);
			pr3.setVisible(true);
 	    	pr4.setVisible(false);
			container.add(pr3,BorderLayout.CENTER);
		}
		if(e.getSource()==btnSubmitEvaluate){
			this.refreshPr4();
			tabSubmitEvaluate=new JTable(new ComboModel(rows4,vSubmitEvaluate));
			rscrollPane4 = new JScrollPane(tabSubmitEvaluate); 
			prwel.setVisible(false);
			pr1.setVisible(false);
			pr2.setVisible(false);
			pr3.setVisible(false);
 	    	pr4.setVisible(true);
			container.add(pr4,BorderLayout.CENTER);
		}
		if(e.getSource()==btnReturn){
			prwel.setVisible(true);
			pr1.setVisible(false);
			pr2.setVisible(false);
			pr3.setVisible(false);
 	    	pr4.setVisible(false);
			container.add(prwel,BorderLayout.CENTER);
		}
	}
	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Tender td=new Tender();
//		td.setVisible(true);
//	}
}

