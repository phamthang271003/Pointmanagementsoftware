package FORM;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import BUS.*;
import DTO.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class JPanelDiemMonHoc extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tblDanhSachDiemMonHoc;
	private JTextField txtCKLan1;
	private JTextField txtCKLan2;
	private JTextField txtDiemTrungBinh;
	private JTextField txtMaSoSinhVien;
	private JComboBox cbHocKi;
	private JComboBox cbNamHoc;
	private JComboBox cbMon;
	
	private StudentsBUS stuBus = new StudentsBUS();
	private SubjectsBUS subjectBus = new SubjectsBUS();
	private ResultsBUS resultBus = new ResultsBUS();
	private List<SubjectsDTO> lstMonHoc = new ArrayList<>();
	private List<ResultsDTO> lstResult = new ArrayList<>();
	private DefaultTableModel modelDanhSachKetQua = new DefaultTableModel();
	float diemLan1;
	float diemLan2;
	float diemTB;

	/**
	 * Create the panel.
	 */
	public JPanelDiemMonHoc() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(33, 30, 695, 168);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Năm học");
		lblNewLabel.setBounds(10, 26, 77, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Môn");
		lblNewLabel_1.setBounds(10, 62, 49, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Điểm");
		lblNewLabel_2.setBounds(10, 101, 49, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Cuối Kì Lần 1");
		lblNewLabel_4.setBounds(62, 104, 95, 14);
		panel.add(lblNewLabel_4);
		
		txtCKLan1 = new JTextField();
		txtCKLan1.setBounds(167, 98, 49, 20);
		panel.add(txtCKLan1);
		txtCKLan1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Cuối Kì Lần 2");
		lblNewLabel_5.setBounds(226, 101, 94, 14);
		panel.add(lblNewLabel_5);
		
		txtCKLan2 = new JTextField();
		txtCKLan2.setBounds(330, 98, 49, 20);
		panel.add(txtCKLan2);
		txtCKLan2.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("TB");
		lblNewLabel_6.setBounds(428, 104, 28, 14);
		panel.add(lblNewLabel_6);
		
		txtDiemTrungBinh = new JTextField();
		txtDiemTrungBinh.setEnabled(false);
		txtDiemTrungBinh.setBounds(466, 101, 49, 20);
		panel.add(txtDiemTrungBinh);
		txtDiemTrungBinh.setColumns(10);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String maSV = txtMaSoSinhVien.getText();
				if(maSV.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Chưa chọn sinh viên cần nhập điểm!");
					return;
				}
				
				try {
					diemLan1 = Float.parseFloat(txtCKLan1.getText());
					if(diemLan1 > 10 || diemLan1 < 0) {
						JOptionPane.showMessageDialog(null, "Điểm thi 1 không hợp lệ");
						return;
					}
					diemLan2 = Float.parseFloat(txtCKLan2.getText());
					if(diemLan2 > 10 || diemLan2 < 0) {
						JOptionPane.showMessageDialog(null, "Điểm thi 2 không hợp lệ");
						return;
					}
//					diemTB = Float.parseFloat(txtDiemTrungBinh.getText());
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng điểm!");
					return;
				}
				SubjectsDTO monHoc = (SubjectsDTO) cbMon.getSelectedItem();
				if(monHoc == null) {
					JOptionPane.showMessageDialog(null, "Chưa chọn môn học cần nhập điểm!");
					return;
				}
				
				int check = resultBus.updateScore(maSV, monHoc.getSub_id(), diemLan1, diemLan2);
				if(check != -1) {
					loadDataTableResult(monHoc.getSub_id());
					JOptionPane.showMessageDialog(null, "Cap nhat diem thanh cong");
				}
				loadDataTableResult(monHoc.getSub_id());
				txtCKLan1.setText("");
				txtCKLan2.setText("");
				txtDiemTrungBinh.setText("");
				txtMaSoSinhVien.setText("");
			}
		});
		btnCapNhat.setBounds(561, 97, 108, 23);
		panel.add(btnCapNhat);
		
		cbMon = new JComboBox();
		
		cbMon.setBounds(99, 58, 221, 22);
		panel.add(cbMon);
		
		JLabel lblNewLabel_7 = new JLabel("Mã Số Sinh Viên");
		lblNewLabel_7.setBounds(452, 62, 111, 14);
		panel.add(lblNewLabel_7);
		
		txtMaSoSinhVien = new JTextField();
		txtMaSoSinhVien.setEnabled(false);
		txtMaSoSinhVien.setBounds(573, 59, 96, 20);
		panel.add(txtMaSoSinhVien);
		txtMaSoSinhVien.setColumns(10);
		
		cbNamHoc = new JComboBox();
		
		cbNamHoc.setBounds(121, 22, 199, 22);
		panel.add(cbNamHoc);
		
		JLabel lblNewLabel_8 = new JLabel("Học Kỳ");
		lblNewLabel_8.setBounds(393, 26, 49, 14);
		panel.add(lblNewLabel_8);
		
		cbHocKi = new JComboBox();
		
		cbHocKi.setBounds(452, 22, 217, 22);
		panel.add(cbHocKi);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Danh s\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(33, 209, 695, 246);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 27, 675, 200);
		panel_1.add(scrollPane);
		
		tblDanhSachDiemMonHoc = new JTable();
		scrollPane.setViewportView(tblDanhSachDiemMonHoc);
		tblDanhSachDiemMonHoc.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 S\u1ED1 Sinh Vi\u00EAn", "T\u00EAn Sinh Vi\u00EAn", "Cu\u1ED1i K\u1EF3 L\u1EA7n 1", "Cu\u1ED1i K\u1EF3 L\u1EA7n 2", "Trung B\u00ECnh"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		tblDanhSachDiemMonHoc.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting()) {
					int selectedRow = tblDanhSachDiemMonHoc.getSelectedRow();
					if(selectedRow != -1) {
						String masv = tblDanhSachDiemMonHoc.getValueAt(selectedRow, 0).toString();
						float diemLan1 = Float.parseFloat(tblDanhSachDiemMonHoc.getValueAt(selectedRow, 2).toString());
						float diemLan2 = Float.parseFloat(tblDanhSachDiemMonHoc.getValueAt(selectedRow, 3).toString());
						float diemTB = Float.parseFloat(tblDanhSachDiemMonHoc.getValueAt(selectedRow, 4).toString());
						
						txtCKLan1.setText(String.valueOf(diemLan1));
						txtCKLan2.setText(String.valueOf(diemLan2));
						txtDiemTrungBinh.setText(String.valueOf(diemTB));
						txtMaSoSinhVien.setText(masv);
					} else {
						System.out.println("No row selected");
					}
				}
			}
		});

		init();
		cbHocKi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSubjects();
			}
		});
		cbNamHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSubjects();
			}
		});
		
		modelDanhSachKetQua.addColumn("Mã sinh viên");
		modelDanhSachKetQua.addColumn("Tên sinh viên");
		modelDanhSachKetQua.addColumn("Điểm thi");
		modelDanhSachKetQua.addColumn("Điểm thi lần 2");
		modelDanhSachKetQua.addColumn("Điểm trung bình");
		
		cbMon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubjectsDTO subject = (SubjectsDTO) cbMon.getSelectedItem();
				if(subject == null) {
					return;
				}
				loadDataTableResult(subject.getSub_id());
			}
		});
	}
	//modelDanhSachKetQua
	void init(){
		try {
			
			lstMonHoc = subjectBus.getAllSubject();
			List<String> dataHocKy = new ArrayList<>();
			List<String> dataNamHoc = new ArrayList<>();
			for(SubjectsDTO subject : lstMonHoc) {
				if(!dataHocKy.contains(subject.getSub_semester())) {
					dataHocKy.add(subject.getSub_semester());
				}
				
				if(!dataNamHoc.contains(String.valueOf(subject.getSub_year()))) {
					dataNamHoc.add(String.valueOf(subject.getSub_year()));
				}
			}
			
			for(String hocKy : dataHocKy) {
				cbHocKi.addItem(hocKy);
			}
			
			for(String namHoc : dataNamHoc) {
				cbNamHoc.addItem(namHoc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void loadSubjects() {
		lstMonHoc.clear();
		cbMon.removeAllItems();
		try {
			String hocKy = cbHocKi.getSelectedItem().toString();
			int namHoc = Integer.parseInt(cbNamHoc.getSelectedItem().toString());
			
			lstMonHoc = subjectBus.showListSubjectsBySemesterAndYear(hocKy, namHoc);
			
			for(SubjectsDTO subject : lstMonHoc) {
				cbMon.addItem(subject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void loadDataTableResult(String subId) {
		modelDanhSachKetQua.setRowCount(0);
		lstResult = resultBus.getListStudentBySubject(subId);

		for(ResultsDTO result : lstResult) {
			StudentsDTO student = stuBus.getStudentById(result.getStu_id());
			modelDanhSachKetQua.addRow(new Object[] {result.getStu_id(), student.getStu_name(), result.getRes_score(), result.getRes_score_2(), result.getAvg_score()});
		}
		tblDanhSachDiemMonHoc.setModel(modelDanhSachKetQua);
	}
}
