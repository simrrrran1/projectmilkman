package customerContentsDisplay;

public class CustomerBean {

	String Name;
    String	MobileNo;
	String Address;
	double CowQuantity;
	double CowPrice;
	double BuffaloQuantity;
	double BuffaloPrice;
	String DateOfStart;
	public CustomerBean(String name, String mobileNo, String address, double cowQuantity, double cowPrice,
			double buffaloQuantity, double buffaloPrice, String dateOfStart) {
		super();
		Name = name;
		MobileNo = mobileNo;
		Address = address;
		CowQuantity = cowQuantity;
		CowPrice = cowPrice;
		BuffaloQuantity = buffaloQuantity;
		BuffaloPrice = buffaloPrice;
		DateOfStart = dateOfStart;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public double getCowQuantity() {
		return CowQuantity;
	}
	public void setCowQuantity(double cowQuantity) {
		CowQuantity = cowQuantity;
	}
	public double getCowPrice() {
		return CowPrice;
	}
	public void setCowPrice(double cowPrice) {
		CowPrice = cowPrice;
	}
	public double getBuffaloQuantity() {
		return BuffaloQuantity;
	}
	public void setBuffaloQuantity(double buffaloQuantity) {
		BuffaloQuantity = buffaloQuantity;
	}
	public double getBuffaloPrice() {
		return BuffaloPrice;
	}
	public void setBuffaloPrice(double buffaloPrice) {
		BuffaloPrice = buffaloPrice;
	}
	public String getDateOfStart() {
		return DateOfStart;
	}
	public void setDateOfStart(String dateOfStart) {
		DateOfStart = dateOfStart;
	}
	
	

}
