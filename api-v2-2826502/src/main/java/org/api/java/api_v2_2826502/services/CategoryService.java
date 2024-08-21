package org.api.java.api_v2_2826502.services;

import java.util.List;
import java.util.Optional;

import org.api.java.api_v2_2826502.entities.Category;
import org.api.java.api_v2_2826502.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository repository;

  // obtener la lista de Categoryas *************************************
  public List<Category> findAll() {
    return (List<Category>) repository.findAll();
  }

  // Seleccionar categoria por ID ***************************************
  public Optional<Category> findById(Long id) {
    return repository.findById(id);
  }

  // insertar nueva categoria **************************************
  @Transactional
  public Category save(Category category) {
    return repository.save(category);
  }

  // ACTUALIZAR CATEGORIA POR AID ************************************
  @Transactional
  public Optional<Category> actualizarCategoria(Category category, Long id) {
    // Verificar si esta la categoria por id
    Optional<Category> cateUpd = this.findById(id);

    if (cateUpd.isPresent()) {
      Category actualizar = cateUpd.get();
      actualizar.setName(category.getName());
      actualizar.setDescription(category.getDescription());

      repository.save(actualizar);

      return Optional.of(actualizar);
    } else {
      return cateUpd;
    }
  }

  // ELIMINADO CATEGORIA ***************************************************
  @Transactional
  public boolean eliminarCategoria(Long id) {
    Optional<Category> category = repository.findById(id);
    if (category.isPresent()) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }

}
