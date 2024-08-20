package org.api.java.api_v2_2826502.controllers;

import org.api.java.api_v2_2826502.entities.Category;
import org.api.java.api_v2_2826502.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

  // Inyectando el servicio
  @Autowired
  private CategoryService categorieService;

  // EndPoint - Listar TODAS LAS CATEGORIAS
  @GetMapping("/categories")
  public ResponseEntity<List<Category>> getCategories() {
    return ResponseEntity.ok().body(categorieService.findAll());
  }
}
