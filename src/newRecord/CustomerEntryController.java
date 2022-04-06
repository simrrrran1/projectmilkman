package newRecord;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import projectDatabaseConnection.Connector;

public class CustomerEntryController {

    private static final String SELECTOR = null;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtN1;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtN4;

    @FXML
    private TextField txtN3;

    @FXML
    private TextField txtN2;

    @FXML
    private DatePicker dtpStartDate;

    PreparedStatement pst1=null;
    Connection con=null;
    
    @FXML
    void doClear(ActionEvent event) {
    	
    	txtName.setText(String.valueOf(""));
    	txtMobile.setText(String.valueOf(""));
    	txtAddress.setText(String.valueOf(""));
    	txtN1.setText(String.valueOf(""));
    	txtN2.setText(String.valueOf(""));
    	txtN3.setText(String.valueOf(""));
    	txtN4.setText(String.valueOf(""));
      	dtpStartDate.getEditor().clear();
      	txtStatus.setText(String.valueOf(0));
      	
    }

    @FXML
    void doFetch(ActionEvent event) {
    	

    	String Name=txtName.getText();
    	try{
        	pst1=con.prepareStatement("select * from customerentry where Name=?");
        	pst1.setString(1,Name );
        	
        		ResultSet table= pst1.executeQuery();
        		boolean jasus=false;
        		while(table.next())
        		{
        			jasus=true;
        			String MobileNo=table.getString("MobileNo");
        			String Address=table.getString("Address");
        			double CowQuantity=table.getDouble("CowQuantity");
        			double CowPrice=table.getDouble("CowPrice");
        			double BuffaloQuantity=table.getDouble("BuffaloQuantity");
        			double BuffaloPrice=table.getDouble("BuffaloPrice");
        			String DateOfStart=table.getString("DateOfStart");
        	    	int status=table.getInt("Status");
        		
        			
        			txtName.setText(Name);
        			txtMobile.setText(MobileNo);
        			txtAddress.setText(Address);
        			txtN1.setText(String.valueOf(CowQuantity));
        			txtN2.setText(String.valueOf(CowPrice));
        			txtN3.setText(String.valueOf(BuffaloQuantity));
        			txtN4.setText(String.valueOf(BuffaloPrice));
        			dtpStartDate.getEditor().setText(DateOfStart);
        			txtStatus.setText(String.valueOf(status));
        		}
        		if(jasus==false)
        		{

        			Alert al=new Alert(AlertType.ERROR);
        			al.setTitle("Title");
        			al.setContentText("Non Existing User");
        			al.setHeaderText("Awwwwww...........");
        			al.show();
        		}
        		
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    

    }

    @FXML
    void doSave(ActionEvent event) {
    	

    	String MobileNo=txtMobile.getText();
    	String Name=txtName.getText();
    	String Address=txtAddress.getText();
    	double CowQuantity=Double.parseDouble(String.valueOf(txtN1.getText()));
    	double CowPrice=Double.parseDouble(String.valueOf(txtN2.getText()));
    	double BuffaloQuantity=Double.parseDouble(String.valueOf(txtN3.getText()));
    	double BuffaloPrice=Double.parseDouble(String.valueOf(txtN4.getText()));
    	LocalDate local=dtpStartDate.getValue();
    	java.sql.Date DateOfStart=	java.sql.Date.valueOf(local);
    	int status=Integer.parseInt(String.valueOf(txtStatus.getText()));
		try
		{
		 PreparedStatement pst1=con.prepareStatement("insert into customerentry values(?,?,?,?,?,?,?,?,?)");
		
		pst1.setString(1,Name);
		pst1.setString(2, MobileNo);
		pst1.setString(3, Address);
		pst1.setDouble(4,CowQuantity);
		pst1.setDouble(5,CowPrice);
		pst1.setDouble(6,BuffaloQuantity);
		pst1.setDouble(7,BuffaloPrice);
		pst1.setDate(8,DateOfStart);
		pst1.setInt(9,status);
		pst1.executeUpdate();

		Alert al=new Alert(AlertType.ERROR);
		al.setTitle("Title");
		al.setContentText("Successfully Added");
		al.setHeaderText("Congratulations");
		al.show();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	
    	
    	String MobileNo="";
    	if(txtMobile.getText()==null)
    	{
    		MobileNo=txtMobile.getText();
    	}
    	String Name=txtName.getText();
    	String Address="";
    	if(txtAddress.getText()==null)
    	{
    		Address=txtAddress.getText();
    	}
    	double CowQuantity=0;
    	String CowQty="";
    	if(txtN1.getText()==null)
    	{
    		CowQty=String.valueOf(txtN1.getText());
    		CowQuantity=Double.parseDouble(CowQty);
    	}
    	else
    	{
    		CowQuantity=Double.parseDouble(String.valueOf(txtN1.getText()));
    	}
    	double CowPrice=0;
    	String CowPrc="";
    	if(txtN2.getText()==null)
    	{
    		CowPrc=String.valueOf(txtN2.getText());
    		CowPrice=Double.parseDouble(CowPrc);
    	}
    	else
    	{
    		CowPrice=Double.parseDouble(String.valueOf(txtN2.getText()));
    	}
    	double BuffaloQuantity=0;
    	String BuffaloQty="";
    	if(txtN3.getText()==null)
    	{
    		BuffaloQty=String.valueOf(txtN3.getText());
    		BuffaloQuantity=Double.parseDouble(BuffaloQty);
    	}
    	else
    	{
    		BuffaloQuantity=Double.parseDouble(String.valueOf(txtN3.getText()));
    	}
    	
    	double BuffaloPrice=0;
    	String BuffaloPrc="";
    	if(txtN4.getText()==null)
    	{
    		BuffaloPrc=String.valueOf(txtN4.getText());
    		BuffaloPrice=Double.parseDouble(BuffaloPrc);
    	}
    	else
    	{
    		BuffaloPrice=Double.parseDouble(String.valueOf(txtN4.getText()));
    	}
    	
    	int status=0;
        String Status="";
    	if(txtStatus.getText()==null)
    	{
    		Status=String.valueOf(txtStatus.getText());
    		Integer.parseInt(String.valueOf(Status));
    	}
    	else
    	{
    		status=Integer.parseInt(String.valueOf(txtStatus.getText()));
    	}
    	
    	String startDate="";
    	LocalDate local;
    	if(dtpStartDate.getValue()==null){
    		
    		startDate=dtpStartDate.getEditor().getText();
    		local=LocalDate.parse(startDate);
    	}
    	else
    	{
    		local=dtpStartDate.getValue();
    	}
    	java.sql.Date DateOfStart=	java.sql.Date.valueOf(local);
		try
		{
		 PreparedStatement pst1=con.prepareStatement("update customerentry set CowQuantity=?,BuffaloQuantity=?,Status=?,CowPrice=?,BuffaloPrice=?,DateOfStart=? where Name=?");
		 
		pst1.setDouble(1,CowQuantity);
		pst1.setDouble(2,BuffaloQuantity);
		pst1.setInt(3,status);
		pst1.setDouble(4,CowPrice);
		pst1.setDouble(5,BuffaloPrice);
		pst1.setDate(6,DateOfStart);
		pst1.setString(7,Name);
		
		int count=pst1.executeUpdate();
		System.out.println(count+" Records Updated");
		Alert al=new Alert(AlertType.ERROR);
		al.setTitle("Title");
		al.setContentText("Updation Successful");
		al.setHeaderText("Congratulations");
		al.show();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
    }
    
    

    

    @FXML
    void initialize() {
    	
    	con=Connector.doConnect();
    }
}
