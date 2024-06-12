package FORM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BUS.ClassesBUS;
import DTO.ClassesDTO;

public class ClassesGUI extends JInternalFrame implements ActionListener {
        private JTextField txtClassID;
        private JTextField txtClassName;
        private JLabel lblClassName;
        private JLabel lblClassID;
        private JButton btnAdd;
        private JButton btnUpdate;
        private JButton btnDelete;
        private JTable table;
        private ClassesBUS classesBUS;
        private DefaultTableModel tableModel;

        public ClassesGUI() {
                this.classesBUS = new ClassesBUS();
                initComponents();
                populateTable();
        }

        private void initComponents() {
                this.setTitle("Quản lý lớp");
                this.setSize(1000, 500);
                getContentPane().setLayout(null);

                JPanel inputPanel = new JPanel();
                inputPanel.setBounds(50, 20, 300, 120);
                inputPanel.setBorder(new TitledBorder("Class Information"));
                inputPanel.setLayout(null);
                getContentPane().add(inputPanel);

                lblClassID = new JLabel("Class ID");
                lblClassID.setBounds(10, 20, 100, 30);
                inputPanel.add(lblClassID);

                txtClassID = new JTextField();
                txtClassID.setBounds(120, 20, 160, 30);
                inputPanel.add(txtClassID);

                lblClassName = new JLabel("Class Name");
                lblClassName.setBounds(10, 60, 100, 30);
                inputPanel.add(lblClassName);

                txtClassName = new JTextField();
                txtClassName.setBounds(120, 60, 160, 30);
                inputPanel.add(txtClassName);

                btnAdd = new JButton("Add");
                btnAdd.setBounds(50, 160, 100, 30);
                btnAdd.addActionListener(this);
                getContentPane().add(btnAdd);

                btnUpdate = new JButton("Update");
                btnUpdate.setBounds(150, 160, 100, 30);
                btnUpdate.addActionListener(this);
                getContentPane().add(btnUpdate);

                btnDelete = new JButton("Delete");
                btnDelete.setBounds(250, 160, 100, 30);
                btnDelete.addActionListener(this);
                getContentPane().add(btnDelete);

//                readFIleExcel = new JButton("Read File Excel");
//                readFIleExcel.setBounds(300, 160, 100, 30);
//                getContentPane().add(readFIleExcel);

                tableModel = new DefaultTableModel();
                tableModel.addColumn("Class ID");
                tableModel.addColumn("Class Name");

                table = new JTable(tableModel);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(50, 200, 450, 250);
                getContentPane().add(scrollPane);
                table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent event) {
                                if (table.getSelectedRow() > -1) {
                                        txtClassID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                                        txtClassName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                                }
                        }
                });
        }

        private void populateTable() {

                tableModel.setRowCount(0);
                ArrayList<ClassesDTO> classesList = classesBUS.getAllClasses();
                for (ClassesDTO classDTO : classesList) {
                        tableModel.addRow(new Object[]{classDTO.getClass_id(), classDTO.getClass_name()});
                }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnAdd) {
                        addClass();
                }
                if (e.getSource() == btnUpdate) {
                        updateClass();
                }
                if (e.getSource() == btnDelete) {
                        deleteClass();
                }
        }

        private void addClass() {
                String classId = txtClassID.getText();
                String className = txtClassName.getText();
                ClassesDTO newClass = new ClassesDTO(classId, className);
                boolean success = classesBUS.addClass(newClass);
                if (success) {
                        tableModel.addRow(new Object[]{classId, className});
                        JOptionPane.showMessageDialog(null, "Class added successfully");
                } else {
                        JOptionPane.showMessageDialog(null, "Failed to add class");
                }
        }

        private void updateClass() {
                String classId = txtClassID.getText();
                String className = txtClassName.getText();
                ClassesDTO updatedClass = new ClassesDTO(classId, className);
                boolean success = classesBUS.updateClass(updatedClass);
                if (success) {
                        tableModel.setValueAt(classId, table.getSelectedRow(), 0);
                        tableModel.setValueAt(className, table.getSelectedRow(), 1);
                        JOptionPane.showMessageDialog(null, "Class updated successfully");
                } else {
                        JOptionPane.showMessageDialog(null, "Failed to update class");
                }
        }
        private  void deleteClass(){
                String classId = txtClassID.getText();
                boolean success = classesBUS.deleteClass(classId);
                if (success) {
                        tableModel.removeRow(table.getSelectedRow());
                        JOptionPane.showMessageDialog(null, "Class deleted successfully");
                } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete class");
                }
        }
}
