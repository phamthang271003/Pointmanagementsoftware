package FORM;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import FORM.StudentsGUI;
import FORM.Assigning_examGUI;

import javax.swing.JMenuBar;
import javax.swing.JDesktopPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

public class MainForm extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;

	public JDesktopPane desktopPane;

	public JMenu mnThongKe;
	private JMenu mnChucNang;

	public JCheckBoxMenuItem chkPhieuLapSach;
	private JCheckBoxMenuItem chkMonHoc;
	private JCheckBoxMenuItem chkLop;
	private JCheckBoxMenuItem chkPhongThi;
	private JCheckBoxMenuItem chkNganh;

	private JCheckBoxMenuItem chkKhoa;

	private JCheckBoxMenuItem chkSinhVien;

	private JCheckBoxMenuItem chkGiangVien;

	private JMenu mnQuanLy;

	private JCheckBoxMenuItem chkThoat;

	private JCheckBoxMenuItem chkDangXuat;
	private JCheckBoxMenuItem chkUser;

	private JCheckBoxMenuItem chkDangNhap;
	private JCheckBoxMenuItem chkLapLichThiTN;
	private JCheckBoxMenuItem chkHocBa;
	private JCheckBoxMenuItem chkDanhSachPhongThi;
	private JCheckBoxMenuItem chkLapLichThi;
	private JCheckBoxMenuItem chkNhapDiem;
	private JCheckBoxMenuItem chkDkCoiThi;
	private JCheckBoxMenuItem chkPCCoiThi;
	private JCheckBoxMenuItem chkThemMHSV;
	private JCheckBoxMenuItem currentCheckbox;
	private JCheckBoxMenuItem chkPhanCongChamThi;
	private JMenu mnHeThong;

	private JMenuBar menuBar;

	public MainForm() {
		this.init();
	}

	public void init() {

		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnHeThong = new JMenu("Hệ thống");
		menuBar.add(mnHeThong);

		chkDangNhap = new JCheckBoxMenuItem("Đăng nhập");
		mnHeThong.add(chkDangNhap);

		chkDangXuat = new JCheckBoxMenuItem("Đăng xuất");
		mnHeThong.add(chkDangXuat);

		chkUser = new JCheckBoxMenuItem("Thông tin User");
		mnHeThong.add(chkUser);

		chkThoat = new JCheckBoxMenuItem("Thoát");
		mnHeThong.add(chkThoat);

		mnQuanLy = new JMenu("Danh mục");
		menuBar.add(mnQuanLy);

		chkGiangVien = new JCheckBoxMenuItem("Quản lý giảng viên");
		
		mnQuanLy.add(chkGiangVien);

		chkSinhVien = new JCheckBoxMenuItem("Quản lý sinh viên");
		mnQuanLy.add(chkSinhVien);

		chkKhoa = new JCheckBoxMenuItem("Quản lý khoa");
		mnQuanLy.add(chkKhoa);

		chkNganh = new JCheckBoxMenuItem("Quản lý ngành");
		
		mnQuanLy.add(chkNganh);

		chkMonHoc = new JCheckBoxMenuItem("Quản lý môn học");
		
		mnQuanLy.add(chkMonHoc);

		chkLop = new JCheckBoxMenuItem("Quản lý lớp");
		mnQuanLy.add(chkLop);

		chkPhongThi = new JCheckBoxMenuItem("Quản lý phòng thi");
		mnQuanLy.add(chkPhongThi);

		mnChucNang = new JMenu("Chức năng");
		menuBar.add(mnChucNang);

		chkPCCoiThi = new JCheckBoxMenuItem("Phân công coi thi");
		mnChucNang.add(chkPCCoiThi);

		chkDkCoiThi = new JCheckBoxMenuItem("Đăng ký coi thi");
		mnChucNang.add(chkDkCoiThi);
		
		chkPhanCongChamThi = new JCheckBoxMenuItem("Phan Cong Cham Thi");
		mnChucNang.add(chkPhanCongChamThi);

		chkNhapDiem = new JCheckBoxMenuItem("Nhập điểm");
		mnChucNang.add(chkNhapDiem);

		chkLapLichThi = new JCheckBoxMenuItem("Lập lịch thi");
		mnChucNang.add(chkLapLichThi);

		chkLapLichThiTN = new JCheckBoxMenuItem("Lập lịch thi tốt nghiệp");
		mnChucNang.add(chkLapLichThiTN);
		
		chkThemMHSV = new JCheckBoxMenuItem("Thêm môn học cho sinh viên");
		
		mnChucNang.add(chkThemMHSV);

		mnThongKe = new JMenu("Thống kê & In ấn");
		menuBar.add(mnThongKe);

		chkDanhSachPhongThi = new JCheckBoxMenuItem("Danh sách sinh viên phòng thi");
		mnThongKe.add(chkDanhSachPhongThi);

		chkHocBa = new JCheckBoxMenuItem("Học bạ");
		mnThongKe.add(chkHocBa);

		desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane);

		chkKhoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentCheckbox != null) {
					currentCheckbox.setSelected(false);
				}
				currentCheckbox = chkKhoa;
				DepartmentForm df = new DepartmentForm();
				desktopPane.add(df);
				df.setVisible(true);
			}
		});
		chkNganh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentCheckbox != null) {
					currentCheckbox.setSelected(false);
				}
				currentCheckbox = chkKhoa;
				AcademicForm af = new AcademicForm();
				desktopPane.add(af);
				af.setVisible(true);
			}
		});
		chkMonHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentCheckbox != null) {
					currentCheckbox.setSelected(false);
				}
				currentCheckbox = chkKhoa;
				SubjectForm sf = new SubjectForm();
				desktopPane.add(sf);
				sf.setVisible(true);
			}
		});
		chkThemMHSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentCheckbox != null) {
					currentCheckbox.setSelected(false);
				}
				currentCheckbox = chkKhoa;
				ResultForm rf = new ResultForm();
				desktopPane.add(rf);
				rf.setVisible(true);
			}
		});
		chkGiangVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentCheckbox != null) {
					currentCheckbox.setSelected(false);
				}
				currentCheckbox = chkKhoa;
				frmTeacher tf = new frmTeacher();
				desktopPane.add(tf);
				tf.setVisible(true);
			}
		});
		chkSinhVien.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentCheckbox != null) {
					currentCheckbox.setSelected(false);
				}
				currentCheckbox = chkKhoa;
				StudentsGUI rf = new StudentsGUI();
				desktopPane.add(rf);
				rf.setVisible(true);
				
			}
		});
		chkLop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentCheckbox != null) {
					currentCheckbox.setSelected(false);
				}
				currentCheckbox = chkKhoa;
				ClassesGUI rf = new ClassesGUI();
				desktopPane.add(rf);
				rf.setVisible(true);
				
			}
		});
		chkPhongThi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentCheckbox != null) {
					currentCheckbox.setSelected(false);
				}
				currentCheckbox = chkKhoa;
				ExamRoomGUI rf = new ExamRoomGUI();
				desktopPane.add(rf);
				rf.setVisible(true);
				
			}
		});
		chkPhanCongChamThi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentCheckbox != null) {
					currentCheckbox.setSelected(false);
				}
				currentCheckbox = chkKhoa;
				Assigning_examGUI rf = new Assigning_examGUI();
				desktopPane.add(rf);
				rf.setVisible(true);
				
			}
		});		
	}

}
