package com.laura.carpaciu.entity.work;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.laura.carpaciu.utility.WorkCategory;

import java.util.Objects;

@Entity
@Table(name = "works")
public class Work {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message = "required")
	private String workDescription;

	@Min(value = 0, message = "invalid")
	private Double timedWork;

	@Enumerated(EnumType.STRING)
	private WorkCategory category;

	public Work() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Work(int id, String workDescription, Double timedWork, WorkCategory category) {
		super();
		this.id = id;
		this.workDescription = workDescription;
		this.timedWork = timedWork;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}

	public Double getTimedWork() {
		return timedWork;
	}

	public void setTimedWork(Double timedWork) {
		this.timedWork = timedWork;
	}

	public WorkCategory getCategory() {
		return category;
	}

	public void setCategory(WorkCategory category) {
		this.category = category;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Work work = (Work) o;
		return Double.compare(work.timedWork, timedWork) == 0 && Objects.equals(workDescription, work.workDescription)
				&& category == work.category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(workDescription, timedWork, category);
	}

	@Override
	public String toString() {
		return "Work{" + "id=" + id + ", workDescription='" + workDescription + '\'' + ", timedWork=" + timedWork
				+ ", category=" + category + '}';
	}

}