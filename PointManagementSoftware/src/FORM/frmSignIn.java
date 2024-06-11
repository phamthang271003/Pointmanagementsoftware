package FORM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import BUS.SignInBUS;
import DAO.DataProvider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmSignIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTK;
	private JTextField txtMK;
	private SignInBUS signInBUS;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frmSignIn frame = new frmSignIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public frmSignIn() {
		DataProvider.getInstance().connectToDatabase();
		this.signInBUS=new SignInBUS();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng nhập");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(263, 40, 170, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tài khoản:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(66, 111, 100, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mật khẩu:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(66, 167, 100, 30);
		contentPane.add(lblNewLabel_2);
		
		txtTK = new JTextField();
		txtTK.setBounds(176, 117, 310, 19);
		contentPane.add(txtTK);
		txtTK.setColumns(10);
		
		txtMK = new JTextField();
		txtMK.setBounds(176, 173, 310, 19);
		contentPane.add(txtMK);
		txtMK.setColumns(10);
		
		//Sự kiện nút đăng nhập
		JButton btnSignIn = new JButton("Đăng nhập");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int checkAccount=signInBUS.checkAccount(getTxtTK(), getTxtMK());
				
				 if (checkAccount == 1) {
	                    JOptionPane.showMessageDialog(frmSignIn.this, "Đăng nhập thành công");
	                    int checkRole=signInBUS.checkRoleAccount(getTxtTK());
	                    if(checkRole==1)
	                    {
	                    	 JOptionPane.showMessageDialog(frmSignIn.this, "Chào mừng bạn Admin");
	                    	 dispose();
	 	                    MainForm frame1 = new MainForm();
	 	                    frame1.setVisible(true);
	                    }
	                    else
	                    {
	                    	JOptionPane.showMessageDialog(frmSignIn.this, "Chào mừng bạn giáo viên");
	                    }
	                    
	                } else {
	                    JOptionPane.showMessageDialog(frmSignIn.this, "Đăng nhập thất bại");
	                }
			}
		});
		btnSignIn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnSignIn.setBounds(249, 230, 154, 49);
		contentPane.add(btnSignIn);
	}
	
	public String getTxtTK()
	{
		return txtTK.getText();
	}
	
	public String getTxtMK()
	{
		return txtMK.getText();
	}
}
