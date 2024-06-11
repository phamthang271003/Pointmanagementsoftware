package FORM;

import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import BUS.AcademicBUS;
import DAO.DepartmentDAO;
import DTO.AcademicDTO;
import DTO.DepartmentsDTO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AcademicForm extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private AcademicBUS academicBUS;
    private DepartmentDAO departmentDAO;
    private AcademicDTO academicDTO;
    private DefaultTableModel tableModel;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtSemester;
    private JComboBox<DepartmentsDTO> cboDepartId;

    public AcademicForm() {
    	
        this.academicBUS = new AcademicBUS();
        this.departmentDAO = new DepartmentDAO();
        this.academicDTO = new AcademicDTO();
        this.init();
        this.tableDepartment();
        this.populateDepartments();
    }

    public void init() {
        this.setTitle("Quản lý ngành");
        this.setSize(1000, 600);
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Academic ID:");
        lblNewLabel.setBounds(36, 30, 100, 13);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Academic Name");
        lblNewLabel_1.setBounds(36, 72, 100, 13);
        getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Semester");
        lblNewLabel_2.setBounds(36, 111, 78, 13);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Department Name");
        lblNewLabel_3.setBounds(323, 103, 100, 29);
        getContentPane().add(lblNewLabel_3);

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

        cboDepartId = new JComboBox<>();
        cboDepartId.setBounds(442, 107, 131, 21);
        getContentPane().add(cboDepartId);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Academic ID");
        tableModel.addColumn("Academic Name");
        tableModel.addColumn("Semester Name");
        tableModel.addColumn("Department ID");

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
                        nextId = academicBUS.getNextAcademicID();
                        txtId.setText(Integer.toString(nextId));
                        txtName.setText("");
                        adding = true;
                    } else {
                        AcademicDTO academicDTO = new AcademicDTO();
                        academicDTO.setAca_id((Integer.toString(nextId)));
                        academicDTO.setAca_name(txtName.getText());
                        academicDTO.setAca_semester(txtSemester.getText());
                        DepartmentsDTO selectedDepartment = (DepartmentsDTO) cboDepartId.getSelectedItem();
                        academicDTO.setDepart_id(selectedDepartment.getDepart_id());
                        academicBUS.addAcademic(academicDTO);

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
                    try {
                        String academicId = table.getValueAt(selectedRow, 0).toString();

                        int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Xác nhận xóa",
                                JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            academicBUS.removeAcademic(academicId);
                            updateTable(); 
                            JOptionPane.showMessageDialog(null, "Đã xóa ngành thành công.");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi xóa ngành.");
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
                    try {
                        String aca_id = txtId.getText();
                        String aca_name = txtName.getText();
                        String aca_semester = txtSemester.getText();
                        DepartmentsDTO selectedDepartment = (DepartmentsDTO) cboDepartId.getSelectedItem();
                        String depart_id = selectedDepartment.getDepart_id();

                        academicDTO.setAca_id(aca_id);
                        academicDTO.setAca_name(aca_name);
                        academicDTO.setAca_semester(aca_semester);
                        academicDTO.setDepart_id(depart_id);
                        academicBUS.updateAcademic(academicDTO);
                        updateTable();
                        JOptionPane.showMessageDialog(null, "Cập nhật ngành thành công.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật ngành.");
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
                            String academicId = table.getValueAt(selectedRow, 0).toString();
                            String academicName = table.getValueAt(selectedRow, 1).toString();
                            String academicSemester = table.getValueAt(selectedRow, 2).toString();
                            String depart_id = table.getValueAt(selectedRow, 3).toString();

                            txtId.setText(academicId);
                            txtName.setText(academicName);
                            txtSemester.setText(academicSemester);

                            for (int i = 0; i < cboDepartId.getItemCount(); i++) {
                                DepartmentsDTO department = cboDepartId.getItemAt(i);
                                if (department.getDepart_id().equals(depart_id)) {
                                    cboDepartId.setSelectedIndex(i);
                                    break;
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });


        btnSua.setBounds(747, 30, 85, 21);
        getContentPane().add(btnSua);
    }

    public void tableDepartment() {
        ArrayList<AcademicDTO> academicList = academicBUS.getAllAcademic();
        for (AcademicDTO academicDTO : academicList) {
            tableModel.addRow(new Object[] { academicDTO.getAca_id(), academicDTO.getAca_name(),
                    academicDTO.getAca_semester(), academicDTO.getDepart_id() });
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        ArrayList<AcademicDTO> academicList = academicBUS.getAllAcademic();
        for (AcademicDTO academicDTO : academicList) {
            tableModel.addRow(new Object[] {
                academicDTO.getAca_id(),
                academicDTO.getAca_name(),
                academicDTO.getAca_semester(),
                academicDTO.getDepart_id()
            });
        }
    }

    private void populateDepartments() {
        try {
            ArrayList<DepartmentsDTO> departmentList = departmentDAO.listDepartment();
            for (DepartmentsDTO department : departmentList) {
                cboDepartId.addItem(department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
