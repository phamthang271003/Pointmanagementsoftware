package FORM;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BUS.ExamSchedulesBUS;
import BUS.RegisExamBUS;
import BUS.TeachersBUS;
import BUS.UsersBUS;
import DTO.ExamSchedulesDTO;
import DTO.RegisExamDTO;
import DTO.TeachersDTO;
import DTO.UserLogin;
import DTO.UsersDTO;

public class frmDangKyCoiThi extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblLichThi;
	private JComboBox cbNamHoc;
	private JComboBox cbHocKy;
	private JComboBox cbLichThi;
	
	private ExamSchedulesBUS scheduleBUS = new ExamSchedulesBUS();
	private RegisExamBUS regisBUS = new RegisExamBUS();
	private TeachersBUS teaBUS = new TeachersBUS();

	private UsersBUS userBUS = new UsersBUS();
	private List<ExamSchedulesDTO> lstSchedule = new ArrayList<>();
	private DefaultTableModel modelDanhSachLichThi = new DefaultTableModel();
	private JTextField txtCa;
	private JTextField txtThoiLuong;
	private JTextField txtPhong;
	private JTextField txtNgayThi;
	private JTextField txtGiaoVien;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmDangKyCoiThi frame = new frmDangKyCoiThi();
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
	public frmDangKyCoiThi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(10, 11, 854, 218);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Năm học");
		lblNewLabel.setBounds(26, 28, 49, 14);
		panel.add(lblNewLabel);
		
		cbNamHoc = new JComboBox();
		
		
		cbNamHoc.setBounds(85, 24, 114, 22);
		panel.add(cbNamHoc);
		
		JLabel lblNewLabel_1 = new JLabel("Học kỳ");
		lblNewLabel_1.setBounds(238, 28, 49, 14);
		panel.add(lblNewLabel_1);
		
		cbHocKy = new JComboBox();
		
		cbHocKy.setBounds(297, 24, 193, 22);
		panel.add(cbHocKy);
		
		JLabel lblNewLabel_2 = new JLabel("Lịch thi");
		lblNewLabel_2.setBounds(26, 74, 49, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Giáo viên");
		lblNewLabel_3.setBounds(238, 74, 49, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnDangKy = new JButton("Cập nhật");
		btnDangKy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = UserLogin.user;
				UsersDTO user = userBUS.getTeacherById(username);
				String teaId = user.getTea_id();
				ExamSchedulesDTO exam = (ExamSchedulesDTO) cbLichThi.getSelectedItem();
				
				if(teaId.isEmpty() || exam == null) {
					JOptionPane.showMessageDialog(null, "Chưa chọn năm học và học kỳ!");
					return;
				}
				
				int check = regisBUS.RegisterExamSchedule(exam.getExam_id(), teaId);
				if(check != -1) {
					JOptionPane.showMessageDialog(null, "Cập nhật coi thi thành công");
				} else {
					JOptionPane.showMessageDialog(null, "Cập nhật coi thi thất bại! Giáo viên với mã là " + teaId + " đã đăng ký lịch coi thi " + exam.getExam_id());
				}

				loadDataTableDanhSachDangKy();
			}
		});
		
		btnDangKy.setBounds(528, 70, 133, 23);
		panel.add(btnDangKy);
		
		cbLichThi = new JComboBox();
		
		cbLichThi.setBounds(85, 70, 114, 22);
		panel.add(cbLichThi);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Th\u00F4ng tin l\u1ECBch thi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(26, 109, 545, 98);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Thời gian bắt đầu");
		lblNewLabel_4.setBounds(10, 24, 129, 14);
		panel_2.add(lblNewLabel_4);
		
		txtCa = new JTextField();
		txtCa.setBounds(149, 21, 96, 20);
		panel_2.add(txtCa);
		txtCa.setEnabled(false);
		txtCa.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Phòng");
		lblNewLabel_6.setBounds(10, 55, 49, 14);
		panel_2.add(lblNewLabel_6);
		
		txtPhong = new JTextField();
		txtPhong.setBounds(149, 52, 96, 20);
		panel_2.add(txtPhong);
		txtPhong.setEnabled(false);
		txtPhong.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Ngày thi");
		lblNewLabel_7.setBounds(270, 24, 49, 14);
		panel_2.add(lblNewLabel_7);
		
		txtNgayThi = new JTextField();
		txtNgayThi.setBounds(377, 21, 96, 20);
		panel_2.add(txtNgayThi);
		txtNgayThi.setEnabled(false);
		txtNgayThi.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Thời lượng");
		lblNewLabel_5.setBounds(270, 55, 97, 14);
		panel_2.add(lblNewLabel_5);
		
		txtThoiLuong = new JTextField();
		txtThoiLuong.setBounds(377, 52, 96, 20);
		panel_2.add(txtThoiLuong);
		txtThoiLuong.setEnabled(false);
		txtThoiLuong.setColumns(10);
		
		
		txtGiaoVien = new JTextField();
		txtGiaoVien.setEnabled(false);
		txtGiaoVien.setBounds(297, 71, 193, 20);
		panel.add(txtGiaoVien);
		txtGiaoVien.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(10, 240, 854, 225);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 834, 175);
		panel_1.add(scrollPane);
		
		tblLichThi = new JTable();
		scrollPane.setViewportView(tblLichThi);
		
		tblLichThi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting()) {
					int selectedRow = tblLichThi.getSelectedRow();
					if(selectedRow != -1) {
						ExamSchedulesDTO exam = scheduleBUS.getExamScheduleById(tblLichThi.getValueAt(selectedRow, 0).toString());
						if(exam == null) {
							return;
						}
						txtCa.setText(exam.getExam_start());
						txtNgayThi.setText(exam.getExam_date().toString());
						txtPhong.setText(exam.getRoom_id());
						txtThoiLuong.setText(exam.getExam_time());
					}
				}
			}
		});
		
		init();
		cbHocKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSelected();
			}
		});
		cbNamHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSelected();
			}
		});
		
		cbLichThi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExamSchedulesDTO exam = (ExamSchedulesDTO) cbLichThi.getSelectedItem();
				if(exam == null) {
					return;
				}
				txtCa.setText(exam.getExam_start());
				txtNgayThi.setText(exam.getExam_date().toString());
				txtPhong.setText(exam.getRoom_id());
				txtThoiLuong.setText(exam.getExam_time());
			}
		});
	}
	
	private void init() {
		// TODO Auto-generated method stub
		loadColumnLichThi();
		lstSchedule = scheduleBUS.getAll();
		
		List<String> dataHocKy = new ArrayList<>();
		List<String> dataNamHoc = new ArrayList<>();
		for(ExamSchedulesDTO exam : lstSchedule) {
			if(!dataHocKy.contains(exam.getSemester())) {
				dataHocKy.add(exam.getSemester());
			}
			
			if(!dataNamHoc.contains(exam.getExam_year())) {
				dataNamHoc.add(exam.getExam_year());
			}
		}
		
		for(String hocKy : dataHocKy) {
			cbHocKy.addItem(hocKy);
		}
		
		for(String namHoc : dataNamHoc) {
			cbNamHoc.addItem(namHoc);
		}

		String username = UserLogin.user;
		UsersDTO user = userBUS.getTeacherById(username);
		if(user != null) {
			String teaId = user.getTea_id();
			TeachersDTO teacher = teaBUS.getTeacherById(teaId);
			if(teacher != null) {
				txtGiaoVien.setText(teacher.getTea_name());
			}else {
				txtGiaoVien.setText("");
			}
		}else {
			txtGiaoVien.setText("");
		}
		
		loadDataTableDanhSachDangKy();
	}

	private void loadColumnLichThi() {
		modelDanhSachLichThi.addColumn("Lịch thi");
		modelDanhSachLichThi.addColumn("Giảng viên");
	}
	
	void loadSelected() {
		String hocKy = cbHocKy.getSelectedItem().toString();
		String namHoc = cbNamHoc.getSelectedItem().toString();
		loadDataLichThi(hocKy, namHoc);
	}

	void loadDataLichThi(String semester, String year) {
		cbLichThi.removeAllItems();
		List<ExamSchedulesDTO> lstExams = scheduleBUS.getExamSchedulesBySemesterAndYear(semester, year);
		for(ExamSchedulesDTO exam : lstExams) {
			cbLichThi.addItem(exam);
		}
	}
	private void loadDataTableDanhSachDangKy() {
		// TODO Auto-generated method stub
		modelDanhSachLichThi.setRowCount(0);
		
		List<RegisExamDTO> lstRegis = regisBUS.getAll();
		
		for (RegisExamDTO regis : lstRegis) {
			TeachersDTO teacher = teaBUS.getTeacherById(regis.getTea_id());
			
			modelDanhSachLichThi.addRow(new Object[] {regis.getExam_id(), teacher.getTea_name()});
		}
		tblLichThi.setModel(modelDanhSachLichThi);
	}
}
