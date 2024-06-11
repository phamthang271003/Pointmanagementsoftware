package FORM;

import DTO.StudentsDTO;
import DTO.StudyRecordDTO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudyRecordsGUI extends JInternalFrame implements ActionListener {

        private Label lblStudentID;
        private JTextField txtStudentID;
        private JButton btnSearch;
        private BUS.StudentsBUS studentsBUS;
        private JButton btnStudyRecords;
        private JTable table;
        private DAO.StudentDAO studentsDAO;
        private DefaultTableModel tableModel;

        public StudyRecordsGUI() {
                studentsBUS = new BUS.StudentsBUS();
                studentsDAO = new DAO.StudentDAO();  // Ensure DAO is properly initialized
                initComponents();
                populateTable();
        }

        public void initComponents() {
                this.setTitle("Quản lý sinh viên");
                this.setSize(1200, 500);
                getContentPane().setLayout(null);

                JPanel inputPanel = new JPanel();
                inputPanel.setBounds(30, 20, 300, 100);
                inputPanel.setBorder(new TitledBorder("Student Information"));
                inputPanel.setLayout(null);
                getContentPane().add(inputPanel);

                lblStudentID = new Label("Student ID: ");
                lblStudentID.setBounds(10, 20, 100, 25);
                inputPanel.add(lblStudentID);

                txtStudentID = new JTextField();
                txtStudentID.setBounds(120, 20, 160, 25);
                inputPanel.add(txtStudentID);

                btnSearch = new JButton("Search");
                btnSearch.setBounds(30, 140, 100, 30);
                btnSearch.addActionListener(this);
                getContentPane().add(btnSearch);

                btnStudyRecords = new JButton("Study Records");
                btnStudyRecords.setBounds(200, 140, 120, 30);
                btnStudyRecords.addActionListener(this);
                getContentPane().add(btnStudyRecords);

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
                                studentDTO.getClass_id()
                        });
                }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnSearch) {
                        searchStudent();
                }
                if (e.getSource() == btnStudyRecords) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                                String studentId = table.getValueAt(selectedRow, 0).toString();
                                ArrayList<StudyRecordDTO> studyRecords = studentsDAO.getStudyRecords(studentId);
                                StudyRecordFrame studyRecordFrame = new StudyRecordFrame(studyRecords);
                                studyRecordFrame.setVisible(true);
                        } else {
                                JOptionPane.showMessageDialog(null, "Please select a student first.");
                        }
                }
        }

        private void searchStudent() {
                String studentId = txtStudentID.getText();
                StudentsDTO student = studentsBUS.searchStudent(studentId);
                if (student != null) {
                        tableModel.setRowCount(0); // Clear the table
                        tableModel.addRow(new Object[]{
                                student.getStu_id(),
                                student.getStu_name(),
                                student.getStu_day_entry(),
                                student.getStu_semester(),
                                student.getStu_sex(),
                                student.getDob(),
                                student.getAddress(),
                                student.getAca_id(),
                                student.getClass_id()
                        });
                } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên với mã đã cung cấp.");
                }
        }
}
