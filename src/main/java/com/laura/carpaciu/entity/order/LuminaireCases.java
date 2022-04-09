package com.laura.carpaciu.entity.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "luminairecases")
public class LuminaireCases {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Lob
	@Column(name = "luminaire_problems")
	private String problems;

	public LuminaireCases() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LuminaireCases(Long id, String problems) {
		super();
		this.id = id;
		this.problems = problems;
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
