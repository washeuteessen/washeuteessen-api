package de.washeuteessen.api;

import de.washeuteessen.recipe.Recipe;

import java.util.Arrays;

public class TestRecipe {

    public static Recipe.RecipeBuilder full() {
        return Recipe.builder()
                .id(1L)
                .ingredients(Arrays.asList("Eier", "Schnittlauch", "Salz", "Pfeffer"))
                .title("Spiegelei")
                .url("http://www.washeuteessen.de");
    }

}
