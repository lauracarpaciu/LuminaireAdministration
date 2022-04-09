package com.laura.carpaciu.entity.clients;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "persons")
public class Person extends Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "required")
	@Size(min = 13, max = 13, message = "has to have {max} digits")
	@Pattern(regexp = "[0-9]+", message = "only digits")
	private String cnp;

	@NotNull(message = "required")
	@Size(max = 30, message = "must have a maximum of {max} characters")
	private String firstName;

	@NotNull(message = "required")
	@Size(max = 30, message = "must have a maximum of {max} characters")
	private String lastName;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person(Long id,
			@NotNull(message = "required") @Size(min = 13, max = 13, message = "has to have {max} digits") @Pattern(regexp = "[0-9]+", message = "only digits") String cnp,
			@NotNull(message = "required") @Size(max = 30, message = "must have a maximum of {max} characters") String firstName,
			@NotNull(message = "required") @Size(max = 30, message = "must have a maximum of {max} characters") String lastName) {
		super();
		this.id = id;
		this.cnp = cnp;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Person person = (Person) o;
		return Objects.equals(cnp, person.cnp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnp);
	}

	@Override
	public String toString() {
		return "Person{" + "id=" + id + ", cnp='" + cnp + '\'' + ", firstName='" + firstName + '\'' + ", lastName='"
				+ lastName + '\'' + '}';
	}

}
