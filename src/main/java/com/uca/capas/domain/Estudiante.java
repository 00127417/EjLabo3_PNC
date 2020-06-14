package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public", name="estudiante")
public class Estudiante {
	@Id
	@Column(name="id_estudiante")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoEstudiante;
	
	@Column(name="nombre")
	@Size(min = 1,max = 50, message = "El nombre no debe superar los 50 caracteres ni ser menor a 1")
	private String nombre;
	
	@Column(name="apellido")
	@Size(min = 1, max = 50, message = "El nombre no debe superar los 50 caracteres ni ser menor a 1")
	private String apellido;
	
	@Column(name = "edad")
	@NotNull
	@Min(value = 18, message = "No puede ser menos de 18 años")
	private Integer edad;

	@NotNull
	@Column(name = "estado")
	private Boolean estado;
	
	
	public Estudiante() {
		
	}


	public Integer getCodigoEstudiante() {
		return codigoEstudiante;
	}
	public void setCodigoEstudiante(Integer codigoEstudiante) {
		this.codigoEstudiante = codigoEstudiante;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public String getDelegateEstado() {
		return estado? "Activo": "Inactivo";
	}
}
