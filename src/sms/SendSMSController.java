package sms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SendSMSController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtMsg;

    @FXML
    private TextField txtMobile;

    @FXML
    void doSMS(ActionEvent event) {
    	
    	String resp=sms.SST_SMS.bceSunSoftSend(txtMobile.getText(),txtMsg.getText() );
    	if(resp.contains("successfully"))
			System.out.println("Sent...");
	else
		if(resp.contains("Unknown"))
			System.out.println("Check Internet connection");
		else
			System.out.println("Invalid Mobile Number");
	

    }

    @FXML
    void initialize() {
  
    }
}
