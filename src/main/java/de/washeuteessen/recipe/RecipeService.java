package de.washeuteessen.recipe;

import de.washeuteessen.recipe.exception.RecipeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeService {

    private RecipeRepository recipeRepository;

    public Recipe get(final Long id) {
        return this.recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));
    }

}
