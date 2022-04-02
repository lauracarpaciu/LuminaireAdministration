package com.laura.carpaciu.entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class LuminaireCases {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Lob
	@Column(name = "luminaire_problems")
	private String problems;

	public LuminaireCases() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LuminaireCases(int id, String problems) {
		super();
		this.id = id;
		this.problems = problems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProblems() {
		return problems;
	}

	public void setProblems(String problems) {
		this.problems = problems;
	}

	@Override
	public String toString() {
		return problems;

	}
}
