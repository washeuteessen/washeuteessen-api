package de.washeuteessen.api.v1;

import de.washeuteessen.api.swagger.v1.SearchApiDelegate;
import de.washeuteessen.api.swagger.v1.model.ApiV1Recipies;
import de.washeuteessen.api.v1.model.V1Recipe;
import de.washeuteessen.recipe.Recipe;
import de.washeuteessen.search.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SearchApiDelegateImpl implements SearchApiDelegate {

    private SearchService searchService;

    @Override
    public ResponseEntity<ApiV1Recipies> search(String s) {

        final List<Recipe> recipies = this.searchService.search(s);

        final ApiV1Recipies resp = new ApiV1Recipies();
        resp.setRecipies(recipies.stream()
                .map(V1Recipe::new)
                .collect(Collectors.toList()));

        return ResponseEntity.ok(resp);
    }
}
