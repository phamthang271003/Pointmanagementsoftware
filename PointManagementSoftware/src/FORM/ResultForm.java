package FORM;

import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import BUS.ResultBUS;
import DAO.ClassesDAO;
import DAO.SubjectDAO;
import DTO.ClassesDTO;
import DTO.ResultDTO;
import DTO.SubjectDTO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResultForm extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private ResultBUS resultBUS;
	private ClassesDAO classesDAO;
	private SubjectDAO subjectDAO;
	private DefaultTableModel tableModel;
	private ClassesDTO selectedClass;
	private JTextField txtStudentId;
	private JComboBox<SubjectDTO> cboSubjectId;
	private JComboBox<ClassesDTO> cboClassId;

	public ResultForm() {
		this.resultBUS = new ResultBUS();
		this.classesDAO = new ClassesDAO();
		this.subjectDAO = new SubjectDAO();
		this.init();
		this.populateClass();
		this.populateSubject();
	}

	public void init() {
		this.setTitle("Thêm môn học cho sinh viên");
		this.setSize(1000, 600);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Class Name");
		lblNewLabel_1.setBounds(36, 34, 100, 13);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Student id");
		lblNewLabel_2.setBounds(36, 111, 78, 13);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Subject Name");
		lblNewLabel_3.setBounds(36, 72, 100, 29);
		getContentPane().add(lblNewLabel_3);

		txtStudentId = new JTextField();
		txtStudentId.setBounds(157, 108, 116, 19);
		getContentPane().add(txtStudentId);
		txtStudentId.setColumns(10);

		cboSubjectId = new JComboBox<>();
		cboSubjectId.setBounds(157, 76, 116, 21);
		getContentPane().add(cboSubjectId);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Student ID");
		tableModel.addColumn("Student Name");
		tableModel.addColumn("Subject Name");
		tableModel.addColumn("Result Time");
		tableModel.addColumn("Result Score");

		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 155, 917, 300);
		getContentPane().add(scrollPane);

		JButton btnThem = new JButton("Thêm sinh viên");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String studentId = txtStudentId.getText();
				SubjectDTO selectedSubject = (SubjectDTO) cboSubjectId.getSelectedItem();

				if (studentId.isEmpty() || selectedSubject == null) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập Student ID và chọn Subject.");
					return;
				}
				ResultDTO result = new ResultDTO();
				result.setStu_id(studentId);
				result.setSub_id(selectedSubject.getSub_id());
				result.setRes_time(1);
				result.setRes_score(0);

				try {
					resultBUS.addResult(result);
					 updateTable(Integer.parseInt(selectedClass.getClass_id()));
				} catch (Exception e2) {
					e2.printStackTrace();
				}

				txtStudentId.setText("");
				cboSubjectId.setSelectedIndex(-1);
			}
		});

		btnThem.setBounds(338, 107, 162, 21);
		getContentPane().add(btnThem);

		cboClassId = new JComboBox<>();
		cboClassId.setBounds(157, 30, 116, 21);
		getContentPane().add(cboClassId);

		cboClassId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedClass = (ClassesDTO) cboClassId.getSelectedItem();
				if (selectedClass != null) {
					updateTable(Integer.parseInt(selectedClass.getClass_id()));
				}
			}
		});

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (!event.getValueIsAdjusting()) {
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow != -1) {
		                String studentId = (String) table.getValueAt(selectedRow, 0);
		                String studentName = (String) table.getValueAt(selectedRow, 1);
		                String subjectName = (String) table.getValueAt(selectedRow, 2);

		            
		                if (studentId != null) {
		                    txtStudentId.setText(studentId);
		                }
		                if (subjectName != null) {
		                    for (int i = 0; i < cboSubjectId.getItemCount(); i++) {
		                        SubjectDTO subject = cboSubjectId.getItemAt(i);
		                        if (subject.getSub_name().equals(subjectName)) {
		                            cboSubjectId.setSelectedIndex(i);
		                            break;
		                        } 	
		                    }
		                }
		            }
		        }
		    }
		});

	}

	private void updateTable(int classId) {
		tableModel.setRowCount(0);
		ArrayList<ResultDTO> results = resultBUS.getResultsByClassId(classId);
		for (ResultDTO result : results) {
			tableModel.addRow(new Object[] { result.getStu_id(), result.getStu_name(), result.getSub_name(),
					result.getRes_time(), result.getRes_score() });
		}
	}

	private void populateClass() {
		try {
			ArrayList<ClassesDTO> Classlist = classesDAO.listClass();
			for (ClassesDTO classesli : Classlist) {
				cboClassId.addItem(classesli);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void populateSubject() {
		try {
			ArrayList<SubjectDTO> subjectlist = subjectDAO.listSubject();
			for (SubjectDTO subject : subjectlist) {
				cboSubjectId.addItem(subject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
