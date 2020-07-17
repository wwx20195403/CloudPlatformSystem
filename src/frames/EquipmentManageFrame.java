package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

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

import controllers.EquipmentController;
import entity.Equipment;


public class EquipmentManageFrame extends JFrame {

	 private static EquipmentManageFrame instance;
	 public static EquipmentManageFrame getInstance() {  
   if (instance == null) {  
       instance = new EquipmentManageFrame();  
   }  
   return instance;  
   } 
	private DefaultTableModel equipmentmodel=new DefaultTableModel();
	private JTable equipments=new JTable(equipmentmodel){
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquipmentManageFrame frame =EquipmentManageFrame.getInstance();
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
	public EquipmentManageFrame() {
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
		equipmentmodel.addColumn("设备序号");
		equipmentmodel.addColumn("设备编号");
		equipmentmodel.addColumn("设备名称");
		equipmentmodel.addColumn("设备类型");
		equipmentmodel.addColumn("设备规格");
		equipmentmodel.addColumn("设备描述");
		equipmentmodel.addColumn("设备状态");
		equipmentmodel.addColumn("租借状态");
		equipments.setRowHeight(30);
		equipments.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
//		updateequipmentList();
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
		updateequipmentList();
		

	}
	public void updateequipmentList() {
		try {
			equipmentList=equipmentController.showEquipment();
			    int num = equipmentmodel.getRowCount(); //得到此数据表中的行数
			    equipmentmodel.getDataVector().clear();
			int i=1;
			for(Equipment equip:  equipmentList) {
				if(equip.getIsAvailable().equals("true")){
				Vector<Object> rowData=new Vector<>();
				rowData.add(i);
				rowData.add(equip.getId());
				rowData.add(equip.getName());
				rowData.add(equip.getType());
				rowData.add(equip.getSpecifications());
				rowData.add(equip.getDescription());
				rowData.add(equip.getEquiomentState());
				rowData.add(equip.getIsRent());
				equipmentmodel.addRow(rowData);
				i++; }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
