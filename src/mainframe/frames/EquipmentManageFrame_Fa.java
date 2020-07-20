package mainframe.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.EquipmentController;
import controllers.EquipmentTypeController;
import entity.Equipment;
import entity.EquipmentType;
import mainframe.Login;
import mainframe.SyperAdmin;
import mainframe.dialog.EditEquipmentDialog;
import mainframe.dialog.EditEquipmentDialog_Fa;
import utils.SmallTool;

public class EquipmentManageFrame_Fa extends JFrame {
	public static String userID;
	public static void setUserID(String a) {
		if(SmallTool.checkUserID(a)) {
			userID=a;
		}
	}
	 private static EquipmentManageFrame_Fa instance;
	 public static EquipmentManageFrame_Fa getInstance() {  
  if (instance == null) {  
      instance = new EquipmentManageFrame_Fa();  
  }  
  return instance; 
  } 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EquipmentManageFrame_Fa.setUserID("wwx");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquipmentManageFrame_Fa frame =EquipmentManageFrame_Fa.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 private String[] tableHead=new String[] {"设备序号","设备编号","设备名称","设备类型","设备规格","设备描述","设备状态","设备来源","所属工厂"};
	private DefaultTableModel equipmentmodel=new DefaultTableModel();			private JTable equipments=new JTable(equipmentmodel){
			public boolean isCellEditable(int rowIndex, int ColIndex){
			     return false;
				}
	   }; 
		private JPanel contentPane;
		private JScrollPane equipmentScroll;
		private List<Equipment> equipmentList=null;
		private EquipmentController equipmentController=new EquipmentController("EquipmentService");
		private JButton btnNewButton;
		private JButton btnNewButton_1;
		private JToolBar toolBar ;
		private JButton btnNewButton_2;
		private JButton btnNewButton_3;
		private JTextField textField; 
		private JButton btnNewButton_4;
	/**
	 * Create the frame.
	 */
	public EquipmentManageFrame_Fa() {
		setTitle(userID+"的"+"设备管理—工厂");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//表格设置
		equipmentScroll=new JScrollPane(equipments);
		equipments.setFillsViewportHeight(true);
		equipments.setRowSelectionAllowed(true);
		contentPane.add(equipmentScroll,BorderLayout.CENTER);
		equipments.setRowHeight(30);
		equipments.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
		toolBar = new JToolBar();
		contentPane.add(toolBar,BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
		btnNewButton = new JButton("新建");		
		btnNewButton_1 = new JButton("删除");
		btnNewButton_2=new JButton("修改");
		btnNewButton_3=new JButton("检索");
		textField=new JTextField();
		textField.setToolTipText("请输入账号");
		toolBar.add(btnNewButton);
		toolBar.add(btnNewButton_1);
		toolBar.add(btnNewButton_2);
		toolBar.add(textField);
		toolBar.add(btnNewButton_3);
		btnNewButton_4 =createToolButton("返回超级管理员界面", "back.png");
		btnNewButton_4.setForeground(Color.white);
		btnNewButton_4.setBackground(new Color(0,130,228));
		contentPane.add(btnNewButton_4, BorderLayout.SOUTH);
		updateequipmentList();
		btnNewButton.addActionListener((e)->{
			try {
				List<EquipmentType> s=new EquipmentTypeController("EquipmentTypeService").showEquipmentType();
				int size=0;
			for(EquipmentType ep:s) {
				if(ep.getIsAvailable().equals("true"))size++;
			}	
			if(size==0) {
				JOptionPane.showMessageDialog(null, "无可创建的设备类型！");
			}else {
				EditEquipmentDialog_Fa a=new EditEquipmentDialog_Fa(EquipmentManageFrame_Fa.getInstance(), equipmentController,null,userID);
				a.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						updateequipmentList();
					}
				});
			}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		btnNewButton_1.addActionListener((e)->{
			int a=onDelete();
			updateequipmentList();
			if(a!=-1)equipments.setRowSelectionInterval(a,a);
		});
		btnNewButton_2.addActionListener((e)->{
			Equipment u=getChange();
			if(u==null)JOptionPane.showMessageDialog(null, "无选择值！");
			else {
				EditEquipmentDialog_Fa a=new EditEquipmentDialog_Fa(EquipmentManageFrame_Fa.getInstance(), equipmentController,null,userID);
				a.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						updateequipmentList();
					}
				});
			}
			
		});
		btnNewButton_4.addActionListener(e->{
			
			// TODO Auto-generated method stub
			Login a=Login.getInstance();
			a.setVisible(true);
			dispose();
		
		});
		setVisible(true);
		
		
	}
	public void updateequipmentList() {
		equipments.setModel(getDefaultTableModel());
	}
	public DefaultTableModel getDefaultTableModel() {
		try {
			equipmentmodel=new DefaultTableModel(null, tableHead);
			equipmentList=equipmentController.showEquipment();
			int i=1;
			for(Equipment equip:  equipmentList) {
				if(equip.getIsAvailable().equals("true")){
					if(equip.getNowBelong().equals(userID)) {
						Vector<Object> rowData=new Vector<>();
						rowData.add(i);
						rowData.add(equip.getId());
						rowData.add(equip.getName());
						rowData.add(equip.getType());
						rowData.add(equip.getSpecifications());
						rowData.add(equip.getDescription());
						rowData.add(equip.getEquiomentState());
						rowData.add(equip.getIsRent());
						rowData.add(SmallTool.userId_FactotyID(equip.getNowBelong()));
						equipmentmodel.addRow(rowData);
					}
				
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return equipmentmodel;
	}
//	public void setSelect(int a) {
//		System.out.println(a);
//		equipments.setRowSelectionInterval(a,a);
//	}
	private int onDelete()
	{
		// 获取选中的行的索引
		int[] rows = equipments.getSelectedRows();
		if(rows.length == 0)return -1;
				
		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "是否确认删除?", "确认", JOptionPane.YES_NO_OPTION);
		if(select != 0)	return rows[0]; // 0号按钮是'确定'按钮

		// 技巧：从后往前删除
		for(int i= rows.length-1; i>=0; i--)
		{
			String id=(String)equipments.getValueAt(rows[i], 1);
			try {
				if(equipmentController.deleteEquipment(id)){
					JOptionPane.showMessageDialog(this, "删除成功！");
					}else{
					JOptionPane.showMessageDialog(this, "删除失败！");
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return 0;
	}
	private Equipment getChange(){
		int[] rows = equipments.getSelectedRows();
		for(int i= rows.length-1; i>=0; i--)
		{
			String s=(String)equipmentmodel.getValueAt(rows[i], 1);
			try {
				Equipment u=equipmentController.searchEquipment(s);
				return u;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
	return null;	
	}
	private JButton createToolButton(String text, String icon)
	{
		// 图标
		String imagePath = "/images/" + icon;
		URL imageURL = getClass().getResource(imagePath);
		// 创建按钮
		JButton button = new JButton(text);
		button.setToolTipText(text);
		button.setIcon(new ImageIcon(imageURL));
		button.setFocusPainted(false);
		return button;
	}


}
