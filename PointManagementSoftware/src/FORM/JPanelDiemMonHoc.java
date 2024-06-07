package FORM;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class JPanelDiemMonHoc extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tblDanhSachDiemMonHoc;
	private JTextField txtDiemGiuaKi;
	private JTextField txtCKLan1;
	private JTextField txtCKLan2;
	private JTextField txtDiemTrungBinh;
	private JTextField textField_4;
	private JTextField txtMaSoSinhVien;

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
		
		JLabel lblNewLabel_1 = new JLabel("Lớp");
		lblNewLabel_1.setBounds(10, 62, 49, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Điểm");
		lblNewLabel_2.setBounds(10, 101, 49, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Giữa Kì");
		lblNewLabel_3.setBounds(84, 101, 49, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Cuối Kì Lần 1");
		lblNewLabel_4.setBounds(216, 101, 74, 14);
		panel.add(lblNewLabel_4);
		
		txtDiemGiuaKi = new JTextField();
		txtDiemGiuaKi.setBounds(143, 98, 49, 20);
		panel.add(txtDiemGiuaKi);
		txtDiemGiuaKi.setColumns(10);
		
		txtCKLan1 = new JTextField();
		txtCKLan1.setBounds(300, 98, 49, 20);
		panel.add(txtCKLan1);
		txtCKLan1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Cuối Kì Lần 2");
		lblNewLabel_5.setBounds(359, 101, 67, 14);
		panel.add(lblNewLabel_5);
		
		txtCKLan2 = new JTextField();
		txtCKLan2.setBounds(436, 98, 49, 20);
		panel.add(txtCKLan2);
		txtCKLan2.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("TB");
		lblNewLabel_6.setBounds(495, 101, 28, 14);
		panel.add(lblNewLabel_6);
		
		txtDiemTrungBinh = new JTextField();
		txtDiemTrungBinh.setBounds(533, 98, 49, 20);
		panel.add(txtDiemTrungBinh);
		txtDiemTrungBinh.setColumns(10);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBounds(592, 97, 77, 23);
		panel.add(btnCapNhat);
		
		JComboBox cbLop = new JComboBox();
		cbLop.setBounds(62, 58, 144, 22);
		panel.add(cbLop);
		
		JButton btnNewButton_1 = new JButton("->");
		btnNewButton_1.setBounds(216, 58, 45, 23);
		panel.add(btnNewButton_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(271, 59, 96, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Mã Số Sinh Viên");
		lblNewLabel_7.setBounds(452, 62, 89, 14);
		panel.add(lblNewLabel_7);
		
		txtMaSoSinhVien = new JTextField();
		txtMaSoSinhVien.setBounds(551, 56, 96, 20);
		panel.add(txtMaSoSinhVien);
		txtMaSoSinhVien.setColumns(10);
		
		JComboBox cbNamHoc = new JComboBox();
		cbNamHoc.setBounds(84, 22, 199, 22);
		panel.add(cbNamHoc);
		
		JLabel lblNewLabel_8 = new JLabel("Học Kỳ");
		lblNewLabel_8.setBounds(366, 26, 49, 14);
		panel.add(lblNewLabel_8);
		
		JComboBox cbHocKi = new JComboBox();
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

	}
}
