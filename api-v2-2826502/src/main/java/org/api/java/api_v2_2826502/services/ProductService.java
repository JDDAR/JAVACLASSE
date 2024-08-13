package org.api.java.api_v2_2826502.services;

import jakarta.transaction.Transactional;
import org.api.java.api_v2_2826502.entities.Product;
import org.api.java.api_v2_2826502.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    /*dependecia inyectada
    * bjeto necesario para cumplir}operaciones en la clase * **************** OBJETO */
    @Autowired
    private ProductRepository repositorio;

    //CRUD
    /*seleccionar todos los producctos **************************** ******** SELECCIONAR LISTA*/
    public List<Product> findAll(){

        //seleccionar todos los productos
        return (List<Product>)
                repositorio.findAll();
    }
    //seleccionar producctos por id ****************************************** SELECCIONAR ID
    //SQL select from where id =

    public Optional<Product> findById(Long id){
        return repositorio.findById(id);
    }

    //INSERTAR NUEVO PRODUCTO ************************************************* INSERTAR
    @Transactional
    public  Product save(Product product){
        return  repositorio.save(product);
    }

    //actualizar producto ***************************************************** ACTUALIZAR
    @Transactional
    public  Optional<Product> cambiarProducto(Product product, Long id){
        //1. Verificar si existe el producto por id
        Optional< Product>prodUpd = this.findById(id);// Optional para saber si esta o no el producto

        if (prodUpd.isPresent()) {
            //2.Si existe el producto
            //obtener en una variable producto del optional
            Product actualizacion = prodUpd.get();
            //Ahora si actualizao los atributos
            actualizacion.setName(product.getName());
            actualizacion.setDescription(product.getDescription());
            actualizacion.setPrice(product.getPrice());
            //GRABANDO DATOS
            repositorio.save(actualizacion);
            //Convertir el producto en optional
            return  Optional.of(actualizacion);
        }else {
            return prodUpd;
        }
    }



}
