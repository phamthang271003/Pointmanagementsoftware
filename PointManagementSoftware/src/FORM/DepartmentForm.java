package FORM;

import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import BUS.DepartmentBUS;
import DTO.DepartmentsDTO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepartmentForm extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DepartmentBUS departmentBUS;
	private DepartmentsDTO departmentsDTO;
	private DefaultTableModel tableModel;
	private JTextField txtId;
	private JTextField txtName;

	public DepartmentForm() {
		this.departmentBUS = new DepartmentBUS();
		this.departmentsDTO = new DepartmentsDTO();
		this.init();
		this.tableDepartment();
		txtId.setEditable(false);

	}

	public void init() {
		this.setTitle("Quản lý Khoa");
		this.setSize(1000, 600);
		getContentPane().setLayout(null);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Department ID");
		tableModel.addColumn("Department Name");

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
		                nextId = departmentBUS.getNextDepartmentID();
		                txtId.setText(Integer.toString(nextId));
		                txtName.setText("");
		                adding = true;
		            } else {
		                DepartmentsDTO newDepartment = new DepartmentsDTO();
		                newDepartment.setDepart_id(Integer.toString(nextId));
		                newDepartment.setDepart_name(txtName.getText());
		                departmentBUS.addDepartment(newDepartment);
		                
		                updateTable();
		                
		                adding = false;
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});


		btnThem.setBounds(488, 30, 85, 21);
		getContentPane().add(btnThem);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String departId = table.getValueAt(selectedRow, 0).toString();

					int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Xác nhận xóa",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						departmentBUS.removeDepartment(departId);
						updateTable();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.");
				}
			}
		});

		btnXoa.setBounds(609, 30, 85, 21);
		getContentPane().add(btnXoa);

		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					String departId = table.getValueAt(selectedRow, 0).toString();
					String departName = table.getValueAt(selectedRow, 1).toString();

					departmentsDTO.setDepart_id(departId);
					departmentsDTO.setDepart_name(txtName.getText());
					try {
						departmentBUS.updateDepartment(departmentsDTO);
						updateTable();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để cập nhật.");
				}
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						try {
							String departId = table.getValueAt(selectedRow, 0).toString();
							String departName = table.getValueAt(selectedRow, 1).toString();

							txtId.setText(departId);
							txtName.setText(departName);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});

		btnSua.setBounds(747, 30, 85, 21);
		getContentPane().add(btnSua);

		JLabel lblNewLabel = new JLabel("Department ID:");
		lblNewLabel.setBounds(36, 30, 100, 13);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Department Name");
		lblNewLabel_1.setBounds(36, 72, 100, 13);
		getContentPane().add(lblNewLabel_1);

		txtId = new JTextField();
		txtId.setBounds(157, 31, 116, 19);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(157, 69, 116, 19);
		getContentPane().add(txtName);
		txtName.setColumns(10);
	}

	public void tableDepartment() {
		ArrayList<DepartmentsDTO> departmentList = departmentBUS.getAllDepartments();
		for (DepartmentsDTO departmentDTO : departmentList) {
			tableModel.addRow(new Object[] { departmentDTO.getDepart_id(), departmentDTO.getDepart_name() });
		}
	}

	private void updateTable() {
		tableModel.setRowCount(0);
		tableDepartment();
	}

}
