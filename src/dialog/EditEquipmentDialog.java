package dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.EquipmentController;
import controllers.UserController;
import entity.Equipment;

import javax.swing.JLabel;
import javax.swing.JTextArea;
public class EditEquipmentDialog extends JDialog {

	private JTextField E_name;
	private JTextField E_sp;
	private JTextField textField_3;
	private JComboBox<String> comboBox;
	private JTextField E_bl;


	/**
	 * Create the dialog.
	 */
	public EditEquipmentDialog(JFrame frame,EquipmentController EquipmentController) {
		super(frame,"创建新用户",true);
		setBounds(100, 100, 352, 447);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("设备名称:");
		lblNewLabel.setBounds(34, 80, 56, 29);
		getContentPane().add(lblNewLabel);
		
		E_name = new JTextField();
		E_name.setBounds(94, 82, 191, 25);
		getContentPane().add(E_name);
		E_name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("设备规格:");
		lblNewLabel_2.setBounds(34, 188, 56, 29);
		getContentPane().add(lblNewLabel_2);
		
		E_sp = new JTextField();
		E_sp.setBounds(94, 190, 191, 25);
		getContentPane().add(E_sp);
		E_sp.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(94, 244, 191, 25);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(233, 375, 93, 23);
		getContentPane().add(btnNewButton);
		
		JLabel E_de = new JLabel("设备描述:");
		E_de.setBounds(34, 242, 56, 29);
		getContentPane().add(E_de);
		
		JLabel lblNewLabel_4 = new JLabel("设备类型:");
		lblNewLabel_4.setBounds(34, 134, 56, 29);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("创建新用户");
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_5.setBounds(128, 24, 87, 29);
		getContentPane().add(lblNewLabel_5);
		
		comboBox = new JComboBox();
		comboBox.setBounds(94, 136, 191, 25);
		comboBox.addItem("云工厂管理员");
		comboBox.addItem("经销商");
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("所属工厂:");
		lblNewLabel_1.setBounds(34, 308, 54, 15);
		getContentPane().add(lblNewLabel_1);
		
		E_bl = new JTextField();
		E_bl.setBounds(94, 303, 191, 25);
		getContentPane().add(E_bl);
		E_bl.setColumns(10);
	}
	
	public Equipment getValue()
	{
		Equipment equips = new Equipment();
		equips.setName(E_name.getText());
		equips.setSpecifications(E_sp.getText());
//		equips.set;
		return null;
	}
}
