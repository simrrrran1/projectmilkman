package billingRecord;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projectDatabaseConnection.Connector;


public class BillingSoftwareController {

	 @FXML
	 private TextField txtStatus;

    @FXML
    private Label lblDays;
    
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> lstAddNames;

    @FXML
    private TextField txt6;

    @FXML
    private TextField txt5;

    @FXML
    private TextField txt4;

    @FXML
    private TextField txt2;

    @FXML
    private TextField txt3;

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt7;

    @FXML
    private DatePicker dtp2;

    @FXML
    private DatePicker dtp1;

    PreparedStatement pst1=null;
    Connection con=null;
    
    @FXML
    void doCalculateBill(ActionEvent event) {
    	
    	int days=Integer.parseInt(lblDays.getText());
    	double cqty=Double.parseDouble(txt1.getText());
    	double cprice=Double.parseDouble(txt2.getText());
    	double bqty=Double.parseDouble(txt3.getText());
    	double bprice=Double.parseDouble(txt4.getText());
    	double cVar=Double.parseDouble(txt5.getText());
    	double bVar=Double.parseDouble(txt6.getText());
    	
    	double totalBill=days*((cqty*cprice)+(bqty*bprice));
    	double netBill=totalBill-((cVar*cprice)+(bVar*bprice));
    	
    	txt7.setText(String.valueOf(netBill));
    	

    }
    

    @FXML
    void doFetchRecordsFromNDB(ActionEvent event) {  	
    	
    	String Name=lstAddNames.getSelectionModel().getSelectedItem();
    	double CowQuantity=0;
    	double BuffaloQuantity=0;
    	LocalDate local=dtp1.getValue();
    	java.sql.Date startDate=Date.valueOf(local);
    	LocalDate local1=dtp2.getValue();
    	java.sql.Date endDate=Date.valueOf(local1);
    	
	 	try{
    	pst1=con.prepareStatement("select sum(CowQuantity) as CowQty,sum(BuffaloQuantity) as BuffaloQty from regularentry  where Date>=? && Date<=? and Name=?  ");
    	pst1.setDate(1,startDate);
    	pst1.setDate(2,endDate);
    	pst1.setString(3,Name);
    	ResultSet table= pst1.executeQuery();//one or zero possibility
    		
    		boolean jasus=false;
    		while(table.next())
    		{
    			jasus=true;
    			double CowQty=table.getDouble("CowQty");
    			double BuffaloQty=table.getDouble("BuffaloQty");
    			txt5.setText(String.valueOf(CowQty));
    			txt6.setText(String.valueOf(BuffaloQty));
    			
    	}
    		if(jasus==false)
    		{
    			Alert al=new Alert(AlertType.ERROR);
    			al.setTitle("Title");
    			al.setContentText("");
    			al.setHeaderText("Try Again");
    			al.show();
    		}
    		
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}

    }

    @FXML
    void doGetNoOfDays(ActionEvent event) throws ParseException {
    	
    	LocalDate StartDate=dtp1.getValue();
    	LocalDate EndDate=dtp2.getValue();
    	int date1=StartDate.getDayOfYear();
    	int date2 = EndDate.getDayOfYear();
    	int diff=Math.abs(date2-date1);
    	lblDays.setText(String.valueOf(diff));   	
    	

    }

