package com.ui;
import java.io.IOException;

import com.dao.ElecteurDBImpl;
import com.dao.IElecteur;
import com.dao.IVote;
import com.dao.VoteDBImpl;
import com.entities.Electeur;
import com.entities.Vote;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tools.Notification;
import tools.Outils;

public class ElecteurController {

	 @FXML
	    private TextField cin_txt;

    @FXML
    private TextField nom_txt;

    @FXML
    private TextField prenom_txt;

    private ObservableList<Electeur> electeurs;

    private IElecteur electeurbda = new ElecteurDBImpl();

    private IVote votedao = new VoteDBImpl();
    @FXML
    private Button valider_btn;

    @FXML
    private Button annuler_btn;
    @FXML
    private Button supprimer_btn;

    @FXML
    private Button modifier_btn;


    @FXML
    private TableView<Electeur> electeur_table;


    @FXML
    private TableColumn<Electeur, String> cinColumn;

    @FXML
    private TableColumn<Electeur, String> nomColumn;

    @FXML
    private TableColumn<Electeur, String> prenomColumn;
    @FXML
    void annuler(ActionEvent event) {
    	gestionButtuntable();
    	cin_txt.setText("");
    	nom_txt.setText("");
    	prenom_txt.setText("");

    }
    private String cin;
    @FXML
    void tableClick(MouseEvent event) {
    	//recuperation de la ligne selectionnéé
    	Electeur electeur=electeur_table.getSelectionModel().getSelectedItem();
    	//affectation des valeurs aux champs de saisie
    	cin_txt.setText(electeur.getCin().toString());
       nom_txt.setText(electeur.getNom().toString());
       prenom_txt.setText(electeur.getPrenom().toString());
    }
    @FXML
    public void votez(ActionEvent event) throws IOException{

        		Outils.load(event, "/com/ui/Vote.fxml");


    }
    private void gestionButtunform(){
    	valider_btn.setDisable(true);
    	annuler_btn.setText("Actualiser");
    	supprimer_btn.setDisable(false);
    	modifier_btn.setDisable(false);
    }
    private void gestionButtuntable(){
    	valider_btn.setDisable(false);//activation
    	annuler_btn.setText("Annuler");
    	supprimer_btn.setDisable(true);//on desactivation
    	modifier_btn.setDisable(true);
    }

    @FXML
    void valider(ActionEvent event) {
    	try {
    		Electeur electeur=new Electeur();
    		electeur.setCin(cin_txt.getText());
    		electeur.setNom(nom_txt.getText());
    		electeur.setPrenom(prenom_txt.getText());
        	int  ok =electeurbda.add(electeur);
        	if(ok!=0){
        		Notification.NotifSucces("Succes", "donnee ajoute");
        		}else{
            		Notification.NotifError("Error", "donnee  non ajoute");
        		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	loadTable();
    	annuler(event);

    }
    @FXML
    void modifier(ActionEvent event) {
    	try {
    		Electeur electeur=new Electeur();
    		electeur.setCin(cin_txt.getText());
    		electeur.setNom(nom_txt.getText());
    		electeur.setPrenom(prenom_txt.getText());
        	int  ok =electeurbda.update(electeur);
        	if(ok!=0){
        		Notification.NotifSucces("Succes", "donnee modifier");
        		}else{
            		Notification.NotifError("Error", "donnee  non modifier");
        		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	loadTable();
    	annuler(event);

    }

    @FXML
    void supprimer(ActionEvent event) {
    	try {
    		int  ok =electeurbda.delete(cin);
        	if(ok!=0){
        		Notification.NotifSucces("Succes", "donnee supprimer");
        		}else{
            		Notification.NotifError("Error", "donnee  non supprimer");
        		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	loadTable();
    	annuler(event);

    }

    private void loadTable(){
	 	cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));//id de le class
	 	nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));//lieu de le class
	 	prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));//bureau de le class

	 	ObservableList<Electeur> electeurs = FXCollections.observableArrayList();
	 	electeurbda.liste().stream()
	 					.forEach(v-> electeurs.add(v));
	 	electeur_table.setItems(electeurs);
    }
    @FXML
    private void initialize(){
    	loadTable();

    }
}


