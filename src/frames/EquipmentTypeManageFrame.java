package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import controllers.EquipmentTypeController;
import dialog.EditEquipmentTypeDialog;
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
		setBounds(100, 100, 611, 302);
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
		equipmentTypeModel.addColumn("类别名称");
		equipmentTypeModel.addColumn("序列号");
		equipmentTypeModel.addColumn("被引用次数");
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
		updateEquipmentTypeList();
		btnNewButton.addActionListener((e)->{
			EditEquipmentTypeDialog a=new EditEquipmentTypeDialog(EquipmentManageFrame.getInstance(), equipmentTypeController);
			a.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					updateEquipmentTypeList();

				}
			});
			
		});
		btnNewButton_2.addActionListener((e)->{
			EquipmentType aa=getChange();
			if(aa==null) {JOptionPane.showMessageDialog(null, "无选择值！");}
			else {
			EditEquipmentTypeDialog a=new EditEquipmentTypeDialog(EquipmentManageFrame.getInstance(), equipmentTypeController,aa);
			a.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					// TODO Auto-generated method stub
					updateEquipmentTypeList();
				}
			});}
		});
		
		btnNewButton_1.addActionListener((e)->{
			onDelete();
			updateEquipmentTypeList();
		});
		btnNewButton_3.addActionListener(e->{
			String name=textField.getText();
			int m=0;
			for( int i=0;i<equipmentTypeList.size();i++) {
				if(equipmentTypeList.get(i).getIsAvailable().equals("true")) {
					if(equipmentTypeList.get(i).getName().equals(name)) {
							 equipmentTypes.setRowSelectionInterval(m, m);
							 equipmentTypes.scrollRectToVisible(equipmentTypes.getCellRect(m, 0, true));
							 equipmentTypes.setSelectionBackground(Color.LIGHT_GRAY);//选中行设置背景色								

					}
					m++;
				}
			}
		});

	
	
	
	}
	
	public static void updateEquipmentTypeList_1() {
		getInstance().updateEquipmentTypeList();
	}

	public void updateEquipmentTypeList() {
		try {
			equipmentTypeList=equipmentTypeController.showEquipmentType();
			    int num = equipmentTypeModel.getRowCount(); //得到此数据表中的行数
			    equipmentTypeModel.getDataVector().clear();
			int i=1;
			for(EquipmentType eqte : equipmentTypeList) {
				if(eqte.getIsAvailable().equals("true")){
				Vector<Object> rowData=new Vector<>();
				rowData.add(i);
				rowData.add(eqte.getName());
				rowData.add(eqte.getSerialNumber());
				rowData.add(eqte.getIsQuote());
				equipmentTypeModel.addRow(rowData);
				i++; }
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private EquipmentType getChange(){
		int[] rows = equipmentTypes.getSelectedRows();
		for(int i= rows.length-1; i>=0; i--)
		{
			String s=(String)equipmentTypeModel.getValueAt(rows[i], 1);
			try {
				EquipmentType u=equipmentTypeController.searchEquipmentType(s);
				return u;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
	return null;	
	}
	private void onDelete()
	{
		// 获取选中的行的索引
		int[] rows=null;
		rows = equipmentTypes.getSelectedRows();
		System.out.println(rows.length);
		if(rows.length == 0)return;
				
		// 弹出对话框确认
		int select = JOptionPane.showConfirmDialog(this, "是否确认删除?", "确认", JOptionPane.YES_NO_OPTION);
		if(select != 0) return; // 0号按钮是'确定'按钮

		// 技巧：从后往前删除
		for(int i= rows.length-1; i>=0; i--)
		{
			String s=(String)equipmentTypes.getValueAt(rows[i], 1);
			try {
				if(equipmentTypeController.deleteEquipmentType(s)){
					JOptionPane.showMessageDialog(this, "删除成功！");
					}else{
					JOptionPane.showMessageDialog(this, "删除失败！");
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			equipmentTypeModel.removeRow(rows[i]);
			
		}
	}
	
}
