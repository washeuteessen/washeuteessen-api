package de.washeuteessen.api.management.v1.model;

import de.washeuteessen.api.swagger.management.v1.model.ManagementApiV1Ingridients;
import de.washeuteessen.api.swagger.management.v1.model.ManagementApiV1Recipe;
import de.washeuteessen.recipe.Recipe;

public class V1ManagementRecipe extends ManagementApiV1Recipe {

    public V1ManagementRecipe(final Recipe recipe) {
        super.setId(String.valueOf(recipe.getId()));
        super.setTitle(recipe.getTitle());
        super.setUrl(recipe.getUrl());
        super.setImageSrc(recipe.getImageSrc());
        if (null != recipe.getIngredients()) {
            super.setIngridients(new ManagementApiV1Ingridients());
            super.getIngridients().addAll(recipe.getIngredients());
        }
    }

}
