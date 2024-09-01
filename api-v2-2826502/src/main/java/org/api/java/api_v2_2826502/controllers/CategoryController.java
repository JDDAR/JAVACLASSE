package org.api.java.api_v2_2826502.controllers;

import org.api.java.api_v2_2826502.entities.Category;
import org.api.java.api_v2_2826502.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

  // Inyectando el servicio ******************
  @Autowired
  private CategoryService categorieService;

  // EndPoint - Listar TODAS LAS CATEGORIAS *********************
  @GetMapping("/categories")
  public ResponseEntity<List<Category>> getCategories() {
    return ResponseEntity.ok().body(categorieService.findAll());
  }

  // EndPoint - LISTAR UNA CATEGORIIA POR ID *********************
  @GetMapping("/categories/{id}")
  public ResponseEntity<?> getCategory(@PathVariable Long id) {
    Optional<Category> optCategory = categorieService.findById(id);
    if (optCategory.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(categorieService.findById(id));
    } else {
      return ResponseEntity.badRequest().body("Categoria con id : " + id + " No encontrada ...");
    }
  }

  // Endpoint - CREAR NUEVA CATEGORIA ******************************
  @PostMapping("/categories")
  public ResponseEntity<?> crearCategory(@Valid @RequestBody Category category, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      List<String> erroresValidation = new ArrayList<>();
      bindingResult.getFieldErrors().forEach(error -> {
        erroresValidation.add(error.getDefaultMessage());
      });
      return ResponseEntity.badRequest().body(erroresValidation);
    }
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(categorieService.save(category));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error Creando El producto ");
    }
  }

  // Endpoint - ACTUALIZAR CATEGORIA ********************************
  @PutMapping("/categories/{id}")
  public ResponseEntity<?> actializarCategoria(@RequestBody Category category, @PathVariable Long id) {
    Optional<Category> cateAct = categorieService.actualizarCategoria(category, id);

    if (cateAct.isPresent()) {
      return ResponseEntity.ok().body(cateAct.get());
    } else {
      return ResponseEntity.badRequest().body("La categoria con id : " + id + "No se pudo actualizar");
    }
  }

  // ENDPOINT ELIMINAR CATEGORIA **************************************
  @DeleteMapping("/categories/{id}")
  public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
    try {
      boolean isEliminarCategoria = categorieService.eliminarCategoria(id);
      if (isEliminarCategoria) {
        return ResponseEntity.ok().body("Categoria con id : " + id + " Eliminado satisfactoriamente");
      } else {
        return ResponseEntity.badRequest().body("la categoria no existe en la base de datos....");
      }

    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al eiminar la categoria con id : " + id + e.getMessage());
    }
  }

}
