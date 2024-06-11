package FORM;

import DTO.ComboItem;
import DTO.StudentsDTO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentsGUI extends JInternalFrame implements ActionListener {
        private JTextField txtStudentID;
        private JTextField txtStudentName;
        private JTextField txtStudentSex;
        private JTextField txtStudentDayEntry;
        private JTextField txtStudentSemester;
        private JTextField txtStudentDOB;
        private JTextField txtStudentAddress;
        private JComboBox<ComboItem> cbAcaId;
        private JComboBox<ComboItem> cbClassId;
        private JButton btnReadFile;
        private Label lblStudentID;
        private Label lblStudentName;
        private Label lblStudentSex;
        private Label lblStudentDayEntry;
        private Label lblStudentSemester;
        private Label lblStudentDOB;
        private Label lblStudentAddress;
        private Label lblAcaName;
        private Label lblClassName;
        private JButton btnAdd;
        private JButton btnUpdate;
        private JButton btnDelete;
        private JTable table;
        private BUS.StudentsBUS studentsBUS;
        private DefaultTableModel tableModel;

        public StudentsGUI() {
                studentsBUS = new BUS.StudentsBUS();
                initComponents();
                populateTable();
        }

        private void initComponents() {
                this.setTitle("Quản lý sinh viên");
                this.setSize(1200, 500);
                getContentPane().setLayout(null);

                JPanel inputPanel = new JPanel();
                inputPanel.setBounds(30, 20, 300, 380);
                inputPanel.setBorder(new TitledBorder("Student Information"));
                inputPanel.setLayout(null);
                getContentPane().add(inputPanel);

                lblStudentID = new Label("Student ID: ");
                lblStudentID.setBounds(10, 20, 100, 25);
                inputPanel.add(lblStudentID);

                txtStudentID = new JTextField();
                txtStudentID.setBounds(120, 20, 160, 25);
                inputPanel.add(txtStudentID);

                lblStudentName = new Label("Student Name");
                lblStudentName.setBounds(10, 60, 100, 25);
                inputPanel.add(lblStudentName);

                txtStudentName = new JTextField();
                txtStudentName.setBounds(120, 60, 160, 25);
                inputPanel.add(txtStudentName);

                lblStudentDayEntry = new Label("Student Day Entry");
                lblStudentDayEntry.setBounds(10, 100, 100, 25);
                inputPanel.add(lblStudentDayEntry);

                txtStudentDayEntry = new JTextField();
                txtStudentDayEntry.setBounds(120, 100, 160, 25);
                inputPanel.add(txtStudentDayEntry);

                lblStudentSemester = new Label("Student Semester");
                lblStudentSemester.setBounds(10, 140, 100, 25);
                inputPanel.add(lblStudentSemester);

                txtStudentSemester = new JTextField();
                txtStudentSemester.setBounds(120, 140, 160, 25);
                inputPanel.add(txtStudentSemester);

                lblStudentSex = new Label("Student Sex");
                lblStudentSex.setBounds(10, 180, 100, 25);
                inputPanel.add(lblStudentSex);

                txtStudentSex = new JTextField();
                txtStudentSex.setBounds(120, 180, 160, 25);
                inputPanel.add(txtStudentSex);

                lblStudentDOB = new Label("Student DOB");
                lblStudentDOB.setBounds(10, 220, 100, 25);
                inputPanel.add(lblStudentDOB);

                txtStudentDOB = new JTextField();
                txtStudentDOB.setBounds(120, 220, 160, 25);
                inputPanel.add(txtStudentDOB);

                lblStudentAddress = new Label("Student Address");
                lblStudentAddress.setBounds(10, 260, 100, 25);
                inputPanel.add(lblStudentAddress);

                txtStudentAddress = new JTextField();
                txtStudentAddress.setBounds(120, 260, 160, 25);
                inputPanel.add(txtStudentAddress);


                lblAcaName = new Label("Academy Name");
                lblAcaName.setBounds(10, 300, 100, 25);
                inputPanel.add(lblAcaName);

                cbAcaId = new JComboBox<ComboItem>();
                cbAcaId.setBounds(120, 300, 160, 25);
                inputPanel.add(cbAcaId);

                loadAcademics();
                lblClassName = new Label("Class Name");
                lblClassName.setBounds(10, 340, 100, 25);
                inputPanel.add(lblClassName);

                cbClassId = new JComboBox<ComboItem>();
                cbClassId.setBounds(120, 340, 160, 25);
                inputPanel.add(cbClassId);
                loadClasses();

                btnAdd = new JButton("Add");
                btnAdd.setBounds(30, 410, 60, 30);
                btnAdd.addActionListener(this);
                getContentPane().add(btnAdd);

                btnUpdate = new JButton("Update");
                btnUpdate.setBounds(100, 410, 70, 30);
                btnUpdate.addActionListener(this);
                getContentPane().add(btnUpdate);

                btnDelete = new JButton("Delete");
                btnDelete.setBounds(190, 410, 70, 30);
                btnDelete.addActionListener(this);
                getContentPane().add(btnDelete);

                btnReadFile = new JButton("Read");
                btnReadFile.setBounds(280, 410, 60, 30);
                btnReadFile.addActionListener(this);
                getContentPane().add(btnReadFile);

                tableModel = new DefaultTableModel();
                tableModel.addColumn("ID");
                tableModel.addColumn("Name");
                tableModel.addColumn("Day Entry");
                tableModel.addColumn("Semester");
                tableModel.addColumn("Sex");
                tableModel.addColumn("DOB");
                tableModel.addColumn("Address");
                tableModel.addColumn("Academy Name");
                tableModel.addColumn("Class Name");

                table = new JTable(tableModel);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(350, 20, 800, 420);
                getContentPane().add(scrollPane);
                
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                for (int i = 0; i < table.getColumnCount(); i++) {
                        table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }


                table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent event) {
                                txtStudentID.setEnabled(false);
                                if (table.getSelectedRow() > -1) {
                                        txtStudentID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                                        txtStudentName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                                        txtStudentDayEntry.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                                        txtStudentSemester.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                                        txtStudentSex.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                                        txtStudentDOB.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
                                        txtStudentAddress.setText(table.getValueAt(table.getSelectedRow(), 6).toString());

                                        String selectedAcaName = table.getValueAt(table.getSelectedRow(), 7).toString();
                                        for (int i = 0; i < cbAcaId.getItemCount(); i++) {
                                                ComboItem item = cbAcaId.getItemAt(i);
                                                if (item.getName().equals(selectedAcaName)) {
                                                        cbAcaId.setSelectedIndex(i);
                                                        break;
                                                }
                                        }

                                        String selectedClassName = table.getValueAt(table.getSelectedRow(), 8).toString();
                                        for (int i = 0; i < cbClassId.getItemCount(); i++) {
                                                ComboItem item = cbClassId.getItemAt(i);
                                                if (item.getName().equals(selectedClassName)) {
                                                        cbClassId.setSelectedIndex(i);
                                                        break;
                                                }
                                        }
                                }
                        }
                });



        }

        private void loadAcademics() {
        	
                BUS.AcademicBUS academicBUS = new BUS.AcademicBUS();
                ArrayList<DTO.AcademicDTO> academicList = academicBUS.getAllAcademic();
                for (DTO.AcademicDTO academic : academicList) {
                        cbAcaId.addItem(new ComboItem(academic.getAca_id(), academic.getAca_name()));
                }
        }

        
        private void loadClasses() {
                BUS.ClassesBUS classBUS = new BUS.ClassesBUS();
                ArrayList<DTO.ClassesDTO> classList = classBUS.getAllClasses();
                for (DTO.ClassesDTO classDTO : classList) {
                        cbClassId.addItem(new ComboItem(classDTO.getClass_id(), classDTO.getClass_name()));
                }
        }

        private void populateTable() {
                tableModel.setRowCount(0);
                ArrayList<StudentsDTO> studentsList = studentsBUS.getAllStudent();
                for (StudentsDTO studentDTO : studentsList) {
                        tableModel.addRow(new Object[]{
                                studentDTO.getStu_id(),
                                studentDTO.getStu_name(),
                                studentDTO.getStu_day_entry(),
                                studentDTO.getStu_semester(),
                                studentDTO.getStu_sex(),
                                studentDTO.getDob(),
                                studentDTO.getAddress(),
                                studentDTO.getAca_id(),
                                studentDTO.getClass_id()});

                }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnAdd) {
                        addStudent();
                }
                if (e.getSource() == btnUpdate) {
                        updateStudent();
                }
                if (e.getSource() == btnDelete) {
                        deleteStudent();
                }
                if(e.getSource() == btnReadFile) {
                	frmStudents frm = new frmStudents();
                	
                	frm.setVisible(true);
                }

        }

        public void addStudent() {
                StudentsDTO student = new StudentsDTO();
                student.setStu_id(txtStudentID.getText());
                student.setStu_name(txtStudentName.getText());
                student.setStu_day_entry(java.sql.Date.valueOf(txtStudentDayEntry.getText()));
                student.setStu_semester(Integer.parseInt(txtStudentSemester.getText()));
                student.setStu_sex(txtStudentSex.getText());
                student.setDob(java.sql.Date.valueOf(txtStudentDOB.getText()));
                student.setAddress(txtStudentAddress.getText());
                ComboItem selectedAcademic = (ComboItem) cbAcaId.getSelectedItem();
                student.setAca_id(selectedAcademic.getId());
                ComboItem selectedClass = (ComboItem) cbClassId.getSelectedItem();
                student.setClass_id(selectedClass.getId());
                boolean success = studentsBUS.addStudent(student);
                if (success) {
                        JOptionPane.showMessageDialog(this, "Add student successfully");
                        clearFields();
                        populateTable();
                } else {
                        JOptionPane.showMessageDialog(this, "Add student failed");
                }
        }
        private void updateStudent() {
                StudentsDTO student = new StudentsDTO();
                student.setStu_id(txtStudentID.getText());
                student.setStu_name(txtStudentName.getText());
                student.setStu_day_entry(java.sql.Date.valueOf(txtStudentDayEntry.getText()));
                student.setStu_semester(Integer.parseInt(txtStudentSemester.getText()));
                student.setStu_sex(txtStudentSex.getText());
                student.setDob(java.sql.Date.valueOf(txtStudentDOB.getText()));
                student.setAddress(txtStudentAddress.getText());
                ComboItem selectedAcademic = (ComboItem) cbAcaId.getSelectedItem();
                student.setAca_id(selectedAcademic.getId());

                ComboItem selectedClass = (ComboItem) cbClassId.getSelectedItem();
                student.setClass_id(selectedClass.getId());

                boolean success = studentsBUS.updateStudent(student);
                if (success) {
                        JOptionPane.showMessageDialog(this, "Add student successfully");
                        clearFields();
                        populateTable();
                } else {
                        JOptionPane.showMessageDialog(this, "Add student failed");
                }
        }

        private void deleteStudent() {
                String studentId = txtStudentID.getText();
                boolean success = studentsBUS.deleteStudent(studentId);
                if (success) {
                        JOptionPane.showMessageDialog(this, "Delete student successfully");
                        clearFields();
                        populateTable();
                } else {
                        JOptionPane.showMessageDialog(this, "Sinh Vien Con Dang hoc ");
                }
        }

        private void clearFields() {
                txtStudentID.setText("");
                txtStudentName.setText("");
                txtStudentDayEntry.setText("");
                txtStudentSemester.setText("");
                txtStudentSex.setText("");
                txtStudentDOB.setText("");
                txtStudentAddress.setText("");
        }


}
