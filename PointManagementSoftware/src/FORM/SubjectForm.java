package FORM;

import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import BUS.AcademicBUS;
import BUS.SubjectBUS;
import DAO.DepartmentDAO;
import DAO.SubjectDAO;
import DTO.AcademicDTO;
import DTO.SubjectDTO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubjectForm extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private SubjectBUS subjectBUS;
	private SubjectDAO subjectDAO;
	private SubjectDTO subjectDTO;
	private DefaultTableModel tableModel;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtSemester;

	public SubjectForm() {

		this.subjectBUS = new SubjectBUS();
		this.subjectDAO = new SubjectDAO();
		this.subjectDTO = new SubjectDTO();
		this.init();
		this.tableSubject();
	}

	public void init() {
		this.setTitle("Quản lý môn học");
		this.setSize(1200, 600);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Subject ID:");
		lblNewLabel.setBounds(36, 30, 100, 13);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Subject Name");
		lblNewLabel_1.setBounds(36, 72, 100, 13);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Semester");
		lblNewLabel_2.setBounds(36, 111, 78, 13);
		getContentPane().add(lblNewLabel_2);

		txtId = new JTextField();
		txtId.setBounds(157, 31, 116, 19);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		txtId.setEditable(false);

		txtName = new JTextField();
		txtName.setBounds(157, 69, 116, 19);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		txtSemester = new JTextField();
		txtSemester.setBounds(157, 108, 116, 19);
		getContentPane().add(txtSemester);
		txtSemester.setColumns(10);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Subject ID");
		tableModel.addColumn("Subject Name");
		tableModel.addColumn("Semester");

		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 155, 917, 300);
		getContentPane().add(scrollPane);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			boolean adding = false;
			int nextId = 0;

			public void actionPerformed(ActionEvent e) {
				try {
					if (!adding) {
						nextId = subjectBUS.getNextSubjectID();
						txtId.setText(Integer.toString(nextId));
						txtName.setText("");
						txtSemester.setText("");
						adding = true;
					} else {
						SubjectDTO subjectDTO = new SubjectDTO();
						subjectDTO.setSub_id(txtId.getText());
						subjectDTO.setSub_name(txtName.getText());
						subjectDTO.setSub_semester(txtSemester.getText());

						subjectBUS.addSubject(subjectDTO);

						updateTable();
						adding = false;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		btnThem.setBounds(381, 26, 85, 21);
		getContentPane().add(btnThem);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					try {
						String subId = table.getValueAt(selectedRow, 0).toString();

						int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?",
								"Xác nhận xóa", JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.YES_OPTION) {
                            subjectBUS.removeSubject(subId);
                            updateTable(); 
							JOptionPane.showMessageDialog(null, "Đã xóa môn học thành công.");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Lỗi khi xóa môn học.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
				}
			}
		});

		btnXoa.setBounds(544, 26, 85, 21);
		getContentPane().add(btnXoa);

		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					try {
						String sub_id = txtId.getText();
						String sub_name = txtName.getText();
						String sub_semester = txtSemester.getText();

                        subjectDTO.setSub_id(sub_id);
                        subjectDTO.setSub_name(sub_name);
                        subjectDTO.setSub_semester(sub_semester);
                        subjectBUS.updateSubject(subjectDTO);
                        updateTable();
						JOptionPane.showMessageDialog(null, "Cập nhật môn học thành công.");
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật môn học.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to update.");
				}
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						try {
							String subId = table.getValueAt(selectedRow, 0).toString();
							String subName = table.getValueAt(selectedRow, 1).toString();
							String subSemester = table.getValueAt(selectedRow, 2).toString();
							txtId.setText(subId);
							txtName.setText(subName);
							txtSemester.setText(subSemester);

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});

		btnSua.setBounds(706, 26, 85, 21);
		getContentPane().add(btnSua);
	}

	public void tableSubject() {
		ArrayList<SubjectDTO> subjectList = subjectBUS.getAllSubject();
		for (SubjectDTO subjectDTO : subjectList) {
			tableModel.addRow(
					new Object[] { subjectDTO.getSub_id(), subjectDTO.getSub_name(), subjectDTO.getSub_semester() });
		}
	}

	private void updateTable() {
		tableModel.setRowCount(0);
		ArrayList<SubjectDTO> subjectList = subjectBUS.getAllSubject();
		for (SubjectDTO subjectDTO : subjectList) {
			tableModel.addRow(
					new Object[] { subjectDTO.getSub_id(), subjectDTO.getSub_name(), subjectDTO.getSub_semester(),

					});
		}
	}

}
