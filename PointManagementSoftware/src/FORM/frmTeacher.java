package FORM;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import BUS.TeacherBUS;
import DAO.DataProvider;
import DTO.TeacherDTO;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class frmTeacher extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTenGV;
	private JTable tb_ListGV;
	private JComboBox<String> cboGT;
	private JComboBox<String> cboKhoa;
	private TeacherBUS teacherBUS;
	private JTextField txtMaGV;
	private JTable tb_ExcelGV;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frmTeacher frame = new frmTeacher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmTeacher() {
		this.setTitle("Quản lý giảng viên");
	    this.setSize(1000, 500);
		DataProvider.getInstance().connectToDatabase();
		this.teacherBUS=new TeacherBUS();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1011, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 10, 408, 267);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Họ tên:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(24, 88, 79, 29);
		panel.add(lblNewLabel);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Giới tính:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(24, 127, 79, 29);
		panel.add(lblNewLabel_2);
		
		cboGT = new JComboBox<String>();
		cboGT.setBounds(114, 132, 252, 21);
		panel.add(cboGT);
		
		JLabel lblNewLabel_3 = new JLabel("Khoa:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(24, 166, 79, 29);
		panel.add(lblNewLabel_3);
		
		cboKhoa = new JComboBox<String>();
		cboKhoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Khoa=getTextCboKhoa();
				if(Khoa.equals("Tất cả"))
				{
					loadTb_ListGVAllTeacher();
				}
				else
				{
					String idKhoa=getIdKhoa();
					loadTb_ListGTeacherByDepartID(idKhoa);
				}
			}
		});
		cboKhoa.setBounds(114, 171, 252, 21);
		panel.add(cboKhoa);
		
		
		JLabel lblNewLabel_4 = new JLabel("Nhập một giảng viên");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(97, 10, 228, 29);
		panel.add(lblNewLabel_4);
		
		// Sự kiện thêm giảng viên
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maGV=getTxtMaGV();
				String tenGV=getTxtTenGV();
				String gtinh=getTextCboGT();
				String Khoa=getTextCboKhoa();
				if(Khoa.equals("Tất cả"))
				{
					JOptionPane.showMessageDialog(frmTeacher.this, "Vui lòng chọn khoa");
					return;
				}
				String idKhoa=getIdKhoa();
				if(maGV.isEmpty() || tenGV.isEmpty())
				{
					JOptionPane.showMessageDialog(frmTeacher.this, "Vui lòng nhập đầy đủ thông tin");
					return;
				}
				else
				{
					int checkIdGV=teacherBUS.checkIdTeacher(maGV);
					if(checkIdGV==1)
					{
						JOptionPane.showMessageDialog(frmTeacher.this, "Giảng viên với mã này đã tồn tại");
						return;
					}
					else
					{
						teacherBUS.insertTeacher(maGV, tenGV, gtinh, idKhoa);
						loadTb_ListGTeacherByDepartID(idKhoa);
						JOptionPane.showMessageDialog(frmTeacher.this, "Thêm giảng viên thành công");
						clearTxtMaGV();
		        		clearTxtTenGV();
					}
					
				}
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBounds(24, 216, 101, 29);
		panel.add(btnThem);
		
		// Sự kiện nút sửa
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String maGV=getTxtMaGV();
				String tenGV=getTxtTenGV();
				String gtinh=getTextCboGT();
				String Khoa=getTextCboKhoa();
				if(Khoa.equals("Tất cả"))
				{
					JOptionPane.showMessageDialog(frmTeacher.this, "Vui lòng chọn khoa");
					return;
				}
				String idKhoa=getIdKhoa();
				if(maGV.isEmpty() || tenGV.isEmpty())
				{
					JOptionPane.showMessageDialog(frmTeacher.this, "Vui lòng nhập đầy đủ thông tin");
					return;
				}
				else
				{
					int checkIdGV=teacherBUS.checkIdTeacher(maGV);
					if(checkIdGV==0)
					{
						JOptionPane.showMessageDialog(frmTeacher.this, "Giảng viên với mã này hiện không tồn tại");
						return;
					}
					else
					{
						teacherBUS.updateTeacher(maGV, tenGV, gtinh, idKhoa);
						loadTb_ListGTeacherByDepartID(idKhoa);
						JOptionPane.showMessageDialog(frmTeacher.this, "Sửa giảng viên thành công");
						clearTxtMaGV();
		        		clearTxtTenGV();
					}
					
				}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setBounds(151, 216, 101, 29);
		panel.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String maGV=getTxtMaGV();
				
				if(maGV.isEmpty() ) {
					JOptionPane.showMessageDialog(frmTeacher.this, "Vui lòng nhập mã giảng viên để xóa");
					return;
				}
				else
				{
					int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
			        
			        
			        if (option == JOptionPane.YES_OPTION) {
			        	if(teacherBUS.checkIdTeacher(maGV)==0)
			        	{
			        		JOptionPane.showMessageDialog(frmTeacher.this, "Giảng viên với mã này hiện không tồn tại");
							return;
			        	}
			        	else
			        	{
			        		teacherBUS.deleteTeacher(maGV);
			        		JOptionPane.showMessageDialog(frmTeacher.this, "Xóa giảng viên thành công");
			        		clearTxtMaGV();
			        		clearTxtTenGV();
			        		String Khoa=getTextCboKhoa();
							if(Khoa.equals("Tất cả"))
							{
								loadTb_ListGVAllTeacher();
							}
							else
							{
								String idKhoa=getIdKhoa();
								loadTb_ListGTeacherByDepartID(idKhoa);;
							}
			        	}
			        }
				}
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBounds(278, 216, 101, 29);
		panel.add(btnXoa);
		
		txtTenGV = new JTextField();
		txtTenGV.setBounds(113, 94, 253, 19);
		panel.add(txtTenGV);
		txtTenGV.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mã:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(24, 54, 79, 29);
		panel.add(lblNewLabel_1);
		
		txtMaGV = new JTextField();
		txtMaGV.setBounds(114, 59, 252, 19);
		panel.add(txtMaGV);
		txtMaGV.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 287, 408, 301);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 388, 291);
		panel_1.add(scrollPane);
		
		tb_ListGV = new JTable();
		tb_ListGV.setEnabled(false);
		tb_ListGV.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã GV", "Họ Tên", "Giới Tính", "Ngành"
			}
		));
		scrollPane.setViewportView(tb_ListGV);
		
		JLabel lblNewLabel_5 = new JLabel("Nhập file Excel");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(428, 20, 137, 29);
		contentPane.add(lblNewLabel_5);
		
		// Sự kiện đọc file excel
		JButton btnDocExcel = new JButton("Tải file excel");
		btnDocExcel.addActionListener(new ActionListener() {
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
		btnDocExcel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDocExcel.setBounds(428, 59, 137, 29);
		contentPane.add(btnDocExcel);
		
		JLabel lblNewLabel_5_1 = new JLabel("Thêm các\n giảng viên");
		lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_5_1.setBounds(428, 98, 137, 29);
		contentPane.add(lblNewLabel_5_1);
		
		// Sự kiện thêm danh sách các giảng viên
		JButton btnThemExcelGV = new JButton("Thêm Excel GV");
		btnThemExcelGV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int count=0;
				String Khoa=getTextCboKhoa();
				if(Khoa.equals("Tất cả"))
				{
					JOptionPane.showMessageDialog(frmTeacher.this, "Vui lòng chọn một khoa ở phía bên trái");
					return;
				}
				
				String idKhoa=getIdKhoa();
				DefaultTableModel model = (DefaultTableModel) tb_ExcelGV.getModel();
        		System.out.println("Số dòng: "+model.getRowCount());
        		int row=model.getRowCount();
        		if(row>0)
        		{
        			int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm danh sách giảng viên này vào khoa "+Khoa+" ?", "Xác nhận thêm", JOptionPane.YES_NO_OPTION);
    		        if (option == JOptionPane.YES_OPTION) {
    		        	for (int i = 0; i < model.getRowCount(); i++) {
                			String tea_id = model.getValueAt(i, 0).toString();
                			int checkTeacherId=0;
                			checkTeacherId=teacherBUS.checkIdTeacher(tea_id);
                			String tenGV=model.getValueAt(i, 1).toString();
                			String gTinh=model.getValueAt(i, 2).toString();
                			if(checkTeacherId==0)
                			{
                    			// Thêm giảng viên vào cơ sở dữ liệu
                				teacherBUS.insertTeacher(tea_id, tenGV, gTinh, idKhoa);
                    			
                			}
                			else
                			{
                				count=count+1;
                			}
                			
                		}
                		if(count > 0)
                		{
                			JOptionPane.showMessageDialog(frmTeacher.this, "Đã có "+count+" giảng viên tồn tại trong danh sách");
                			loadTb_ListGTeacherByDepartID(idKhoa);
                			 model.setRowCount(0); // Clear existing data
                		}
                		else
                		{
                			JOptionPane.showMessageDialog(frmTeacher.this, "Thêm tất cả giảng viên thành công");
                			loadTb_ListGTeacherByDepartID(idKhoa);
                	        model.setRowCount(0); // Clear existing data
                		}
                		
    		        }
        			
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(frmTeacher.this, "Vui lòng chọn file excel danh sách giảng viên");
        		}
			}
		});
		btnThemExcelGV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThemExcelGV.setBounds(428, 137, 137, 29);
		contentPane.add(btnThemExcelGV);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(588, 10, 399, 578);
		contentPane.add(scrollPane_1);
		
		tb_ExcelGV = new JTable();
		tb_ExcelGV.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã GV", "Họ Tên", "Giới Tính"
			}
		));
		tb_ExcelGV.setEnabled(false);
		scrollPane_1.setViewportView(tb_ExcelGV);
		
		setCboKhoa();
		setCboGT();
		loadTb_ListGVAllTeacher();
	}
	
	private void clearTxtMaGV()
	{
		txtMaGV.setText("");
	}
	
	private void clearTxtTenGV()
	{
		txtTenGV.setText("");
	}
	
	private String getIdKhoa()
	{
		String[] split=getTextCboKhoa().split("\\.");
		return split[0];
	}
	
	private String getTextCboKhoa()
	{
		return cboKhoa.getSelectedItem().toString();
	}
	
	private void setCboKhoa()
	{
		 String[] classes = teacherBUS.takeInforDepart(); // Lấy danh sách các lớp từ StudentBUS
		 cboKhoa.addItem("Tất cả");
	     for (String className : classes) {
	          cboKhoa.addItem(className); // Thêm tên lớp vào JComboBox
	     }
	}
	
	private String getTxtMaGV()
	{
		return txtMaGV.getText();
	}
	
	private String getTxtTenGV()
	{
		return txtTenGV.getText();
	}
	
	private String getTextCboGT()
	{
		return cboGT.getSelectedItem().toString();
	}
	
	private void setCboGT()
	{
		 String[] classes = {"Nam","Nữ"}; // Lấy danh sách các lớp từ StudentBUS
	     for (String className : classes) {
	          cboGT.addItem(className); // Thêm tên lớp vào JComboBox
	     }
	}
	
	public void loadTb_ListGVAllTeacher()
	{
		List<TeacherDTO> teachers = teacherBUS.takeAllTeachers();
        DefaultTableModel model = (DefaultTableModel) tb_ListGV.getModel();
        model.setRowCount(0);
        for (TeacherDTO teacher : teachers) {
            model.addRow(new Object[]{teacher.getTeaId(), teacher.getTeaName(), teacher.getTeaSex(), teacher.getDepartId()});
        }
	}
	
	public void loadTb_ListGTeacherByDepartID(String depart_id)
	{
		List<TeacherDTO> teachers = teacherBUS.takeAllTeachersByDepartID(depart_id);
        DefaultTableModel model = (DefaultTableModel) tb_ListGV.getModel();
        model.setRowCount(0);
        for (TeacherDTO teacher : teachers) {
            model.addRow(new Object[]{teacher.getTeaId(), teacher.getTeaName(), teacher.getTeaSex(), teacher.getDepartId()});
        }
	}

	private void readExcelFile(File file) throws IOException {
	    FileInputStream fis = new FileInputStream(file);
	    Workbook workbook = new XSSFWorkbook(fis);
	    Sheet sheet = workbook.getSheetAt(0);
	    DefaultTableModel model = (DefaultTableModel) tb_ExcelGV.getModel();
	    model.setRowCount(0); // Clear existing data

	    for (Row row : sheet) {
	        if (row.getRowNum() == 0) {
	            continue; // Skip header row
	        }
	        
	        boolean hasData = false;
	        for (int cn = 0; cn < 3; cn++) {
	            Cell cell = row.getCell(cn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	            if (cell.getCellType() != CellType.BLANK) {
	                hasData = true;
	                break;
	            }
	        }
	        
	        if (hasData) {
	            String[] rowData = new String[7];
	            for (int cn = 0; cn < 3; cn++) {
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
