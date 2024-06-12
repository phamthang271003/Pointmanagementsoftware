package FORM;

import DTO.StudyRecordDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudyRecordFrame extends JFrame {
        private JTable table;
        private DefaultTableModel tableModel;

        public StudyRecordFrame(ArrayList<StudyRecordDTO> studyRecords) {
                this.setTitle("Study Records");
                this.setSize(600, 400);
                getContentPane().setLayout(new BorderLayout());

                tableModel = new DefaultTableModel();
                tableModel.addColumn("Student ID");
                tableModel.addColumn("Student Name");
                tableModel.addColumn("Subject Name");
                tableModel.addColumn("Time");
                tableModel.addColumn("Score");
                table = new JTable(tableModel);
                JScrollPane scrollPane = new JScrollPane(table);
                getContentPane().add(scrollPane, BorderLayout.CENTER);

                for (StudyRecordDTO record : studyRecords) {
                        tableModel.addRow(new Object[]{
                                record.getStu_id(),
                                record.getStu_name(),
                                record.getSub_name(),
                                record.getRes_time(),
                                record.getRes_score()
                        });
                }
        }
}
