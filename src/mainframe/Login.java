package mainframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainframe.frames.EquipmentManageFrame_Fa;
import mainframe.frames.UserManageFrame;
import utils.SmallTool;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Login extends JFrame {
	 private static Login instance;
	 public static Login getInstance() {  
    if (instance == null) {  
        instance = new Login();  
    }  
    return instance;  
    } 
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = Login.getInstance();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(53, 113, 159, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(223, 119, 93, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(e->{
			if(check()) {
				if(SmallTool.checkUserID(textField.getText())) {
					EquipmentManageFrame_Fa.setUserID(textField.getText());
					EquipmentManageFrame_Fa a=EquipmentManageFrame_Fa.getInstance();
					dispose();
				}
			}
		});
		
		
		
		setVisible(true);
	}
	
	public boolean check() {
		if(textField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "账户名不得为空!");
			return false;
		}
		else return true;
		}
	}
