package de.washeuteessen.recipe;

import de.washeuteessen.recipe.exception.RecipeNotFoundException;
import io.reactivex.Flowable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeService {

    private RecipeRepository recipeRepository;
    private RecipeMetrics recipeMetrics;

    public Recipe get(final Long id) {
        return Optional
                .ofNullable(this.recipeRepository.findById(id).blockingGet())
                .orElseThrow(() -> new RecipeNotFoundException(id));
    }

    public String open(final Long id) {
        final Recipe recipe = this.get(id);

        this.recipeMetrics.incrementRedirectRecipies();

        return recipe.getUrl();
    }

    public Long recipiesInDatabase() {
        final Long total = this.recipeRepository.count().blockingGet();
        this.recipeMetrics.setTotalRecipies(total);
        return total;
    }

    public Flowable<Recipe> getAll() {
        return this.recipeRepository.findAll();
    }

}
