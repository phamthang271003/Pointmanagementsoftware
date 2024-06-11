package FORM;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import DTO.*;
import BUS.*;
import javax.swing.JPasswordField;

public class JFrameLapLichThi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNienKhoa;
	private JTextField txtMaLichThi;
	private JTextField txtThoiLuong;
	private JTextField txtNgayThi;
	private JButton btnXoaLichThi, btnTaoLichThi;
	private SubjectsBUS subjectsBus = new SubjectsBUS();
	JComboBox comboBoxHocKi,comboBoxTenPhong,comboBoxLanThi,comboBoxCaThi, comboBoxMonThi ;
	ExamRoomBUS examBus = new ExamRoomBUS();
	SubjectExamBUS suBus = new SubjectExamBUS();
	ExamSchedulesBUS examSchedulesBUS =  new ExamSchedulesBUS();
	SubjectsBUS subjectBus = new SubjectsBUS();
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	private JTable tableLichThi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameLapLichThi frame = new JFrameLapLichThi();
					
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
	public JFrameLapLichThi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1237, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 11, 1203, 144);
		contentPane.add(panel);
		panel.setLayout(null);
		
		comboBoxMonThi = new JComboBox();
		comboBoxMonThi.setBounds(310, 13, 128, 21);
		panel.add(comboBoxMonThi);
		
		JLabel lblLp = new JLabel("Môn thi");
		lblLp.setBounds(230, 11, 71, 21);
		lblLp.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblLp);
		
		comboBoxHocKi = new JComboBox();
		comboBoxHocKi.setBounds(91, 11, 128, 21);
		panel.add(comboBoxHocKi);
		
		JLabel lblHcK = new JLabel("Học kì");
		lblHcK.setBounds(10, 12, 71, 21);
		lblHcK.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblHcK);
		
		JLabel lblSS_1 = new JLabel("Lần thi");
		lblSS_1.setBounds(462, 11, 71, 21);
		lblSS_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblSS_1);
		
		JLabel lblSPhngThi_1 = new JLabel("Tên Phòng");
		lblSPhngThi_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSPhngThi_1.setBounds(703, 11, 119, 21);
		panel.add(lblSPhngThi_1);
		
		comboBoxTenPhong = new JComboBox();
		comboBoxTenPhong.setBounds(797, 13, 128, 21);
		panel.add(comboBoxTenPhong);
		
		JLabel lblSS_2 = new JLabel("Ngày thi");
		lblSS_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSS_2.setBounds(973, 13, 71, 21);
		panel.add(lblSS_2);
		
		txtNgayThi = new JTextField();
		txtNgayThi.setColumns(10);
		txtNgayThi.setBounds(1065, 15, 128, 20);
		panel.add(txtNgayThi);
		
		comboBoxLanThi = new JComboBox();
		comboBoxLanThi.setBounds(543, 12, 128, 21);

		panel.add(comboBoxLanThi);
		
		JLabel lblCaThi = new JLabel("Ca thi");
		lblCaThi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCaThi.setBounds(325, 66, 71, 21);
		panel.add(lblCaThi);
		
		comboBoxCaThi = new JComboBox();
		comboBoxCaThi.setBounds(405, 68, 128, 21);
		panel.add(comboBoxCaThi);
		
		JLabel lblSS_2_1 = new JLabel("Thời lượng");
		lblSS_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSS_2_1.setBounds(596, 68, 92, 21);
		panel.add(lblSS_2_1);
		
		txtThoiLuong = new JTextField();
		txtThoiLuong.setBounds(687, 70, 128, 20);
		panel.add(txtThoiLuong);
		
		JLabel lblNinKho = new JLabel("Niên khoá");
		lblNinKho.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNinKho.setBounds(79, 66, 95, 21);
		panel.add(lblNinKho);
		
		txtNienKhoa = new JTextField();
		txtNienKhoa.setColumns(10);
		txtNienKhoa.setBounds(160, 67, 128, 20);
		panel.add(txtNienKhoa);
		
		JLabel lblMLchThi = new JLabel("Mã lịch thi");
		lblMLchThi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMLchThi.setBounds(859, 68, 95, 21);
		panel.add(lblMLchThi);
		
		txtMaLichThi = new JTextField();
		txtMaLichThi.setColumns(10);
		txtMaLichThi.setBounds(940, 69, 128, 20);
		panel.add(txtMaLichThi);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBounds(10, 166, 1203, 82);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		btnTaoLichThi = new JButton("Tạo lịch thi");
		btnTaoLichThi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTaoLichThi.setBounds(547, 11, 115, 60);
		panel_2.add(btnTaoLichThi);
		
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 259, 1203, 330);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		tableLichThi = new JTable();
		tableLichThi.setBounds(0, 0, 1193, 330);
		defaultTableModel.addColumn("Mã lịch thi");
		defaultTableModel.addColumn("Học kì");
		defaultTableModel.addColumn("Phòng");
		defaultTableModel.addColumn("Lần thi");
		defaultTableModel.addColumn("Thời lượng");
		defaultTableModel.addColumn("Năm học");
		defaultTableModel.addColumn("Ngày thi");
		defaultTableModel.addColumn("Môn học");
		defaultTableModel.addColumn("Thời lượng");
		panel_3.add(tableLichThi);
		loadData();
		addEvent();
		
	}
	void loadData()
	{
		loadHocKi();
		loadLanThi();
		loadMonThi();
		loadPhong();
		loadCaThi();
		loadTable();
	}
	void loadHocKi()
	{
		comboBoxHocKi.addItem("1");
		comboBoxHocKi.addItem("2");
		comboBoxHocKi.addItem("Hè");
	}
	void loadMonThi()
	{
		try {
			List<SubjectsDTO> lst = subjectsBus.getAllSubject();
			comboBoxMonThi.removeAll();
			for (SubjectsDTO s : lst)
			{
				comboBoxMonThi.addItem(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	void loadLanThi()
	{
		comboBoxLanThi.addItem("1");
		comboBoxLanThi.addItem("2");
	}
	void loadPhong()
	{
		try {
			List<ExamRoomDTO> lst = examBus.getAll();
			comboBoxTenPhong.removeAll();
			for(ExamRoomDTO e : lst)
			{
				comboBoxTenPhong.addItem(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	void loadCaThi()
	{
		comboBoxCaThi.addItem("1");
		comboBoxCaThi.addItem("2");
		comboBoxCaThi.addItem("3");
		comboBoxCaThi.addItem("4");
	}
	void addEvent()
	{
		btnTaoLichThi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ma = txtMaLichThi.getText().toString();
				SubjectsDTO s = (SubjectsDTO) comboBoxMonThi.getSelectedItem(); 
				String hocKi = comboBoxHocKi.getSelectedItem().toString();
				ExamRoomDTO tenPhong = (ExamRoomDTO) comboBoxTenPhong.getSelectedItem();
				String thoiLuong = txtThoiLuong.getText() ;
				String lanThi = comboBoxLanThi.getSelectedItem().toString();
				String nienKhoa = txtNienKhoa.getText().toString();
				String caThi = comboBoxCaThi.getSelectedItem().toString();
				String ngayThi = txtNgayThi.getText() ;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        LocalDate localDate = LocalDate.parse(ngayThi, formatter);
		        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
 				ExamSchedulesDTO exam = new ExamSchedulesDTO(ma,hocKi,tenPhong.getRoom_id(),caThi,thoiLuong,nienKhoa,sqlDate);
 				SubjectExamDTO sub = new SubjectExamDTO(ma,s.getSub_id(),Integer.parseInt(lanThi));
 				int kq = examSchedulesBUS.create(exam);
 				int qk = suBus.create(sub);
				System.out.print("kq="+kq);
				System.out.print("qk="+qk);
				loadTable();
			}
		});
	}
	void loadTable()
	{
		
		defaultTableModel.setRowCount(0);
		List<ExamSchedulesDTO> list = examSchedulesBUS.getAll();
		List<SubjectExamDTO> listSE = suBus.getAll(); 
		for(SubjectExamDTO e : listSE)
		{
			
			try {
				ExamSchedulesDTO exam = examSchedulesBUS.getExamScheduleById(e.getExam_id());
				SubjectsDTO s = subjectBus.getById(e.getSub_id());
				defaultTableModel.addRow(new Object[] {exam.getExam_id(),exam.getSemester(),exam.getRoom_id(),exam.getExam_time(),exam.getExam_start(),exam.getExam_year(),exam.getExam_date(),s.getSub_name(),e.getExam_time()});
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		tableLichThi.setModel(defaultTableModel);
	}
}