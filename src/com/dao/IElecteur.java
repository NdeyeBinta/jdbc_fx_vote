package com.dao;

import java.util.List;

import com.entities.Electeur;

public interface IElecteur {

	public int add(Electeur e);
	public int delete(String cin);
	public int update(Electeur e);
	public List<Electeur> liste();
	public Electeur get(String cin);
}
