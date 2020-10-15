package com.dao;

import java.util.List;

import com.entities.Vote;

public interface IVote {

	public int add(Vote v);
	public int delete(int idV);
	public int update(Vote v);
	public List<Vote> liste();
}
