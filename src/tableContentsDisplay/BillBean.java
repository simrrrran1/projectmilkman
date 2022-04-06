package tableContentsDisplay;

public class BillBean {
	
	String Name;
	String StartDate;
	String EndDate;
	double CowQty;
	double	BuffaloQty;
	double AmountToBePaid;
	double CowPrice;
	double BuffaloPrice;
	double CowVar;
	double BuffaloVar;

	public BillBean(){}

	public BillBean(String name, String startDate, String endDate, double cowQty, double buffaloQty,
			double amountToBePaid, double cowPrice, double buffaloPrice, double cowVar, double buffaloVar) {
		super();
		Name = name;
		StartDate = startDate;
		EndDate = endDate;
		CowQty = cowQty;
		BuffaloQty = buffaloQty;
		AmountToBePaid = amountToBePaid;
		CowPrice = cowPrice;
		BuffaloPrice = buffaloPrice;
		CowVar = cowVar;
		BuffaloVar = buffaloVar;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public double getCowQty() {
		return CowQty;
	}

	public void setCowQty(double cowQty) {
		CowQty = cowQty;
	}

	public double getBuffaloQty() {
		return BuffaloQty;
	}

	public void setBuffaloQty(double buffaloQty) {
		BuffaloQty = buffaloQty;
	}

	public double getAmountToBePaid() {
		return AmountToBePaid;
	}

	public void setAmountToBePaid(double amountToBePaid) {
		AmountToBePaid = amountToBePaid;
	}

	public double getCowPrice() {
		return CowPrice;
	}

	public void setCowPrice(double cowPrice) {
		CowPrice = cowPrice;
	}

	public double getBuffaloPrice() {
		return BuffaloPrice;
	}

	public void setBuffaloPrice(double buffaloPrice) {
		BuffaloPrice = buffaloPrice;
	}

	public double getCowVar() {
		return CowVar;
	}

	public void setCowVar(double cowVar) {
		CowVar = cowVar;
	}

	public double getBuffaloVar() {
		return BuffaloVar;
	}

	public void setBuffaloVar(double buffaloVar) {
		BuffaloVar = buffaloVar;
	}
	

}
