package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entities.Electeur;

public class ElecteurDBImpl implements IElecteur {

	private DB db = new DB();
	private int ok;
	private ResultSet rs;

	@Override
	public int add(Electeur e) {
		String sql = "INSERT INTO electeur VALUES(?, ?, ?)";
		try {
			//initialise la requete sql
			db.initPrepar(sql);
			/*passage de valeur*/
			db.getPstm().setString(1, e.getCin());
			db.getPstm().setString(2, e.getNom());
			db.getPstm().setString(3, e.getPrenom());
			//execution de la requete
			ok = db.executeMaj();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ok;
	}

	@Override
	public int delete(String cin) {
		String sql = "delete from electeur where cni=?";
		ok=0;
		try{
			db.initPrepar(sql);
			db.getPstm().setString(1,cin);
			ok=db.executeMaj();
	}catch(Exception e){
		e.printStackTrace();
	}
		return ok;
	}

	@Override
	public int update(Electeur e) {
		String sql = "update electeur set nom=?,prenom=? where  cin=?";
		ok=0;
		try{
				db.initPrepar(sql);
				db.getPstm().setString(1, e.getNom());
				db.getPstm().setString(1, e.getPrenom());
				db.getPstm().setString(1, e.getCin());
				ok=db.executeMaj();
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return ok;
	}

	@Override
	public List<Electeur> liste() {
		List<Electeur> electeurs =new ArrayList<Electeur>();
		String sql = "select * from electeur";
		try{
			db.initPrepar(sql);
			rs=db.executeSelect();
			//appel du sous programme d'extraction de rs
			electeurs=extractRs(rs);

		}catch(Exception e){
			e.printStackTrace();
		}
		return electeurs;
	}
	private List<Electeur> extractRs(ResultSet rs){
		List<Electeur> liste = new ArrayList<Electeur>();
		try{
			while(rs.next()){
				Electeur electeur=new Electeur();
				electeur.setCin(rs.getString(1));
				electeur.setNom(rs.getString(2));
				electeur.setPrenom(rs.getString(3));
				liste.add(electeur);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public Electeur get(String cin) {
		String sql="select * from electeur where cin=?";
		Electeur electeur = null;
		try {
				db.initPrepar(sql);
				db.getPstm().setString(1, cin);
				rs=db.executeSelect();
				if(rs.next()){
					 electeur = new Electeur();
					 electeur.setCin(rs.getString(1));
					 electeur.setNom(rs.getString(2));
					 electeur.setPrenom(rs.getString(3));

				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return electeur;
	}

}
