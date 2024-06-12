package FORM;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JFrameNhapDiem extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout layout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameNhapDiem frame = new JFrameNhapDiem();
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
	public JFrameNhapDiem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1200,600);
		//setBounds(100, 100, 928, 631);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu mnDiemTotNghiep = new JMenu("Điểm môn tốt nghiệp");
		
		JMenu mnDiemMonHoc = new JMenu("Điểm môn học");
		
		menuBar.add(mnDiemTotNghiep);
		menuBar.add(mnDiemMonHoc);
		
		mnDiemTotNghiep.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mnDiemMonHoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		contentPane = new JPanel();
		layout = new CardLayout();
		contentPane.setLayout(layout);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanelDiemMonHoc diemMonHoc = new JPanelDiemMonHoc();
		JPanelDiemMonTotNghiep diemTotNghiep = new JPanelDiemMonTotNghiep();
		
		contentPane.add(diemMonHoc, "MonHoc");
		contentPane.add(diemTotNghiep, "TotNghiep");
		

		this.setJMenuBar(menuBar);
		setContentPane(contentPane);
		
		mnDiemMonHoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layout.show(contentPane, "MonHoc");
			}
		});
		
		mnDiemTotNghiep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layout.show(contentPane, "TotNghiep");
			}
		});
	}
}
