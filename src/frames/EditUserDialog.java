package frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.User;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Rectangle;

public class EditUserDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	
	
	// 默认是“取消"
	private boolean retValue = false;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			EditUserDialog dialog = new EditUserDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public EditUserDialog(JFrame userFrame) {
		super(userFrame,"创建新用户",true);
		setBounds(100, 100, 353, 444);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("登录账号:");
		lblNewLabel.setBounds(34, 80, 56, 29);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(94, 82, 191, 25);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("登录密码:");
		lblNewLabel_1.setBounds(34, 134, 56, 29);
		contentPanel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(94, 136, 191, 25);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("真实姓名:");
		lblNewLabel_2.setBounds(34, 188, 56, 29);
		contentPanel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(94, 190, 191, 25);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(94, 244, 191, 25);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(94, 298, 191, 25);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(234, 357, 93, 23);
		contentPanel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("联系方式:");
		lblNewLabel_3.setBounds(34, 242, 56, 29);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("用户类型:");
		lblNewLabel_4.setBounds(34, 296, 56, 29);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("创建新用户");
		lblNewLabel_5.setFont(new Font("宋体", Font.BOLD, 15));
		lblNewLabel_5.setBounds(128, 24, 87, 29);
		contentPanel.add(lblNewLabel_5);
	}
	
	public boolean exec()
	{
		// 相对owner居中显示
		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width)/2;
		int y = frmRect.y + (frmRect.height - height)/2;
		this.setBounds(x,y, width, height);
		
		// 显示窗口 ( 阻塞 ，直接对话框窗口被关闭)
		this.setVisible(true);
		
		return retValue;
	}
	
	// 获取用户的输入 
	public User getValue()
	{
		User user = new User();
		user.setId(textField.getText());
		user.setPassword(textField_1.getText());
		user.setName(textField_2.getText());
		user.setPhone(Integer.parseInt(textField_3.getText()));
		user.setType(textField_4.getText());
		return user;
	}
}
