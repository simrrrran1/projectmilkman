package variationLog;

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
import variationLog.VariationBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import projectDatabaseConnection.Connector;

public class VariationContentsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dtp1;

    @FXML
    private DatePicker dtp2;

    @FXML
    private ComboBox<String> comboCusName;

    @FXML
    private TableView<VariationBean> tblVar;
    
    Connection con=null;
    PreparedStatement pst1;

    @FXML
    void doFind(ActionEvent event) {
    	

    	String Name=comboCusName.getSelectionModel().getSelectedItem();    	
    	LocalDate local=dtp1.getValue();
    	java.sql.Date Date=	java.sql.Date.valueOf(local);
    	String date=String.valueOf(Date);
    	LocalDate local1=dtp2.getValue();
    	java.sql.Date Date1=java.sql.Date.valueOf(local1);
    	String date1=String.valueOf(Date1);
    	
    	
    	try {
			
     		 pst1=con.prepareStatement("select * from regularentry where Name=? and  Date BETWEEN Date(?) and Date(?) ");
     		 pst1.setString(1,Name);
     		pst1.setString(2,date);
     		pst1.setString(3,date1);
     		
     		
     		 fetchAllRecords(pst1);
         	 
     	
  		} 
     	 catch (SQLException e) 
     	 {
  			
  			e.printStackTrace();
  		}  	    	
        tblVar.setItems(list);
    }

    
 ObservableList<VariationBean> list;
    

    void fetchAllRecords(PreparedStatement pst1)
    {
    	list=FXCollections.observableArrayList();
    	try{
        
        	ResultSet table= pst1.executeQuery();
        		
        		while(table.next())
        		{
        			String name=table.getString("Name");
        			double cowQuantity=table.getDouble("CowQuantity");
        			
        			double buffaloQuantity=table.getDouble("BuffaloQuantity");
        			String Date=table.getString("Date");
        	//System.out.println(Date);
        			VariationBean sb=new VariationBean(name,cowQuantity,buffaloQuantity,Date); 
        			list.add(sb);
        			
        		}
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
    	
    }

    @FXML
    void doViewAll(ActionEvent event) {
    	

     	 try {
  			
     		 pst1=con.prepareStatement("select * from regularentry");
     		
     		 fetchAllRecords(pst1);
         	 
     	    tblVar.setItems(list);
  		} 
     	 catch (SQLException e) 
     	 {
  			
  			e.printStackTrace();
  		}  	


    }
    
    void fillColumns()
    {
    	TableColumn<VariationBean, String> name=new TableColumn<VariationBean, String>("Name");//Dikhava Title
     	name.setCellValueFactory(new PropertyValueFactory<>("name"));//bean field name, no link with table col name

     	TableColumn<VariationBean, Double> cowQuantity=new TableColumn<VariationBean, Double>("Reg Cow Milk Qty");//Dikhava Title
     	cowQuantity.setCellValueFactory(new PropertyValueFactory<>("cowQuantity"));//bean field name, no link with table col name
     	
     	
     	TableColumn<VariationBean, Double> buffaloQuantity=new TableColumn<VariationBean, Double>("Reg Buffalo Milk Qty");//Dikhava Title
     	buffaloQuantity.setCellValueFactory(new PropertyValueFactory<>("buffaloQuantity"));//bean field name, no link with table col name
     	
     	TableColumn<VariationBean, String>  Date=new TableColumn<VariationBean, String>("Variation Dates");//Dikhava Title
     	Date.setCellValueFactory(new PropertyValueFactory<>("Date"));//bean field name, no link with table col name
     	
     	tblVar.getColumns().clear();
     	tblVar.getColumns().addAll(name,cowQuantity,buffaloQuantity,Date);
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
    	comboCusName.getItems().addAll(namesAry);
    }


    @FXML
    void doViewInExcel(ActionEvent event) throws Exception {
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
            String text="Name,Cow Quantity,Buffalo Quantity,Date\n";
            writer.write(text);
            for (VariationBean p : list)
            {
				text = p.getName()+ "," + p.getCowQuantity()+ "," + p.getBuffaloQuantity()+ "," + p.getDate()+"\n";
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
