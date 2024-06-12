package FORM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BUS.StudentBUS;
import DAO.DataProvider;
import DAO.StudentDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class frmStudents extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tb_ListStudent;
	private StudentBUS studentBUS;
	private JComboBox<String> cboLop;
	private JComboBox<String> cboNganh;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frmStudents frame = new frmStudents();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public frmStudents() {
		DataProvider.getInstance().connectToDatabase();
		this.studentBUS=new StudentBUS();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 928, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tải file excel");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 88, 28);
		contentPane.add(lblNewLabel);
		
		JButton btnAddStudent = new JButton("Đọc file excel");
		btnAddStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser fileChooser = new JFileChooser();
	                int result = fileChooser.showOpenDialog(null);
	                if (result == JFileChooser.APPROVE_OPTION) {
	                    File selectedFile = fileChooser.getSelectedFile();
	                    try {
	                        readExcelFile(selectedFile);
	                    } catch (IOException ex) {
	                        ex.printStackTrace();
	                        JOptionPane.showMessageDialog(null, "Error reading file: " + ex.getMessage());
	                    }
	                }
			}
		});
		btnAddStudent.setBounds(10, 48, 152, 33);
		contentPane.add(btnAddStudent);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(172, 10, 732, 578);
		contentPane.add(scrollPane);
		
		tb_ListStudent = new JTable();
		tb_ListStudent.setEnabled(false);
		tb_ListStudent.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"MSSV", "Họ Tên", "Ng\u00E0y Nh\u1EADp H\u1ECDc", "H\u1ECDc K\u00EC", "Gi\u1EDBi T\u00EDnh", "Ng\u00E0y Sinh", "\u0110\u1ECBa ch\u1EC9"
			}
		));
        scrollPane.setViewportView(tb_ListStudent);
        
        JLabel lblNewLabel_1 = new JLabel("Ngành");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(10, 111, 88, 28);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Lớp");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(10, 211, 88, 28);
        contentPane.add(lblNewLabel_2);
        
        cboNganh = new JComboBox<String>();
        cboNganh.setBounds(10, 147, 152, 28);
        contentPane.add(cboNganh);
        
        cboLop = new JComboBox<String>();
        cboLop.setBounds(10, 246, 152, 28);
        contentPane.add(cboLop);
        
        JLabel lblNewLabel_3 = new JLabel("Hoàn tất");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(10, 316, 88, 28);
        contentPane.add(lblNewLabel_3);
        
        // Sự kiện thêm danh sách sinh viên vào bảng
        JButton btnThem = new JButton("Thêm");
        btnThem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int count=0;
        		String MSSV="";
        		String[] splitLop=getTextCboLop().split("\\.");
        		String idLop=splitLop[0];
        		String[] splitNganh=getTextCboNganh().split("\\.");
        		String idNganh=splitNganh[0];
        		System.out.println("Ngành: "+idNganh+" Lop: "+idLop);
        		// Lấy thông tin sinh viên từ bảng
        		DefaultTableModel model = (DefaultTableModel) tb_ListStudent.getModel();
        		System.out.println("Số dòng: "+model.getRowCount());
        		int row=model.getRowCount();
        		if(row>0)
        		{
        			for (int i = 0; i < model.getRowCount(); i++) {
            			String stu_id = model.getValueAt(i, 0).toString();
            			int checkStudentId=0;
            			checkStudentId=studentBUS.checkStudentId(stu_id);
            			String stu_name = model.getValueAt(i, 1).toString();
            			String stu_day_entry = model.getValueAt(i, 2).toString();
            			String[] splitSemester = model.getValueAt(i, 3).toString().split("\\.");
            			String stu_semester=splitSemester[0];
            			String stu_sex = model.getValueAt(i, 4).toString();
            			String stu_dob = model.getValueAt(i, 5).toString();
            			String stu_address = model.getValueAt(i, 6).toString();
            			if(checkStudentId==0)
            			{
                			// Thêm sinh viên vào cơ sở dữ liệu
                			studentBUS.insertStudent(stu_id, stu_name, stu_day_entry, stu_semester, stu_sex, stu_dob, stu_address, idNganh, idLop);
            			}
            			else
            			{
            				count=count+1;
            				MSSV=MSSV+stu_name+", ";
            			}
            			
            		}
            		if(count > 0)
            		{
            			JOptionPane.showMessageDialog(frmStudents.this, "Đã có "+count+" sinh viên trong danh sách các lớp khác\n");
            			 model.setRowCount(0); // Clear existing data
            		}
            		else
            		{
            			JOptionPane.showMessageDialog(frmStudents.this, "Thêm tất cả sinh viên thành công");
            			
            	        model.setRowCount(0); // Clear existing data
            		}
            		
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(frmStudents.this, "Vui lòng chọn file excel danh sách sinh viên");
        		}
        		
        	}
        });
        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnThem.setBounds(10, 360, 152, 33);
        contentPane.add(btnThem);
        setCboClass();
        setCboNganh();
	}
	
	private String getTextCboLop()
	{
		return cboLop.getSelectedItem().toString();
	}
	
	private String getTextCboNganh()
	{
		return cboNganh.getSelectedItem().toString();
	}
	
	private void setCboClass()
	{
		 String[] classes = studentBUS.takeInforClasses(); // Lấy danh sách các lớp từ StudentBUS
	     for (String className : classes) {
	          cboLop.addItem(className); // Thêm tên lớp vào JComboBox
	     }
	}
	
	private void setCboNganh()
	{
		 String[] classes = studentBUS.takeInforAca(); // Lấy danh sách các lớp từ StudentBUS
	     for (String className : classes) {
	          cboNganh.addItem(className); // Thêm tên lớp vào JComboBox
	     }
	}
	
	private void readExcelFile(File file) throws IOException {
	    FileInputStream fis = new FileInputStream(file);
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheetAt(0);
	    DefaultTableModel model = (DefaultTableModel) tb_ListStudent.getModel();
	    model.setRowCount(0); // Clear existing data

	    for (Row row : sheet) {
	        if (row.getRowNum() == 0) {
	            continue; // Skip header row
	        }
	        
	        boolean hasData = false;
	        for (int cn = 0; cn < 7; cn++) {
	            Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	            if (cell.getCellType() != CellType.BLANK) {
	                hasData = true;
	                break;
	            }
	        }
	        
	        if (hasData) {
	            String[] rowData = new String[7];
	            for (int cn = 0; cn < 7; cn++) {
	                Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	                rowData[cn] = cell.toString();
	            }
	            model.addRow(rowData);
	        }
	    }

	    workbook.close();
	    fis.close();
	}
}
