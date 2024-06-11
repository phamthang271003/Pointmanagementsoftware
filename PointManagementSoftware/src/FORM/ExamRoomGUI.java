package FORM;

import BUS.ExamRoomBUS;
import DTO.ExamRoomDTO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExamRoomGUI extends JInternalFrame implements ActionListener {
        private JTextField txtExamRoomID;
        private JTextField txtExamRoomName;
        private JTextField txtExamRoomQuantity;
        private Label lblExamRoomName;
        private Label lblExamRoomID;
        private Label lblExamRoomQuantity;
        private JButton btnAdd;
        private JButton btnUpdate;
        private JButton btnDelete;
        private JTable table;
        private ExamRoomBUS examRoomBUS;
        private DefaultTableModel tableModel;

        public ExamRoomGUI() {
                this.examRoomBUS = new ExamRoomBUS();
                initComponents();
                populateTable();
        }

        private void initComponents() {
                this.setTitle("Quản lý phòng thi");
                this.setSize(1000, 500);
                getContentPane().setLayout(null);

                JPanel inputPanel = new JPanel();
                inputPanel.setBounds(30, 20, 300, 380);
                inputPanel.setBorder(new TitledBorder("Exam  Room Information"));
                inputPanel.setLayout(null);
                getContentPane().add(inputPanel);

                lblExamRoomID = new Label("ExamRoom ID: ");
                lblExamRoomID.setBounds(10, 20, 100, 25);
                inputPanel.add(lblExamRoomID);

                txtExamRoomID = new JTextField();
                txtExamRoomID.setBounds(120, 20, 160, 25);
                inputPanel.add(txtExamRoomID);

                lblExamRoomName = new Label("Room Name");
                lblExamRoomName.setBounds(10, 60, 100, 25);
                inputPanel.add(lblExamRoomName);

                txtExamRoomName = new JTextField();
                txtExamRoomName.setBounds(120, 60, 160, 25);
                inputPanel.add(txtExamRoomName);

                lblExamRoomQuantity = new Label("Quantiity");
                lblExamRoomQuantity.setBounds(10, 100, 100, 25);
                inputPanel.add(lblExamRoomQuantity);

                txtExamRoomQuantity = new JTextField();
                txtExamRoomQuantity.setBounds(120, 100, 160, 25);
                inputPanel.add(txtExamRoomQuantity);

                btnAdd = new JButton("Add");
                btnAdd.setBounds(30, 410, 80, 30);
                btnAdd.addActionListener(this);
                getContentPane().add(btnAdd);

                btnUpdate = new JButton("Update");
                btnUpdate.setBounds(140, 410, 80, 30);
                btnUpdate.addActionListener(this);
                getContentPane().add(btnUpdate);

                btnDelete = new JButton("Delete");
                btnDelete.setBounds(250, 410, 80, 30);
                btnDelete.addActionListener(this);
                getContentPane().add(btnDelete);

                tableModel = new DefaultTableModel();
                tableModel.addColumn("ID");
                tableModel.addColumn("Name");
                tableModel.addColumn("Quantity");

                table = new JTable(tableModel);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(350, 20, 800, 420);
                getContentPane().add(scrollPane);
                table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent e) {
                                txtExamRoomID.enable(false);
                                if (table.getSelectedRow() >= 0) {
                                        txtExamRoomID.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                                        txtExamRoomName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                                        txtExamRoomQuantity.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                                }
                        }
                });
        }

        private void populateTable() {
                tableModel.setRowCount(0);
                ArrayList<ExamRoomDTO> examRooms = examRoomBUS.getAllExamRoom();
                for (ExamRoomDTO examRoom : examRooms) {
                        tableModel.addRow(new Object[]{examRoom.getRoomID(), examRoom.getRoomName(), examRoom.getQuantity()});
                }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnAdd) {
                        addExamRoom();
                }
                if (e.getSource() == btnUpdate) {
                        updateExamRoom();
                }
                if (e.getSource() == btnDelete) {
                        deleteExamRoom();
                }

        }

        private void addExamRoom() {
                ExamRoomDTO examRoom = new ExamRoomDTO();
                examRoom.setRoomID(txtExamRoomID.getText());
                examRoom.setRoomName(txtExamRoomName.getText());
                examRoom.setQuantity(Integer.parseInt(txtExamRoomQuantity.getText()));
                boolean success = examRoomBUS.addExamRoom(examRoom);
                if (success) {
                        JOptionPane.showMessageDialog(this, "Add student successfully");
                        clearFields();
                        populateTable();
                } else {
                        JOptionPane.showMessageDialog(this, "Add student failed");
                }
        }

        private void updateExamRoom() {
                ExamRoomDTO examRoom = new ExamRoomDTO();
                examRoom.setRoomID(txtExamRoomID.getText());
                examRoom.setRoomName(txtExamRoomName.getText());
                examRoom.setQuantity(Integer.parseInt(txtExamRoomQuantity.getText()));
                boolean success = examRoomBUS.updateExamRoom(examRoom);
                if (success) {
                        JOptionPane.showMessageDialog(this, "Update student successfully");
                        clearFields();
                        populateTable();
                } else {
                        JOptionPane.showMessageDialog(this, "Update student failed");
                }
        }

        private void deleteExamRoom() {
                String roomID = txtExamRoomID.getText();
                boolean success = examRoomBUS.deleteExamRoom(roomID);
                if (success) {
                        JOptionPane.showMessageDialog(this, "Delete student successfully");
                        clearFields();
                        populateTable();
                } else {
                        JOptionPane.showMessageDialog(this, "Delete student failed");
                }
        }

        private void clearFields() {
                txtExamRoomID.setText("");
                txtExamRoomName.setText("");
                txtExamRoomQuantity.setText("");

        }
}
