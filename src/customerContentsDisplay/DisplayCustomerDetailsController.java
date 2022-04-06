package customerContentsDisplay;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import projectDatabaseConnection.Connector;
import variationLog.VariationBean;
import customerContentsDisplay.CustomerBean;

public class DisplayCustomerDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<CustomerBean> tblRecords;

    @FXML
    private RadioButton radBoth;

    @FXML
    private ToggleGroup Milk;

    @FXML
    private RadioButton radBuffalo;

    @FXML
    private RadioButton radCow;

    @FXML
    private DatePicker dtp1;
    
    Connection con=null;
    PreparedStatement pst1=null;

    @FXML
    void doFetchRecordOfSelectedDate(ActionEvent event) {
    	
    	LocalDate local=dtp1.getValue();
    	java.sql.Date Date=	java.sql.Date.valueOf(local);
    	String date=String.valueOf(Date);
    	
    	try {
			
      		 pst1=con.prepareStatement("select * from customerentry where DateOfStart=?");
      		 pst1.setString(1,date);
      		
      		 fetchAllRecords(pst1);
          	 
      	    tblRecords.setItems(list);
   		} 
      	 catch (SQLException e) 
      	 {
   			
   			e.printStackTrace();
   		}  	

    }


    ObservableList<CustomerBean> list;
    

    void fetchAllRecords(PreparedStatement pst1)
    {
    	list=FXCollections.observableArrayList();
    	try{
        
        	ResultSet table= pst1.executeQuery();
        		
        		while(table.next())
        		{
        			String name=table.getString("Name");
        			String mobileNo=table.getString("MobileNo");
        			String address=table.getString("Address");
        			double cowQuantity=table.getDouble("CowQuantity");
        			double cowPrice=table.getDouble("CowPrice");
        			double buffaloQuantity=table.getDouble("BuffaloQuantity");
        			double buffaloPrice=table.getDouble("BuffaloPrice");
        			String dateOfStart=table.getString("DateOfStart");
        			CustomerBean sb=new CustomerBean(name,mobileNo,address,cowQuantity,cowPrice,buffaloQuantity,buffaloPrice,dateOfStart); 
        			list.add(sb);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	
    }
    

    
    @FXML
    void doSearchSelected(ActionEvent event) {
    	
    	if(radCow.isSelected()==true|| radBuffalo.isSelected()==true || radBoth.isSelected()==true)
    		tryCatchMania();
    
    }
    
    void tryCatchMania()
    {

    	try {
 			
   	     pst1=con.prepareStatement("select * from customerentry");
   		
   		 fetchAllRecords(pst1);
       	 
   		tblRecords.setItems(list);
		} 
   	 catch (SQLException e) 
   	 {
			
			e.printStackTrace();
		}

    }

    @FXML
    void doViewAll(ActionEvent event) {

      	 try {
   			
      		 pst1=con.prepareStatement("select * from customerentry");
      		
      		 fetchAllRecords(pst1);
          	 
      	    tblRecords.setItems(list);
   		} 
      	 catch (SQLException e) 
      	 {
   			
   			e.printStackTrace();
   		}  	

    }
    
    void fillColumns()
    {
    	TableColumn<CustomerBean, String> name=new TableColumn<CustomerBean, String>("Name");//Dikhava Title
     	name.setCellValueFactory(new PropertyValueFactory<>("name"));//bean field name, no link with table col name


    	TableColumn<CustomerBean, String> mobileNo=new TableColumn<CustomerBean, String>("Mobile No");//Dikhava Title
    	mobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));//bean field name, no link with table col name
     	
     	TableColumn<CustomerBean, String>  address=new TableColumn<CustomerBean, String>("Address");//Dikhava Title
     	address.setCellValueFactory(new PropertyValueFactory<>("address"));//bean field name, no link with table col name
     	
     	TableColumn<CustomerBean, Double> cowQuantity=new TableColumn<CustomerBean, Double>("Reg Cow Milk Qty");//Dikhava Title
     	cowQuantity.setCellValueFactory(new PropertyValueFactory<>("cowQuantity"));//bean field name, no link with table col name
     	
     	TableColumn<CustomerBean, Double> cowPrice=new TableColumn<CustomerBean, Double>("CowMilk Price/kg");//Dikhava Title
     	cowPrice.setCellValueFactory(new PropertyValueFactory<>("cowPrice"));//bean field name, no link with table col name
     	
     	
     	TableColumn<CustomerBean, Double> buffaloQuantity=new TableColumn<CustomerBean, Double>("Reg Buffalo Milk Qty");//Dikhava Title
     	buffaloQuantity.setCellValueFactory(new PropertyValueFactory<>("buffaloQuantity"));//bean field name, no link with table col name
     	
       	TableColumn<CustomerBean, Double> buffaloPrice=new TableColumn<CustomerBean, Double>("BuffaloMilk Price/kg");//Dikhava Title
     	buffaloPrice.setCellValueFactory(new PropertyValueFactory<>("buffaloPrice"));//bean field name, no link with table col name
     	
     	TableColumn<CustomerBean, String>  dateOfStart=new TableColumn<CustomerBean, String>("Date Of Start");//Dikhava Title
     	dateOfStart.setCellValueFactory(new PropertyValueFactory<>("dateOfStart"));//bean field name, no link with table col name
     	
     	tblRecords.getColumns().clear();
     	tblRecords.getColumns().addAll(name,mobileNo,address,cowQuantity,cowPrice,buffaloQuantity,buffaloPrice,dateOfStart);
    }

    

    @FXML    
    void fillNames()
    {
    	ArrayList<String> namesAry=new ArrayList<>();
    	try{
    		pst1=con.prepareStatement("select distinct DateOfStart from customerentry");
    		ResultSet table= pst1.executeQuery();
        		
        		while(table.next())
        		{
        			String dateOfStart=table.getString("DateOfStart");//col. name acc. to table
        			namesAry.add(dateOfStart);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	
    	dtp1.getEditor().setUserData(namesAry);;
    }



    @FXML
    void doViewInExcel(ActionEvent event)throws Exception {
    	
    	writeExcel();

    }
    
    @FXML
    public void writeExcel() throws Exception {
        Writer writer = null;
        try {
        	FileChooser chooser=new FileChooser();
	    	
        	chooser.setTitle("Select Path:");
        	
        	chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.*")
                    
                );
        	 File file=chooser.showSaveDialog(null);
        	String filePath=file.getAbsolutePath();
        	if(!(filePath.endsWith(".csv")||filePath.endsWith(".CSV")))
        	{
        		filePath=filePath+".csv";
        	}
        	 file = new File(filePath);
        	 
        	 
        	 
            writer = new BufferedWriter(new FileWriter(file));
            String text="Name,Mobile No,Address,Regular cow Milk Qty,Cow Milk Price/kg,Regular buffalo Milk Qty,Buffalo Milk Price/kg,Date of Start\n";
            writer.write(text);
            for (CustomerBean p : list)
            {
				text = p.getName()+ "," + p.getMobileNo()+ "," + p.getAddress()+ "," + p.getCowQuantity()+","+p.getCowPrice()+","+p.getBuffaloQuantity()+","+p.getBuffaloPrice()+","+p.getDateOfStart()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }
    

    @FXML
    void initialize() {
    	
    	con=Connector.doConnect();
    	fillNames();
    	fillColumns();
    	

    }
}
