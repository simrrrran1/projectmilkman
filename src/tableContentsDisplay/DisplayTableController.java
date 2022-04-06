package tableContentsDisplay;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import customerContentsDisplay.CustomerBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import projectDatabaseConnection.Connector;

public class DisplayTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private RadioButton radPending;

    @FXML
    private ToggleGroup PaymentStatus;

    @FXML
    private RadioButton radPaid;

    @FXML
    private ComboBox<String> comboNames;


    @FXML
    private TableView<BillBean> tblBillRecord;

    Connection con=null;
    PreparedStatement pst1=null;
    
    @FXML
    void doFetchAllRecords(ActionEvent event) {
    	

   	 try {
			
   		 pst1=con.prepareStatement("select * from bills");
   		
   		 fetchAllRecords(pst1);
       	 
   	    tblBillRecord.setItems(list);
		} 
   	 catch (SQLException e) 
   	 {
			
			e.printStackTrace();
		}  	

    }
    

    ObservableList<BillBean> list;
    
    void fetchAllRecords(PreparedStatement pst1)
    {
    	list=FXCollections.observableArrayList();
    	try{
        
        	ResultSet table= pst1.executeQuery();
        		
        		while(table.next())
        		{
        			String name=table.getString("CustomerName");
        			String startDate=table.getString("StartDate");
        			String endDate=table.getString("EndDate");
        			double cowQty=table.getDouble("CowQty");
        			double buffaloQty=table.getDouble("BuffaloQty");
        			double amountToBePaid=table.getDouble("AmountToBePaid");
        			double cowPrice=table.getDouble("CowPrice");
        			double buffaloPrice=table.getDouble("BuffaloPrice");
        			double cowVar=table.getDouble("CowVar");
        			double buffaloVar=table.getDouble("BuffaloVar");
        			BillBean sb=new BillBean(name,startDate,endDate,cowQty,buffaloQty,amountToBePaid,cowPrice,buffaloPrice,cowVar,buffaloVar); 
        			list.add(sb);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	
    }
    void fillColumns()
    {
    	TableColumn<BillBean, String> name=new TableColumn<BillBean, String>("Name");//Dikhava Title
     	name.setCellValueFactory(new PropertyValueFactory<>("name"));//bean field name, no link with table col name


    	TableColumn<BillBean, String> startDate=new TableColumn<BillBean, String>("Start Date");//Dikhava Title
     	startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));//bean field name, no link with table col name
     	
     	TableColumn<BillBean, String> endDate=new TableColumn<BillBean, String>("End Date");//Dikhava Title
     	endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));//bean field name, no link with table col name
     	
     	TableColumn<BillBean, Double> cowQty=new TableColumn<BillBean, Double>("Reg Cow Milk Qty");//Dikhava Title
     	cowQty.setCellValueFactory(new PropertyValueFactory<>("cowQty"));//bean field name, no link with table col name
     	
     	TableColumn<BillBean, Double> buffaloQty=new TableColumn<BillBean, Double>("Reg Buffalo Milk Qty");//Dikhava Title
     	buffaloQty.setCellValueFactory(new PropertyValueFactory<>("buffaloQty"));//bean field name, no link with table col name
     	
     	TableColumn<BillBean, Double> amountToBePaid=new TableColumn<BillBean, Double>("Total Bill");//Dikhava Title
     	amountToBePaid.setCellValueFactory(new PropertyValueFactory<>("amountToBePaid"));//bean field name, no link with table col name
     	
     	TableColumn<BillBean, Double> cowPrice=new TableColumn<BillBean, Double>("CowMilk Price/kg");//Dikhava Title
     	cowPrice.setCellValueFactory(new PropertyValueFactory<>("cowPrice"));//bean field name, no link with table col name
     	
     	TableColumn<BillBean, Double> buffaloPrice=new TableColumn<BillBean, Double>("BuffaloMilk Price/kg");//Dikhava Title
     	buffaloPrice.setCellValueFactory(new PropertyValueFactory<>("buffaloPrice"));//bean field name, no link with table col name
     	
     	TableColumn<BillBean, Double> cowVar=new TableColumn<BillBean, Double>("Adjustment(Cow Milk)");//Dikhava Title
     	cowVar.setCellValueFactory(new PropertyValueFactory<>("cowVar"));//bean field name, no link with table col name
     	
     	TableColumn<BillBean, Double> buffaloVar=new TableColumn<BillBean, Double>("Adjustment(Buffalo Milk)");//Dikhava Title
     	buffaloVar.setCellValueFactory(new PropertyValueFactory<>("buffaloVar"));//bean field name, no link with table col name
     	
     	tblBillRecord.getColumns().clear();
     	tblBillRecord.getColumns().addAll(name,startDate,endDate,cowQty,buffaloQty,amountToBePaid,cowPrice,buffaloPrice,cowVar,buffaloVar);
    }
    
    
    @FXML
    void doFetchSelected(ActionEvent event) {
    	
    	
    	String Name=comboNames.getSelectionModel().getSelectedItem();
    	try {
 			
   	     pst1=con.prepareStatement("select * from bills where CustomerName=?");
   		 pst1.setString(1,Name);
   		
   		 fetchAllRecords(pst1);
       	 
   		tblBillRecord.setItems(list);
		} 
   	 catch (SQLException e) 
   	 {
			
			e.printStackTrace();
		}

    }

    

    @FXML    
    void fillNames()
    {
    	ArrayList<String> namesAry=new ArrayList<>();
    	try{
    		pst1=con.prepareStatement("select distinct CustomerName from bills");
    		ResultSet table= pst1.executeQuery();
        		
        		while(table.next())
        		{
        			String Name=table.getString("CustomerName");//col. name acc. to table
        			namesAry.add(Name);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	comboNames.getItems().addAll(namesAry);
    }

        
    
    @FXML
    void doFindSelected(ActionEvent event) {
    	
    	if(radPaid.isSelected()==true)
    	{
    		
    		try {
     			
    	   	     pst1=con.prepareStatement("select * from bills where Status=1");
    	   		    	   		
    	   		 fetchAllRecords(pst1);
    	       	 
    	   		tblBillRecord.setItems(list);
    			} 
    	   	 catch (SQLException e) 
    	   	 {
    				
    				e.printStackTrace();
    			}
    	}
    	
    	else
    	{
    		
       		try {
     			
    	   	     pst1=con.prepareStatement("select * from bills where Status=0");
    	   		
    	   		
    	   		 fetchAllRecords(pst1);
    	       	 
    	   		tblBillRecord.setItems(list);
    			} 
    	   	 catch (SQLException e) 
    	   	 {
    				
    				e.printStackTrace();
    			}
    	
    		
    	}
    	
    	

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
            String text="Name,Regular Cow Milk Qty,Regular Buffalo Milk Qty,Start Date,End Date,Total Bill,Cow Milk Price/kg,Buffalo Milk Price/kg,Cow Variations,Buffalo Variations\n";
            writer.write(text);
            for (BillBean p : list)
            {
				text = p.getName()+ "," + p.getCowQty()+ "," + p.getBuffaloQty()+ "," + p.getStartDate()+","+p.getEndDate()+","+p.getAmountToBePaid()+","+p.getCowPrice()+","+p.getBuffaloPrice()+","+p.getCowVar()+","+p.getBuffaloVar()+"\n";
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