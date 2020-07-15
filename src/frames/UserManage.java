package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.UserController;
import entity.User;

public class UserManage extends JFrame {
	private DefaultTableModel usermodel=new DefaultTableModel();
	private JTable users=new JTable(usermodel); 
	private JPanel contentPane;
	private JScrollPane userScroll;
	private List<User> userList=null;
	private UserController userController;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManage frame = new UserManage();
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
	public UserManage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//表格设置
		userScroll=new JScrollPane(users);
		users.setFillsViewportHeight(true);
		users.setRowSelectionAllowed(true);
		contentPane.add(userScroll,BorderLayout.CENTER);
		usermodel.addColumn("序号");
		usermodel.addColumn("登录账号");
		usermodel.addColumn("姓名");
		usermodel.addColumn("联系方式");
		usermodel.addColumn("类型");
		users.setRowHeight(30);
		updateUserList();
		
	}

	public void updateUserList() {
		try {
			userList=userController.showUser();
			usermodel.getDataVector().removeAllElements();
			int i=1;
			for(User user1 : userList) {
				Vector<Object> rowData=new Vector<>();
				rowData.add(i);
				rowData.add(user1.getId());
				rowData.add(user1.getName());
				rowData.add(user1.getType());
				rowData.add(user1.getType());
				usermodel.addRow(rowData);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
