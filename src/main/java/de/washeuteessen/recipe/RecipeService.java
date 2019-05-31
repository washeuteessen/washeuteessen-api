package de.washeuteessen.recipe;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeService {

    private static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

    private RecipeRepository recipeRepository;

    public Recipe create(final Recipe recipe) {

        this.recipeRepository.save(recipe);

        LOG.info("{} created", recipe);

        return recipe;
    }

}
