package variationLog;

public class VariationBean {
	
	String Name;
	double CowQuantity;
	double BuffaloQuantity;
	String Date;
	
	public VariationBean(String name, double cowQuantity, double buffaloQuantity, String date) {
		super();
		Name = name;
		CowQuantity = cowQuantity;
		BuffaloQuantity = buffaloQuantity;
		Date = date;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public double getCowQuantity() {
		return CowQuantity;
	}
	public void setCowQuantity(double cowQuantity) {
		CowQuantity = cowQuantity;
	}
	public double getBuffaloQuantity() {
		return BuffaloQuantity;
	}
	public void setBuffaloQuantity(double buffaloQuantity) {
		BuffaloQuantity = buffaloQuantity;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}


	
	


}
