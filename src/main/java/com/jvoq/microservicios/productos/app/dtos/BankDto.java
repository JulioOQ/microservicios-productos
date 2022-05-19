package com.jvoq.microservicios.productos.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "idBanco", "nombre", "ruc", "telefono", "telefono", "correo", "direccion" })
public class BankDto {

	@JsonProperty("id_banco")
	private String idBanco;
	private String nombre;
	private String correo;
	private String direccion;
	private String telefono;
	private String ruc;
}
