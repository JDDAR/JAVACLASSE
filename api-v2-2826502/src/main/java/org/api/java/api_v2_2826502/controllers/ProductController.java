package org.api.java.api_v2_2826502.controllers;

import jakarta.validation.Valid;
import org.api.java.api_v2_2826502.entities.Product;
import org.api.java.api_v2_2826502.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    //inyectar el servicio
    @Autowired
    private ProductService servicio;

    //Endpoint ************
    //listar todos los productos
    @GetMapping("/products")
    public ResponseEntity<List<Product>>getProducts(){ // Respuesta adaptable
        return ResponseEntity.ok().body(servicio.findAll()); // Response con codigo 200 ->
    }

    //endpoint ***********
    //listar un producto por ID
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        Optional<Product> optProduct = servicio.findById(id);
        if (optProduct.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK) // 201 - 200  crear de forma existoa ó HttpStatus.ok
                    .body(servicio.findById(id)
                    ); // body es la respueta de los datos que queremos dar alcliente
        } else {
            return  ResponseEntity
                    .badRequest()
                    .body("Producto con id : " + id + " No Encontrado " );
        }
    }

    //endpoint ************************************************************* CREAR
    //crear un producto
    @PostMapping("/products")
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Product product,
                                           BindingResult bindingResult){ //BindingResul siempre despues deñ requestBody
        if (bindingResult.hasErrors()){
            //Si existe erroes de validacion en los datos del body tonces :
            //return  ResponseEntity.badRequest().body("Errores de validacion "); // ARBOLES DE DESICION
            List<String> erroresValidacion = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(error -> {
                erroresValidacion.add(error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(erroresValidacion);
        }
        try {
            return  ResponseEntity.status(HttpStatus.CREATED) // 201 Se creo el producto
                                        .body(servicio.save(product));
        }catch (Exception e){
            return  ResponseEntity.badRequest().body("Error Creando el Producto");
        }
    }

    //Endpoint: actualizar un producto ******************************************* ACTUALIZAR
    @PutMapping("/products/{id}")
    public  ResponseEntity<?> actualizarProducto( @RequestBody Product product,@PathVariable Long id){
        Optional<Product> prodAct  = servicio.cambiarProducto(product, id);
       if(prodAct.isPresent()){
           //Si el producto actualizar eziste entonces
           return  ResponseEntity.ok().body(prodAct.get());
       }else {
           return ResponseEntity.badRequest().body("El Producto con id : " + id  + " no se puedo actualizar ");
       }
    }


}
