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

public class JPanelDiemMonHoc extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tblDanhSachDiemMonHoc;
	private JTextField txtCKLan1;
	private JTextField txtCKLan2;
	private JTextField txtDiemTrungBinh;
	private JTextField txtMon;
	private JTextField txtMaSoSinhVien;
	private JComboBox cbHocKi;
	private JComboBox cbNamHoc;
	private JComboBox cbMon;
	
	private SubjectsBUS subjectBus = new SubjectsBUS();
	private ResultsBUS resultBus = new ResultsBUS();
	private List<String> lstHocKy = new ArrayList<>();
	private List<String> lstNamHoc = new ArrayList<>();
	private List<SubjectsDTO> lstMonHoc = new ArrayList<>();
	private List<ResultsDTO> lstResult = new ArrayList<>();
	private DefaultTableModel modelDanhSachKetQua = new DefaultTableModel();

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
		lblNewLabel.setBounds(10, 26, 49, 14);
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
				float diemLan1 = Float.parseFloat(txtCKLan1.getText());
				float diemLan2 = Float.parseFloat(txtCKLan2.getText());
				float diemTB = Float.parseFloat(txtDiemTrungBinh.getText());
				String maMon = cbMon.getSelectedItem().toString();
				
				int check = resultBus.updateScore(maSV, maMon, diemLan1, diemLan2, diemTB);
				if(check != -1) {
					JOptionPane.showMessageDialog(null, "Cap nhat diem thanh cong");
				}
				loadDataTableResult(maMon);
			}
		});
		btnCapNhat.setBounds(592, 97, 77, 23);
		panel.add(btnCapNhat);
		
		cbMon = new JComboBox();
		
		cbMon.setBounds(62, 58, 144, 22);
		panel.add(cbMon);
		
		JButton btnChonMon = new JButton("->");
		btnChonMon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String name = subjectBus.getById(cbMon.getSelectedItem().toString()).getSub_name();
					txtMon.setText(name);
				} catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}
		});
		btnChonMon.setBounds(216, 58, 45, 23);
		panel.add(btnChonMon);
		
		txtMon = new JTextField();
		txtMon.setEnabled(false);
		txtMon.setBounds(271, 59, 96, 20);
		panel.add(txtMon);
		txtMon.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Mã Số Sinh Viên");
		lblNewLabel_7.setBounds(452, 62, 89, 14);
		panel.add(lblNewLabel_7);
		
		txtMaSoSinhVien = new JTextField();
		txtMaSoSinhVien.setEnabled(false);
		txtMaSoSinhVien.setBounds(551, 56, 96, 20);
		panel.add(txtMaSoSinhVien);
		txtMaSoSinhVien.setColumns(10);
		
		cbNamHoc = new JComboBox();
		
		cbNamHoc.setBounds(84, 22, 199, 22);
		panel.add(cbNamHoc);
		
		JLabel lblNewLabel_8 = new JLabel("Học Kỳ");
		lblNewLabel_8.setBounds(366, 26, 49, 14);
		panel.add(lblNewLabel_8);
		
		cbHocKi = new JComboBox();
		
		cbHocKi.setBounds(452, 22, 217, 22);
		panel.add(cbHocKi);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Danh s\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(33, 209, 695, 246);
		add(panel_1);
		panel_1.setLayout(null);
		
		tblDanhSachDiemMonHoc = new JTable();
		tblDanhSachDiemMonHoc.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 S\u1ED1 Sinh Vi\u00EAn", "T\u00EAn Sinh Vi\u00EAn", "Gi\u1EEFa K\u1EF3", "Cu\u1ED1i K\u1EF3 L\u1EA7n 1", "Cu\u1ED1i K\u1EF3 L\u1EA7n 2", "Trung B\u00ECnh"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblDanhSachDiemMonHoc.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tblDanhSachDiemMonHoc.setBounds(10, 35, 658, 200);
		panel_1.add(tblDanhSachDiemMonHoc);

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
		modelDanhSachKetQua.addColumn("Điểm thi");
		modelDanhSachKetQua.addColumn("Điểm thi lần 2");
		modelDanhSachKetQua.addColumn("Lần thi");
		modelDanhSachKetQua.addColumn("Điểm trung bình");
		
		cbMon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelDanhSachKetQua.setRowCount(0);
				lstResult = resultBus.getListStudentBySubject(cbMon.getSelectedItem().toString());

				for(ResultsDTO result : lstResult) {
					modelDanhSachKetQua.addRow(new Object[] {result.getStu_id(), result.getRes_score(), result.getRes_score_2(), result.getRes_time(), result.getAvg_score()});
				}
				tblDanhSachDiemMonHoc.setModel(modelDanhSachKetQua);
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
						float diemLan1 = Float.parseFloat(tblDanhSachDiemMonHoc.getValueAt(selectedRow, 1).toString());
						float diemLan2 = Float.parseFloat(tblDanhSachDiemMonHoc.getValueAt(selectedRow, 2).toString());
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
	}
	//modelDanhSachKetQua
	void init(){
		try {
			
			lstMonHoc = subjectBus.getAllSubject();
			
//			for (String hocKy : lstMonHoc.stream().map(SubjectsDTO::getSub_semester).collect(Collectors.toList())) {
//				cbHocKi.addItem(hocKy);
//			}
//			
//			for (int namHoc : lstMonHoc.stream().map(SubjectsDTO::getSub_year).collect(Collectors.toList())) {
//				cbNamHoc.addItem(namHoc);
//			}
			for(SubjectsDTO subject : lstMonHoc) {
				cbHocKi.addItem(subject.getSub_semester());
				cbNamHoc.addItem(String.valueOf(subject.getSub_year()));
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
//			if(hocKy.isEmpty() || namHoc == 0) {
//				return;
//			}
			
			for(SubjectsDTO subject : lstMonHoc) {
				cbMon.addItem(subject.getSub_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void loadDataTableResult(String subId) {
		modelDanhSachKetQua.setRowCount(0);
		lstResult = resultBus.getListStudentBySubject(subId);

		for(ResultsDTO result : lstResult) {
			modelDanhSachKetQua.addRow(new Object[] {result.getStu_id(), result.getRes_score(), result.getRes_score_2(), result.getRes_time(), result.getAvg_score()});
		}
		tblDanhSachDiemMonHoc.setModel(modelDanhSachKetQua);
	}
}