    @FXML
    void doSavePaymentRecords(ActionEvent event) {
    	
    	
    	String Name=lstAddNames.getSelectionModel().getSelectedItem();
		double CowQuantity=Double.parseDouble(String.valueOf(txt1.getText()));
    	double CowPrice=Double.parseDouble(String.valueOf(txt2.getText()));
    	double BuffaloQuantity=Double.parseDouble(String.valueOf(txt3.getText()));
    	double BuffaloPrice=Double.parseDouble(String.valueOf(txt4.getText()));
		double CowVar=Double.parseDouble(String.valueOf(txt5.getText()));
		double BuffaloVar=Double.parseDouble(String.valueOf(txt6.getText()));
		double Amount=Double.parseDouble(String.valueOf(txt7.getText()));
		int Status=Integer.parseInt(String.valueOf(txtStatus.getText()));

    	String startDate="";
    	LocalDate local=null;
    	if(dtp1.getValue()==null){
    		
    		startDate=dtp1.getEditor().getText();
    		local=LocalDate.parse(startDate);
    	}
    	else
    	{
    		local=dtp1.getValue();
    	}
    	java.sql.Date StartDate=	java.sql.Date.valueOf(local);

    	String endDate="";
    	LocalDate local1 = null;
    	if(dtp2.getValue()==null){
    		
    		endDate=dtp2.getEditor().getText();
    		local1=LocalDate.parse(endDate);
    	}
    	else
    	{
    		local1=dtp2.getValue();
    	}
    	java.sql.Date EndDate=	java.sql.Date.valueOf(local1);
    	
		try
		{
		 PreparedStatement pst1=con.prepareStatement("insert into bills values(?,?,?,?,?,?,?,?,?,?,?)");
		
		pst1.setString(1,Name);
		pst1.setDate(2,StartDate);
		pst1.setDate(3,EndDate);
		pst1.setDouble(4,CowQuantity);
		pst1.setDouble(5,BuffaloQuantity);
		pst1.setDouble(6,Amount);
		pst1.setDouble(7,CowPrice);
		pst1.setDouble(8,BuffaloPrice);
		pst1.setDouble(9,CowVar);
		pst1.setDouble(10,BuffaloVar);
		pst1.setInt(11,Status);
		pst1.executeUpdate();//fire query in table

		Alert al=new Alert(AlertType.ERROR);
		al.setTitle("Title");
		al.setContentText("You have successfully saved the Bill");
		al.setHeaderText("Congratulations");
		al.show();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
    }

    
    @FXML
    void doFetchRecords(MouseEvent event) {
    	
    	if(event.getClickCount()==1)
    	{

    		String Name=lstAddNames.getSelectionModel().getSelectedItem();
    		
    		 	try{
            	pst1=con.prepareStatement("select * from customerentry where Name=?");
            	pst1.setString(1,Name);
            		ResultSet table= pst1.executeQuery();//one or zero possibility
            		
            		boolean jasus=false;
            		while(table.next())
            		{
            			jasus=true;
            			double CowQuantity=table.getDouble("CowQuantity");
            			double CowPrice=table.getDouble("CowPrice");
            			double BuffaloQuantity=table.getDouble("BuffaloQuantity");
            			double BuffaloPrice=table.getDouble("BuffaloPrice");          			
                        txt1.setText(String.valueOf(CowQuantity));
            			txt2.setText(String.valueOf(CowPrice));
            			txt3.setText(String.valueOf(BuffaloQuantity));
            			txt4.setText(String.valueOf(BuffaloPrice));
            			           		   
            	}
            		if(jasus==false)
            		{

            			Alert al=new Alert(AlertType.ERROR);
            			al.setTitle("Title");
            			al.setContentText("Try Again");
            			al.setHeaderText("Awwwwww.......");
            			al.show();            		}
            		
            	}
            	catch(Exception ex)
            	{
            		ex.printStackTrace();
            	}

    	}
    	
    	

    }
    
    @FXML    
    void fillNames()
    {
    	ArrayList<String> namesAry=new ArrayList<>();
    	try{
        	pst1=con.prepareStatement("select distinct Name from customerentry");
        	ResultSet table= pst1.executeQuery();
        		
        		while(table.next())
        		{
        			String Name=table.getString("Name");//col. name acc. to table
        			namesAry.add(Name);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	lstAddNames.getItems().addAll(namesAry);
    }
    
    @FXML
    void doSendSMS(MouseEvent event) {

    	if(event.getClickCount()==1)
    	{
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("sms/SendSMS.fxml")); 
			Scene scene = new Scene(root,600,600);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//to hide the opened window
			 //	Scene scene1=(Scene)txtMobile.getScene();
			   //scene1.getWindow().hide();
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    }
  }
    
    @FXML
    void initialize() {
    	con=Connector.doConnect();
    	fillNames();
    	
    }
}
