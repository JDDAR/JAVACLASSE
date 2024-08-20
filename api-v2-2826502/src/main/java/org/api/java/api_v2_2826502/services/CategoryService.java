package org.api.java.api_v2_2826502.services;

import java.util.List;

import org.api.java.api_v2_2826502.entities.Category;
import org.api.java.api_v2_2826502.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository repository;

  // obtener la lista de Categoryas
  public List<Category> findAll() {
    return (List<Category>) repository.findAll();
  }
}
