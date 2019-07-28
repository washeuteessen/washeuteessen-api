package de.washeuteessen.api.v1;

import de.washeuteessen.api.swagger.v1.SearchApiDelegate;
import de.washeuteessen.api.swagger.v1.model.ApiV1Recipe;
import de.washeuteessen.api.swagger.v1.model.ApiV1Recipes;
import de.washeuteessen.api.v1.model.V1Recipe;
import de.washeuteessen.recipe.Recipe;
import de.washeuteessen.recipe.RecipeService;
import de.washeuteessen.search.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SearchApiDelegateImpl implements SearchApiDelegate {

    private SearchService searchService;
    private RecipeService recipeService;

    @Override
    public ResponseEntity<ApiV1Recipes> search(String s, final Integer offset, final Integer limit) {

        final List<Recipe> recipes = this.searchService.search(s, Optional.ofNullable(offset), Optional.ofNullable(limit));

        final ApiV1Recipes resp = new ApiV1Recipes();
        resp.setRecipes(recipes.stream()
                .map(V1Recipe::new)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(resp);
    }

    @Override
    public ResponseEntity<Void> openExternal(final String id) {
        final String url = this.recipeService.open(Long.parseLong(id));

        return ResponseEntity.status(301).header("Location", url).build();
    }

    @Override
    public ResponseEntity<ApiV1Recipe> getRecipe(String id) {

        final Recipe recipe = this.recipeService.get(Long.parseLong(id));

        return ResponseEntity.ok().body(new V1Recipe(recipe));
    }
}
