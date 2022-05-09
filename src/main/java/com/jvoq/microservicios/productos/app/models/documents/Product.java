package com.jvoq.microservicios.productos.app.models.documents;


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
@JsonPropertyOrder({ "idProducto", "tipoProducto", "nombre", "descripcion","costoApertura","idBanco"}) 
public class Product {

	@JsonProperty("id_producto")
	@Id	
	private String idProducto;
	
	@Field("id_banco")
	@JsonProperty("id_banco")
	private String idBanco;	
	
	@Field("tipo_producto")
	@JsonProperty("tipo_producto")
	private String tipoProducto;
	
	private String nombre;
	private String descripcion;
	
	@Field("costo_apertura")
	@JsonProperty("costo_apertura")
	private Boolean costoApertura;
	
}
