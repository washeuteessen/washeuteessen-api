package de.washeuteessen.api.v1.model;

import de.washeuteessen.api.swagger.v1.model.ApiV1Recipe;
import de.washeuteessen.recipe.Recipe;

public class V1Recipe extends ApiV1Recipe {

    public V1Recipe(final Recipe recipe) {
        super.setId(String.valueOf(recipe.getId()));
        super.setTitle(recipe.getTitle());
        super.setImageSrc(recipe.getImageSrc());
    }

}
