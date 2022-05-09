package com.jvoq.microservicios.productos.app.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "banks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "idBanco", "nombre", "ruc", "telefono","telefono","correo","direccion"})
public class Bank {

	@Id
	@JsonProperty("id_banco")
	private String idBanco;
	private String nombre;
	private String correo;
	private String direccion;
	private String telefono;
	private String ruc;
	
}
