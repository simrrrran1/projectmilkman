package dailyRecord;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import projectDatabaseConnection.Connector;

public class RegularEntryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> lstNames;

    @FXML
    private ScrollBar scrollLeftRight;

    @FXML
    private ScrollBar scrollUpDown;

    @FXML
    private TextField txtBuffaloQty;

    @FXML
    private TextField txtCowQty;

    @FXML
    private DatePicker dtpDate;

    
    
    
    Connection con=null;
    PreparedStatement pst1=null;

    
    @FXML
    void doDragRightLeft(MouseEvent event) {

    }

    @FXML
    void doDropUpDown(MouseEvent event) {

    }

    @FXML
    void doFetchRecord(MouseEvent event) {
    	
    	if(event.getClickCount()==1)
    	{
    		String Name=lstNames.getSelectionModel().getSelectedItem();
    		
    		 	try{
            	pst1=con.prepareStatement("select * from customerentry where Name=?");
            	pst1.setString(1,Name);
            	
            	ResultSet table= pst1.executeQuery();//one or zero possibility
            		
            		boolean jasus=false;
            		while(table.next())
            		{
            			jasus=true;
            			double CowQty=table.getDouble("CowQuantity");
            			double BuffaloQty=table.getDouble("BuffaloQuantity");
            			String VarDate=table.getString("DateOfStart");
            			txtCowQty.setText(String.valueOf(CowQty));
            			txtBuffaloQty.setText(String.valueOf(BuffaloQty));
            			dtpDate.getEditor().setText(VarDate);
            		   
            	}
            		if(jasus==false)
            		{
            			Alert al=new Alert(AlertType.ERROR);
            			al.setTitle("Title");
            			al.setContentText("Try Again");
            			al.setHeaderText("Awwwww.........");
            			al.show();
            		}
            		
            	}
            	catch(Exception ex)
            	{
            		ex.printStackTrace();
            	}
        


    	}
	

    }

    
    @FXML
    void doSaveInDB(ActionEvent event) {

    	String Name=lstNames.getSelectionModel().getSelectedItem();
    	double CowQuantity=Double.parseDouble(String.valueOf(txtCowQty.getText()));
    	double BuffaloQuantity=Double.parseDouble(String.valueOf(txtBuffaloQty.getText()));
    	LocalDate local=dtpDate.getValue();
    	java.sql.Date VarDate=	java.sql.Date.valueOf(local);
    	
		try
		{
		 PreparedStatement pst1=con.prepareStatement("insert into regularentry values(?,?,?,?)");
		
		pst1.setString(1,Name);
		pst1.setDouble(2,CowQuantity);
		pst1.setDouble(3,BuffaloQuantity);
		pst1.setDate(4,VarDate);
		pst1.executeUpdate();//fire query in table

		Alert al=new Alert(AlertType.ERROR);
		al.setTitle("Title");
		al.setContentText("Variation Saved Successfully");
		al.setHeaderText("Congratulations");
		al.show();
			{
        	int index = lstNames.getSelectionModel().getSelectedIndex();
        	

        if (index >= 0) {
            lstNames.getItems().remove(index);
        }
        	}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
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
    	lstNames.getItems().addAll(namesAry);
    }


    @FXML
    void initialize() {
    	con=Connector.doConnect();
    	fillNames();
    	
    }
}
