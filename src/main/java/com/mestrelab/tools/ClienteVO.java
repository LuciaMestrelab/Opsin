package com.mestrelab.tools;

public class ClienteVO {

	private String name;
	private String surname;
	private String dni;
	
	public ClienteVO(String name,String surname,String dni){
		setName(name);
		setSurname(surname);
		setDni(dni);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}
