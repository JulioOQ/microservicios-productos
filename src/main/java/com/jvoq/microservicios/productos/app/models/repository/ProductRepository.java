package com.jvoq.microservicios.productos.app.models.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.jvoq.microservicios.productos.app.models.documents.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
