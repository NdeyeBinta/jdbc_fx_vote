package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entities.Electeur;
import com.entities.Vote;

public class VoteDBImpl  implements IVote{


	private DB db = new DB();
	private int ok;
	private ResultSet rs;
	public int add(Vote v) {
		String sql="insert into vote values(null,?,?,?,?,?)";
		ok=0;
		try {
			db.initPrepar(sql);
			db.getPstm().setString(1, v.getLieu());
			db.getPstm().setInt(2, v.getBureau());
			db.getPstm().setString(3, v.getBulletin());
			db.getPstm().setInt(4, v.getAnnee());
			db.getPstm().setString(5, v.getElecteur().getCin());
			ok=db.executeMaj();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public int delete(int idV) {
		String sql= "DELETE  from vote where idv =?";
		ok=0;
		try {
			db.initPrepar(sql);
			db.getPstm().setInt(1, idV);
			ok=db.executeMaj();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public int update(Vote v) {
		String sql= "update   vote  set lieu= ? ,bureau=?,bulletin=?,annee=?,cin=? where idv =?";
		ok=0;
		try {
			db.initPrepar(sql);
			db.getPstm().setString(1, v.getLieu());
			db.getPstm().setInt(2, v.getBureau());
			db.getPstm().setString(3, v.getBulletin());
			db.getPstm().setInt(4, v.getAnnee());
			db.getPstm().setString(5, v.getElecteur().getCin());
			db.getPstm().setInt(6, v.getIdV());
			ok=db.executeMaj();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public List<Vote> liste() {
		String sql="select * from vote";
		List<Vote> votes = new ArrayList<Vote>();
		try {
				db.initPrepar(sql);
				rs=db.executeSelect();
				while(rs.next()){
					Vote v = new Vote();
					v.setIdV(rs.getInt(1));
					v.setLieu(rs.getString(2));
					v.setBureau(rs.getInt(3));
					v.setBulletin(rs.getString(4));
					v.setAnnee(rs.getInt(5));
					v.setElecteur(new ElecteurDBImpl().get(rs.getString(6)));
					votes.add(v);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return votes;
	}

}
