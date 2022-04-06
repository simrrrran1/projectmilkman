package dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DashboardViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    
    @FXML
    void doCalculateBill(MouseEvent event) {
    	
    	if(event.getClickCount()==1){
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billingRecord/BillingSoftware.fxml")); 
			Scene scene = new Scene(root,600,651);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//to hide the opened window
			 
			  // Scene scene1=(Scene)txtMobile.getScene();
			   //scene1.getWindow().hide();
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    	
   	}	


    }

    @FXML
    void doOpenBillRecords(MouseEvent event) {
    	

    	if(event.getClickCount()==1){
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("tableContentsDisplay/DisplayTable.fxml")); 
			Scene scene = new Scene(root,954,681);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//to hide the opened window
			 
			  // Scene scene1=(Scene)txtMobile.getScene();
			   //scene1.getWindow().hide();
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    }	



    }

    @FXML
    void doOpenNewRecords(MouseEvent event) {
    	

    	if(event.getClickCount()==1){
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("newRecord/CustomerEntry.fxml")); 
			Scene scene = new Scene(root,600,454);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//to hide the opened window
			 
			  // Scene scene1=(Scene)txtMobile.getScene();
			   //scene1.getWindow().hide();
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    }	



    }

    @FXML
    void doOpenNewRecordsInTable(MouseEvent event) {
    	

    	if(event.getClickCount()==1){
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customerContentsDisplay/DisplayCustomerDetails.fxml")); 
			Scene scene = new Scene(root,954,681);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//to hide the opened window
			 
			  // Scene scene1=(Scene)txtMobile.getScene();
			   //scene1.getWindow().hide();
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    }	



    }

    @FXML
    void doOpenRegularUpdatesInTable(MouseEvent event) {
    	

    	if(event.getClickCount()==1){
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("variationLog/VariationContents.fxml")); 
			Scene scene = new Scene(root,759,553);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//to hide the opened window
			 
			  // Scene scene1=(Scene)txtMobile.getScene();
			   //scene1.getWindow().hide();
			 
	    } 
	catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    }	



    }

    @FXML
    void doOpenRegularUpdation(MouseEvent event) {
    	

    	if(event.getClickCount()==1){
    	
    	try {
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dailyRecord/RegularEntry.fxml")); 
			Scene scene = new Scene(root,600,589);
			
			Stage primaryStage=new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//to hide the opened window
			 
			  // Scene scene1=(Scene)txtMobile.getScene();
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
    	
    	

    }
}
