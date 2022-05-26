package com.jvoq.microservicios.productos.app.models.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "idProducto", "tipoProducto", "nombre", "descripcion", "costoApertura", "idBanco" })
public class Product {

	@Id
	@JsonProperty("id_producto")
	private String idProducto;
	@Field("id_banco")
	@JsonProperty("id_banco")
	private String idBanco;
	@Field("tipo_producto")
	@JsonProperty("tipo_producto")
	private String tipoProducto;
	private String nombre;
	private String descripcion;
	private int juridico;
	private int natural;
	@Field("max_deposito")
	@JsonProperty("max_deposito")
	private int maxDeposito;
	@Field("max_retiro")
	@JsonProperty("max_retiro")
	private int maxRetiro;
	@Field("com_deposito")
	@JsonProperty("com_deposito")
	private Double comDeposito;
	@Field("com_retiro")
	@JsonProperty("com_retiro")
	private Double comRetiro;
	@Field("com_mantenimiento")
	@JsonProperty("com_mantenimiento")
	private Double comMantenimiento;
	@Field("fecha_creacion")
	@JsonProperty("fecha_creacion")
	private Date fechaCreacion;
}
