package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

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

import controllers.FactoryControllers;
import controllers.UserController;
import entity.Factory;



public class FactoryManageFrame extends JFrame {

	 private static FactoryManageFrame instance;
	 public static FactoryManageFrame getInstance() {  
  if (instance == null) {  
      instance = new FactoryManageFrame();  
  }  
  return instance; 
  } 
	 
	
	private DefaultTableModel factorymodel=new DefaultTableModel();
	private JTable factorys=new JTable(factorymodel){
		public boolean isCellEditable(int rowIndex, int ColIndex){
		     return false;
			}
   }; 
	
   
   	private JPanel contentPane;
	private JScrollPane factoryScroll;
	private List<Factory> factoryList=null;
	private FactoryControllers factoryController=new FactoryControllers("FactoryService");
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JToolBar toolBar ;
	private JTextField textField;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FactoryManageFrame frame = new FactoryManageFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FactoryManageFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//表格设置
		factoryScroll=new JScrollPane(factorys);
		factorys.setFillsViewportHeight(true);
		factorys.setRowSelectionAllowed(true);
		contentPane.add(factoryScroll,BorderLayout.CENTER);
		factorymodel.addColumn("序号");
		factorymodel.addColumn("工厂名称");
		factorymodel.addColumn("工厂简介");
		factorymodel.addColumn("负责人");
		factorymodel.addColumn("联系方式");
		factorymodel.addColumn("工厂状态");
		factorymodel.addColumn("操作");
		factorys.setRowHeight(30);
		factorys.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
		toolBar = new JToolBar();
		contentPane.add(toolBar,BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
		btnNewButton = new JButton("检索");		
		btnNewButton_1 = new JButton("开启");
		btnNewButton_2=new JButton("关停");
		btnNewButton_3=new JButton("刷新");
		textField=new JTextField();
		textField.setToolTipText("请输入账号");
	
		toolBar.add(textField);
		toolBar.add(btnNewButton);
		toolBar.add(btnNewButton_1);
		toolBar.add(btnNewButton_2);
		toolBar.add(btnNewButton_3);
		updateFactoryList();
		btnNewButton_3.addActionListener(e->{
			updateFactoryList();
		});
	
	}
	
	public void updateFactoryList() {
		try {
			factoryList=factoryController.showFactory();
			    int num = factorymodel.getRowCount(); //得到此数据表中的行数
			    factorymodel.getDataVector().clear();
			int i=1;
			for(Factory fa : factoryList) {
				if(fa.getIsAvailable().equals("true")){
				Vector<Object> rowData=new Vector<>();
				rowData.add(i);
				rowData.add(fa.getName());
				rowData.add(fa.getIntroduction());
				rowData.add(fa.getUsername());
				rowData.add((new UserController("UserService")).searchUser(fa.getUserId()).getPhone());
				rowData.add(fa.getFctorystate());
				factorymodel.addRow(rowData);
				i++; }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}
