package com.ui;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tools.Notification;

public class VoteController {

    @FXML
    private TextField lieu_txt;

    @FXML
    private TextField bureau_txt;

    @FXML
    private TextField bultin_txt;

    @FXML
    private TextField annee_txt;

    @FXML
    private ComboBox<Electeur> electeur_cbx;

    private ObservableList<Electeur> electeurs;

    private IElecteur electeurbda = new ElecteurDBImpl();

    private IVote votedao = new VoteDBImpl();

    @FXML
    private Button annuler_btn;

    @FXML
    private Button valider_btn;

    @FXML
    private Button supprimer_btn;

    @FXML
    private Button modifier_btn;

    @FXML
    private TableView<Vote> vote_table;

    @FXML
    private TableColumn<Vote, Integer> idColumn;

    @FXML
    private TableColumn<Vote, String> lieuColumn;

    @FXML
    private TableColumn<Vote, Integer> bureauColumn;

    @FXML
    private TableColumn<Vote, String> bultinColumn;

    @FXML
    private TableColumn<Vote, Integer> anneeColumn;

    @FXML
    private TableColumn<Vote, Electeur> electeurColumn;


    @FXML
    void annuler(ActionEvent event) {
    	gestionButtuntable();
    	lieu_txt.setText("");
    	bureau_txt.setText("");
    	bultin_txt.setText("");
    	annee_txt.setText("");
    	electeur_cbx.getSelectionModel().selectFirst();
    }
//pour l'id de vote de la table ligne du table selectionnéé
    private int idV;

    @FXML
    void tableClick(MouseEvent event) {
    	//recuperation de la ligne selectionnéé
    	Vote vote=vote_table.getSelectionModel().getSelectedItem();
    	//affectation des valeurs aux champs de saisie
    	idV = vote .getIdV();
    	lieu_txt.setText(vote.getLieu().toString());
    	bureau_txt.setText(vote.getBureau()+"");
    	bultin_txt.setText(vote.getBulletin().toString());
    	annee_txt.setText(vote.getAnnee()+"");
    	electeur_cbx.getSelectionModel().select(vote.getElecteur());
    	gestionButtunform();
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
    		Vote vote=new Vote();
        	vote.setLieu(lieu_txt.getText());
        	vote.setBulletin(bultin_txt.getText());
        	vote.setBureau(Integer.parseInt(bureau_txt.getText().toString()));
        	vote.setAnnee(Integer.parseInt(annee_txt.getText().toString()));
        	vote.setElecteur(electeur_cbx.getSelectionModel().getSelectedItem());
        	int  ok =votedao.add(vote);
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
    		Vote vote=new Vote();
    		vote.setIdV(idV);
        	vote.setLieu(lieu_txt.getText());
        	vote.setBulletin(bultin_txt.getText());
        	vote.setBureau(Integer.parseInt(bureau_txt.getText().toString()));
        	vote.setAnnee(Integer.parseInt(annee_txt.getText().toString()));
        	vote.setElecteur(electeur_cbx.getSelectionModel().getSelectedItem());
        	int  ok =votedao.update(vote);
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
        	int  ok =votedao.delete(idV);
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

    private void loadCombo(){

    	electeurs = FXCollections.observableArrayList();
    	Electeur  electeur = new Electeur();
    	electeur.setCin("..");
    	electeur.setNom("Faite votre choix");
    	electeur.setPrenom("..");
    	electeurs.add(electeur);
    	electeurbda.liste().stream()
    						.forEach(e->{
    							electeurs.add(e);
    						});
    	electeur_cbx.setItems(electeurs);
    	//selection de la premier entree du combobox (faite votre choix)
    	electeur_cbx.getSelectionModel().selectFirst();
    }
  private void loadTable(){
	 	idColumn.setCellValueFactory(new PropertyValueFactory<>("idV"));//id de le class
	 	lieuColumn.setCellValueFactory(new PropertyValueFactory<>("lieu"));//lieu de le class
	 	bureauColumn.setCellValueFactory(new PropertyValueFactory<>("bureau"));//bureau de le class
	 	bultinColumn.setCellValueFactory(new PropertyValueFactory<>("bulletin"));//bultin de le class
	 	anneeColumn.setCellValueFactory(new PropertyValueFactory<>("annee"));//annee de le class
	 	electeurColumn.setCellValueFactory(new PropertyValueFactory<>("electeur"));//electeur de le class
	 	ObservableList<Vote> votes = FXCollections.observableArrayList();
	 	votedao.liste().stream()
	 					.forEach(v-> votes.add(v));
	 	vote_table.setItems(votes);
    }
  @FXML
    private void initialize(){
    	loadCombo();
    	loadTable();

    }

}

