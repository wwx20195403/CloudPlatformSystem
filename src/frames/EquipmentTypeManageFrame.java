package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.EquipmentTypeController;
import entity.EquipmentType;


public class EquipmentTypeManageFrame extends JFrame {
	 private static EquipmentTypeManageFrame instance;
	 public static EquipmentTypeManageFrame getInstance() {  
  if (instance == null) {  
      instance = new EquipmentTypeManageFrame();  
  }  
  return instance;  
  } 
	private JPanel contentPane;
	private DefaultTableModel equipmentTypeModel=new DefaultTableModel();
	private JTable equipmentTypes=new JTable(equipmentTypeModel){
		public boolean isCellEditable(int rowIndex, int ColIndex){
		     return false;
			}
   }; 
	private JScrollPane equipmentTypeScroll;
	private List<EquipmentType> equipmentTypeList=null;
	private EquipmentTypeController equipmentTypeController=new EquipmentTypeController("EquipmentTypeService");
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JToolBar toolBar ;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquipmentTypeManageFrame frame = new EquipmentTypeManageFrame();
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
	public EquipmentTypeManageFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		
		//表格设置
		equipmentTypeScroll=new JScrollPane(equipmentTypes);
		equipmentTypes.setFillsViewportHeight(true);
		equipmentTypes.setRowSelectionAllowed(true);
		contentPane.add(equipmentTypeScroll,BorderLayout.CENTER);
		equipmentTypeModel.addColumn("序号");
		equipmentTypeModel.addColumn("类别名称编号");
		equipmentTypes.setRowHeight(30);
		equipmentTypes.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
//		updateequipmentList();
		toolBar = new JToolBar();
		contentPane.add(toolBar,BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
		btnNewButton = new JButton("新建");		
		btnNewButton_1 = new JButton("删除");
		btnNewButton_2=new JButton("修改");
		btnNewButton_3=new JButton("检索");
		textField=new JTextField();
		textField.setToolTipText("请输入类别");
		toolBar.add(btnNewButton);
		toolBar.add(btnNewButton_1);
		toolBar.add(btnNewButton_2);
		toolBar.add(textField);
		toolBar.add(btnNewButton_3);
	
	
	
	
	
	
	
	
	
	}

}
