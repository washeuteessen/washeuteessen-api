package de.washeuteessen.recipe;

import de.washeuteessen.recipe.exception.RecipeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeService {

    private RecipeRepository recipeRepository;
    private RecipeMetrics recipeMetrics;

    public Recipe get(final Long id) {
        return this.recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));
    }

    public String open(final Long id) {
        final Recipe recipe = this.get(id);

        this.recipeMetrics.incrementRedirectRecipies();

        return recipe.getUrl();
    }

    public Long recipiesInDatabase() {
        final Long total = this.recipeRepository.count();
        this.recipeMetrics.setTotalRecipies(total);
        return total;
    }
}
