package de.washeuteessen.api.management.v1;

import com.washeuteessen.api.swagger.management.v1.RecipiesApiDelegate;
import de.washeuteessen.api.management.v1.model.V1ManagementRecipe;
import de.washeuteessen.api.swagger.management.v1.model.ManagementApiV1CreateRecipe;
import de.washeuteessen.api.swagger.management.v1.model.ManagementApiV1Recipe;
import de.washeuteessen.recipe.Recipe;
import de.washeuteessen.recipe.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecipiesDelegateImpl implements RecipiesApiDelegate {

    private RecipeService recipeService;

    @Override
    public ResponseEntity<ManagementApiV1Recipe> create(final ManagementApiV1CreateRecipe recipeReq) {

        Recipe recipe = Recipe.builder()
                .title(recipeReq.getTitle())
                .url(recipeReq.getUrl())
                .ingredients(recipeReq.getIngridients())
                .build();

        recipe = this.recipeService.create(recipe);

        return ResponseEntity.status(HttpStatus.CREATED).body(new V1ManagementRecipe(recipe));
    }
}
