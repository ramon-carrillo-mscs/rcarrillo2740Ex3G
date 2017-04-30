package rcarrillo2740Ex3G;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame {

	private Payroll employee;
	private JPanel contentPane;
	private JTextField hoursTextField;
	private JList employeeList;
	private DefaultListModel employeeListModel;
	private JLabel totalHoursLabel;
	private JLabel grossPayLabel;
	private JTextField idTextField;
	private JTextField empNameTextField;
	private JTextField payRateTextField;
	private JButton btnUpdate;
	private JButton btnaddHours;
	private JButton btnClear;
	private PayrollObjMapper payrollObjMapper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
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
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				do_this_windowClosing(e);
			}
		});
		setTitle("rcarrillo2740Ex3G");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Employee");
		lblNewLabel.setBounds(10, 25, 128, 21);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 57, 414, 159);
		contentPane.add(scrollPane);
		
//		employeeListModel = new DefaultListModel();
//		employeeListModel.addElement(new Payroll(101, "Ramon Carrillo", 10.0));
//		employeeListModel.addElement(new Payroll(102, "Patti Weigand", 20.0));
//		employeeListModel.addElement(new Payroll(103, "Lyle Stelter", 30.0));
//		employeeListModel.addElement(new Payroll(104, "Neva Burdick", 40.0));
//		employeeListModel.addElement(new Payroll(105, "Lisa Laing", 50.0));
		payrollObjMapper = new PayrollObjMapper("exercise3g.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList(employeeListModel);		
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(employeeList);
		
		JLabel lblEmployeeId = new JLabel("Employee ID (>100)");
		lblEmployeeId.setBounds(10, 240, 128, 21);
		contentPane.add(lblEmployeeId);
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setBounds(10, 272, 95, 21);
		contentPane.add(lblEmployeeName);
		
		JLabel lblPayRate = new JLabel("Pay Rate (7.25-100)");
		lblPayRate.setBounds(10, 304, 137, 21);
		contentPane.add(lblPayRate);
		
		JLabel lblEnterHours = new JLabel("Enter Hours (.01 - 20)");
		lblEnterHours.setBounds(10, 336, 128, 21);
		contentPane.add(lblEnterHours);
		
		JLabel lblTotalHours = new JLabel("Total Hours");
		lblTotalHours.setBounds(10, 368, 95, 21);
		contentPane.add(lblTotalHours);
		
		JLabel lblGrossPay = new JLabel("Gross Pay");
		lblGrossPay.setBounds(10, 400, 95, 21);
		contentPane.add(lblGrossPay);
		
		hoursTextField = new JTextField();
		hoursTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				do_hoursTextField_focusGained(e);
			}
		});
		hoursTextField.setText("0");
		hoursTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		hoursTextField.setBounds(157, 336, 128, 20);
		contentPane.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		totalHoursLabel = new JLabel("0");
		totalHoursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLabel.setBounds(199, 366, 86, 24);
		contentPane.add(totalHoursLabel);
		
		grossPayLabel = new JLabel("$0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(202, 398, 83, 24);
		contentPane.add(grossPayLabel);
		
		JButton btnclearForm = new JButton("Clear Form");
		btnclearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnclearForm_actionPerformed(e);
			}
		});
		btnclearForm.setBounds(295, 433, 108, 23);
		contentPane.add(btnclearForm);
		
		btnaddHours = new JButton("+");
		btnaddHours.setEnabled(false);
		btnaddHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnaddHours_actionPerformed(arg0);
			}
		});
		btnaddHours.setBounds(295, 335, 44, 23);
		contentPane.add(btnaddHours);
		
		btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnClear_actionPerformed(arg0);
			}
		});
		btnClear.setBounds(350, 335, 89, 23);
		contentPane.add(btnClear);
		
		idTextField = new JTextField();
		idTextField.setText("000");
		idTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		idTextField.setColumns(10);
		idTextField.setBounds(157, 240, 128, 20);
		contentPane.add(idTextField);
		
		empNameTextField = new JTextField();
		empNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		empNameTextField.setColumns(10);
		empNameTextField.setBounds(157, 272, 128, 20);
		contentPane.add(empNameTextField);
		
		payRateTextField = new JTextField();
		payRateTextField.setText("7.25");
		payRateTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		payRateTextField.setColumns(10);
		payRateTextField.setBounds(157, 304, 128, 20);
		contentPane.add(payRateTextField);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnUpdate_actionPerformed(arg0);
			}
		});
		btnUpdate.setBounds(177, 433, 108, 23);
		contentPane.add(btnUpdate);
	}
	
	protected void do_employeeList_mouseClicked(MouseEvent arg0) {
		employee = (Payroll) employeeList.getSelectedValue();
		this.idTextField.setText (Integer.toString(employee.getId()));
		this.empNameTextField.setText (employee.getName());
		DecimalFormat payfmt = new DecimalFormat("$#,###.00");
		this.payRateTextField.setText(payfmt.format(employee.getPayRate()));
		DecimalFormat hoursfmt = new DecimalFormat ("0.00");
		this.totalHoursLabel.setText(hoursfmt.format(employee.getHours()));		
		DecimalFormat grossfmt = new DecimalFormat ("$####.00");
		this.grossPayLabel.setText(grossfmt.format(employee.calcGrossPay()));		
		this.hoursTextField.requestFocus();
		this.btnUpdate.setEnabled(true);
		this.btnaddHours.setEnabled(true);
		this.btnClear.setEnabled(true);
	}
	
	protected void do_btnaddHours_actionPerformed(ActionEvent arg0) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		double hours = Double.parseDouble(this.hoursTextField.getText());
		
		if (payroll.addHours(hours)) {
			DecimalFormat hoursfmt = new DecimalFormat("####0");
			this.totalHoursLabel.setText(hoursfmt.format(payroll.getHours()));
			DecimalFormat grossfmt = new DecimalFormat ("$#,##0.00");
			this.grossPayLabel.setText(grossfmt.format(payroll.calcGrossPay()));
			this.hoursTextField.setText("00");
			this.hoursTextField.requestFocus();			
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Invalid hours. \nMust be between 0.1 and 20.");
		}        
	}	
		
	protected void do_btnClear_actionPerformed(ActionEvent arg0) {
		this.hoursTextField.setText("0");
		this.hoursTextField.requestFocus();
	}
	
	protected void do_btnclearForm_actionPerformed(ActionEvent e) {
		this.idTextField.setText("");
		this.empNameTextField.setText("");
		this.payRateTextField.setText("$0.00");
		this.hoursTextField.setText("0");
		this.totalHoursLabel.setText("0");
		this.grossPayLabel.setText("$0.00");
		this.hoursTextField.requestFocus();
		this.employeeList.clearSelection();
		this.btnUpdate.setEnabled(false);
		this.btnaddHours.setEnabled(false);
		this.btnClear.setEnabled(false);
	}
	
	protected void do_hoursTextField_focusGained(FocusEvent e) {
		this.hoursTextField.selectAll();
	}
	protected void do_btnUpdate_actionPerformed(ActionEvent arg0) {
		int id = Integer.parseInt(idTextField.getText());
		double rate = Double.parseDouble(payRateTextField.getText());
		double hours = Double.parseDouble(hoursTextField.getText());
		String name = empNameTextField.getText();
		Payroll payroll = (Payroll)employeeList.getSelectedValue();
		
		if (!payroll.setId(id)) 
		{
			JOptionPane.showMessageDialog(null, "Invalid employee ID. \nMust be > 100");
			idTextField.setText(Integer.toString(payroll.getId()));
			idTextField.requestFocus();
		}
		
			else if (!payroll.setPayRate(rate)) 
			{
				JOptionPane.showMessageDialog(null, "Invalid Pay Rate. \nMust be >= 7.25 and <= 100.00");
				DecimalFormat payfmt = new DecimalFormat("$#,###.00");
				payRateTextField.setText(payfmt.format(payroll.getPayRate()));
				payRateTextField.requestFocus();
			}		
		
			else if (!payroll.setName(name)) 
			{
				JOptionPane.showMessageDialog(null, "Invalid Name. \nName is required");
				empNameTextField.setText(payroll.getName());
				empNameTextField.requestFocus();
			}
		
		employeeList.repaint();
	}
	
	
	protected void do_this_windowClosing(WindowEvent e) {
		if (payrollObjMapper != null)
			payrollObjMapper.writeAllPayroll(employeeListModel);
	}


	
}


