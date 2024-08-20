package org.api.java.api_v2_2826502.repositories;

import org.api.java.api_v2_2826502.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
