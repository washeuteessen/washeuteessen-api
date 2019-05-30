package de.washeuteessen.recipe;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeService {

    private RecipeRepository recipeRepository;

    public Recipe create(final Recipe recipe) {

        this.recipeRepository.save(recipe);

        return recipe;
    }

}
