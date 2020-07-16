package frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.UserController;
import entity.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EditUserDialog extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox<String> comboBox;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			EditUserDialog dialog = new EditUserDialog(null,null);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public EditUserDialog(JFrame frame,UserController userController) {
		super(frame,"创建新用户",true);
		setBounds(100, 100, 352, 433);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("登录账号:");
		lblNewLabel.setBounds(34, 80, 56, 29);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(94, 82, 191, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("登录密码:");
		lblNewLabel_1.setBounds(34, 134, 56, 29);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(94, 136, 191, 25);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("真实姓名:");
		lblNewLabel_2.setBounds(34, 188, 56, 29);
		getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(94, 190, 191, 25);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(94, 244, 191, 25);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(234, 357, 93, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("联系方式:");
		lblNewLabel_3.setBounds(34, 242, 56, 29);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("用户类型:");
		lblNewLabel_4.setBounds(34, 296, 56, 29);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("创建新用户");
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_5.setBounds(128, 24, 87, 29);
		getContentPane().add(lblNewLabel_5);
		
		comboBox = new JComboBox();
		comboBox.setBounds(94, 300, 191, 25);
		comboBox.addItem("云工厂管理员");
		comboBox.addItem("经销商");
		getContentPane().add(comboBox);
		
		btnNewButton.addActionListener( (e)->{
			if(checkValid()) {
			try {	
				boolean a=userController.addUser(getValue());
				if(!a) {
					JOptionPane.showMessageDialog(null,"账号已被注册!");
				}else {
					JOptionPane.showMessageDialog(null,"创建成功!");
					dispose();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});	
		setVisible(true);
	}

	public EditUserDialog(JFrame frame,UserController userController,User user) {
		super(frame, "", true);
		setBounds(100, 100, 352, 433);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("登录账号:");
		lblNewLabel.setBounds(34, 80, 56, 29);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField(user.getId());
		textField.setBounds(94, 82, 191, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		JLabel lblNewLabel_1 = new JLabel("登录密码:");
		lblNewLabel_1.setBounds(34, 134, 56, 29);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(94, 136, 191, 25);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("真实姓名:");
		lblNewLabel_2.setBounds(34, 188, 56, 29);
		getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(94, 190, 191, 25);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		textField_3 = new JTextField();
		textField_3.setBounds(94, 244, 191, 25);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(234, 357, 93, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("联系方式:");
		lblNewLabel_3.setBounds(34, 242, 56, 29);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("用户类型:");
		lblNewLabel_4.setBounds(34, 296, 56, 29);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("修改用户信息");
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_5.setBounds(94, 30, 145, 29);
		getContentPane().add(lblNewLabel_5);
		
		comboBox = new JComboBox();
		comboBox.setBounds(94, 300, 191, 25);
		comboBox.addItem("云工厂管理员");
		comboBox.addItem("经销商");
		if(user.getType().equals("云工厂管理员")) {
			comboBox.setSelectedIndex(0);
		}else {
			comboBox.setSelectedIndex(1);
		}
		comboBox.setEnabled(false);
		getContentPane().add(comboBox);
		
		btnNewButton.addActionListener( (e)->{
			if(checkValid()) {
			try {	
				boolean a=userController.changeUser(getValue());
				if(!a) {
					JOptionPane.showMessageDialog(null,"修改失败!");
				}else {
					JOptionPane.showMessageDialog(null,"修改成功!");
					dispose();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});	
		
		setVisible(true);  // 放在最后
	}
	// 获取用户的输入 
	public User getValue()
	{
		User user = new User();
		user.setId(textField.getText());
		user.setPassword(textField_1.getText());
		user.setName(textField_2.getText());
		user.setPhone(Integer.parseInt(textField_3.getText()));
		user.setType((String)comboBox.getSelectedItem());
		return user;
	}
	// 检查输入有效性
	public boolean checkValid()
	{
		if(textField.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "账号不得为空!");
			return false;
		}
		if(textField_1.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "密码不得为空!");
			return false;
		}
		if(textField_2.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "姓名不得为空!");
			return false;
		}

		if(textField_3.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "联系方式不得为空!");
			return false;
		}else {
			try {
			Integer.parseInt(textField_3.getText());
			
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this, "联系方式错误!");
				return false;
			}
		}
		
		
		return true;
	}
}
