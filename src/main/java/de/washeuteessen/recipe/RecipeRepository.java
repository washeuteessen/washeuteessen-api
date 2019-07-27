package de.washeuteessen.recipe;

import org.springframework.data.repository.reactive.RxJava2CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends RxJava2CrudRepository<Recipe, Long> {


}
