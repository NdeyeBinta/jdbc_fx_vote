package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tools.Notification;
import tools.Outils;

public class MainController {
    @FXML
    private TextField email_txt;
    @FXML
    private PasswordField password_txt;

    @FXML
    public void connexion(ActionEvent event){
        String email =email_txt.getText();
        String password=password_txt.getText();
        if (email.equals("") || password.equals("")){
        	/*Alert a = new Alert(Alert.AlertType.INFORMATION);
        	a.setTitle("Message");
        	a.setContentText("Veuillez remplir tous les Champs!");

        	a.showAndWait();*/
        	//Notification.NotifSucces("Message","Veuillez remplir tous les Champs!");
        	Notification.NotifError("Message","Veuillez remplir tous les Champs!");
        }else{
        	try {
        		Outils.load(event, "/com/ui/Electeur.fxml");
			} catch (IOException e) {
				e.printStackTrace();
				// TODO: handle exception
			}


        }
    }
}
