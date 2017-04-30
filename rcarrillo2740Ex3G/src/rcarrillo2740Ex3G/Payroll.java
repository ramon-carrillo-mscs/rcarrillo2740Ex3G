package rcarrillo2740Ex3G;
import java.text.DecimalFormat;


public class Payroll {
	private int id;
	private String name;
	private double payRate;
	private double hours = 0.0;
	private double calcGrossPay;
	private double overtimePay;
	
	
	public Payroll(int id, String name, double payRate) {
		
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = 0.00;
	}
	
public Payroll(int id, String name, double payRate, double hours) {
		
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = hours;
	}
	
	public int getId() {
		return this.id;
	}
	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}
	public String getName() {
		return this.name;
	}
	
	public boolean setName(String name) {
		if (name.isEmpty())
		{	
			return false;
		}
		else
		{
			this.name = name;
			return true;
		}
	}
	
	public double getPayRate() {
		return this.payRate;
	}
	public boolean setPayRate(double payRate) {
		if (payRate <= 7.25 && payRate >= 100.0) {
			this.payRate = payRate;
			return true;
		}
		else 
		{
			return false;
		}
			
	}
	public double getHours() {
		return this.hours;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	@Override
	public String toString() {
		DecimalFormat fmt = new DecimalFormat("#,###.00");
		return Integer.toString(getId()) + "  " + this.name + "  " + fmt.format(this.getPayRate());
	}
	
	public double calcGrossPay () {
				
		if (hours > 40){
			calcGrossPay = 40 * payRate;
			overtimePay = (hours - 40) * (payRate * 1.5);
			calcGrossPay += overtimePay;
		}
		else
		{
			calcGrossPay = payRate * hours;
		}
		return this.calcGrossPay;
	}
	public boolean addHours(double hours){
		if (hours >= 0.1 && hours <= 100.0) {
			this.hours += hours;
			return true;
		}
		else {
			return false;
		}
		
	}
	
/*
	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}
 */

}