package FORM;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BUS.ExamSchedulesBUS;
import BUS.RegisExamBUS;
import BUS.ResultsBUS;
import BUS.SubjectsBUS;
import BUS.TeachersBUS;
import DTO.ExamSchedulesDTO;
import DTO.ResultsDTO;
import DTO.*;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;

public class JFrameDangKyCoiThi extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblLichThi;
	private JComboBox cbNamHoc;
	private JComboBox cbHocKy;
	private JComboBox cbGiaoVien;
	private JComboBox cbLichThi;
	
	private ExamSchedulesBUS scheduleBUS = new ExamSchedulesBUS();
	private RegisExamBUS regisBUS = new RegisExamBUS();
	private TeachersBUS teaBUS = new TeachersBUS();
	private List<ExamSchedulesDTO> lstSchedule = new ArrayList<>();
	private DefaultTableModel modelDanhSachLichThi = new DefaultTableModel();
	private JTextField txtCa;
	private JTextField txtThoiLuong;
	private JTextField txtPhong;
	private JTextField txtNgayThi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameDangKyCoiThi frame = new JFrameDangKyCoiThi();
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
	public JFrameDangKyCoiThi() {
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
		
		
		cbNamHoc.setBounds(106, 24, 114, 22);
		panel.add(cbNamHoc);
		
		JLabel lblNewLabel_1 = new JLabel("Học kỳ");
		lblNewLabel_1.setBounds(374, 28, 49, 14);
		panel.add(lblNewLabel_1);
		
		cbHocKy = new JComboBox();
		
		cbHocKy.setBounds(472, 24, 139, 22);
		panel.add(cbHocKy);
		
		JLabel lblNewLabel_2 = new JLabel("Lịch thi");
		lblNewLabel_2.setBounds(26, 87, 49, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Giáo viên");
		lblNewLabel_3.setBounds(272, 87, 49, 14);
		panel.add(lblNewLabel_3);
		
		cbGiaoVien = new JComboBox();
		cbGiaoVien.setBounds(374, 83, 181, 22);
		panel.add(cbGiaoVien);
		
		JButton btnDangKy = new JButton("Đăng ký coi thi");
		btnDangKy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TeachersDTO teacher = (TeachersDTO) cbGiaoVien.getSelectedItem();
				ExamSchedulesDTO exam = (ExamSchedulesDTO) cbLichThi.getSelectedItem();
				
				int check = regisBUS.RegisterExamSchedule(exam.getExam_id(), teacher.getTea_id());
				if(check != -1) {
					JOptionPane.showMessageDialog(null, "Dang ky coi thi thanh cong");
				} else {
					JOptionPane.showMessageDialog(null, "Dang ky coi thi that bai");
				}

				loadDataTableDanhSachDangKy();
			}
		});
		
		btnDangKy.setBounds(641, 83, 133, 23);
		panel.add(btnDangKy);
		
		cbLichThi = new JComboBox();
		
		cbLichThi.setBounds(106, 83, 84, 22);
		panel.add(cbLichThi);
		
		JLabel lblNewLabel_4 = new JLabel("Thời gian bắt đầu");
		lblNewLabel_4.setBounds(26, 126, 88, 14);
		panel.add(lblNewLabel_4);
		
		txtCa = new JTextField();
		txtCa.setEnabled(false);
		txtCa.setBounds(124, 123, 103, 20);
		panel.add(txtCa);
		txtCa.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Thời lượng");
		lblNewLabel_5.setBounds(26, 152, 88, 14);
		panel.add(lblNewLabel_5);
		
		txtThoiLuong = new JTextField();
		txtThoiLuong.setEnabled(false);
		txtThoiLuong.setBounds(134, 154, 96, 20);
		panel.add(txtThoiLuong);
		txtThoiLuong.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Phòng");
		lblNewLabel_6.setBounds(26, 193, 49, 14);
		panel.add(lblNewLabel_6);
		
		txtPhong = new JTextField();
		txtPhong.setEnabled(false);
		txtPhong.setBounds(124, 190, 96, 20);
		panel.add(txtPhong);
		txtPhong.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Ngày thi");
		lblNewLabel_7.setBounds(261, 136, 49, 14);
		panel.add(lblNewLabel_7);
		
		txtNgayThi = new JTextField();
		txtNgayThi.setEnabled(false);
		txtNgayThi.setBounds(342, 133, 96, 20);
		panel.add(txtNgayThi);
		txtNgayThi.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(10, 240, 854, 225);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		tblLichThi = new JTable();
		tblLichThi.setBounds(10, 11, 806, 229);
		panel_1.add(tblLichThi);
		
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
		
		tblLichThi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting()) {
					int selectedRow = tblLichThi.getSelectedRow();
					if(selectedRow != -1) {
						
					}
				}
			}
		});
		
		cbLichThi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExamSchedulesDTO exam = (ExamSchedulesDTO) cbLichThi.getSelectedItem();
				
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
		loadDataGiaoVien();
		lstSchedule = scheduleBUS.getAll();
		
		for (ExamSchedulesDTO schedule : lstSchedule) {
			cbHocKy.addItem(schedule.getSemester());
			cbNamHoc.addItem(schedule.getExam_year());
		}
		
		loadDataTableDanhSachDangKy();
	}

	private void loadDataGiaoVien() {
		// TODO Auto-generated method stub
		List<TeachersDTO> lstTeacher = teaBUS.getAll();
		
		for(TeachersDTO teacher : lstTeacher) {
			cbGiaoVien.addItem(teacher);
		}
	}

	private void loadColumnLichThi() {
		// TODO Auto-generated method stub
//		modelDanhSachLichThi.addColumn("Mã lịch thi");
//		modelDanhSachLichThi.addColumn("Học kỳ");
//		modelDanhSachLichThi.addColumn("Phòng thi");
//		modelDanhSachLichThi.addColumn("Thời gian bắt đầu");
//		modelDanhSachLichThi.addColumn("Thời lượng thi");
//		modelDanhSachLichThi.addColumn("Ngày thi");
//		modelDanhSachLichThi.addColumn("Niên khóa");
		

		modelDanhSachLichThi.addColumn("Lịch thi");
		modelDanhSachLichThi.addColumn("Giảng viên");
	}
	
	void loadSelected() {
		String hocKy = cbHocKy.getSelectedItem().toString();
		String namHoc = cbNamHoc.getSelectedItem().toString();
		loadDataLichThi(hocKy, namHoc);
	}

	void loadDataLichThi(String semester, String year) {
		List<ExamSchedulesDTO> lstExams = scheduleBUS.getExamSchedulesBySemesterAndYear(semester, year);
		for(ExamSchedulesDTO exam : lstExams) {
			cbLichThi.addItem(exam);
		}
	}
	private void loadDataTableDanhSachDangKy() {
		// TODO Auto-generated method stub
//		lstSchedule.clear();
		modelDanhSachLichThi.setRowCount(0);
		
		List<RegisExamDTO> lstRegis = regisBUS.getAll();
		
		for (RegisExamDTO regis : lstRegis) {
			TeachersDTO teacher = teaBUS.getTeacherById(regis.getTea_id());
			
			modelDanhSachLichThi.addRow(new Object[] {regis.getExam_id(), teacher.getTea_name()});
		}
		tblLichThi.setModel(modelDanhSachLichThi);
	}
}
