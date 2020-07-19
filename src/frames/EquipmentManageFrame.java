package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import controllers.EquipmentController;
import controllers.EquipmentTypeController;
import dialog.EditEquipmentDialog;
import dialog.EditEquipmentTypeDialog;
import entity.Equipment;
import entity.EquipmentType;


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
		equipmentmodel.addColumn("所属工厂");
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
				EditEquipmentDialog a=new EditEquipmentDialog(EquipmentManageFrame.getInstance(), equipmentController,null);
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
				EditEquipmentDialog a=new EditEquipmentDialog(EquipmentManageFrame.getInstance(), equipmentController, u);
				a.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						updateequipmentList();
					}
				});
			}
			
		});

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
				rowData.add(equip.getNowBelong());
				equipmentmodel.addRow(rowData);
				i++; }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
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
			equipmentmodel.removeRow(rows[i]);
			
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
	
}
