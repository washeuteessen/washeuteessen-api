package de.washeuteessen.api.management.v1.model;

import de.washeuteessen.recipe.Recipe;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class V1ManagementRecipeTest {

    @Test
    public void should_MapId() {

        final Recipe recipe = TestRecipe.full()
                .id(2L)
                .build();

        assertThat(new V1ManagementRecipe(recipe).getId()).isEqualTo("2");
    }

    @Test
    public void should_MapTitle() {

        final Recipe recipe = TestRecipe.full()
                .title("Schweinelenden")
                .build();

        assertThat(new V1ManagementRecipe(recipe).getTitle()).isEqualTo("Schweinelenden");
    }

    @Test
    public void should_MapUrl() {

        final Recipe recipe = TestRecipe.full()
                .url("someurl")
                .build();

        assertThat(new V1ManagementRecipe(recipe).getUrl()).isEqualTo("someurl");
    }

    @Test
    public void should_MapIngredients() {

        final Recipe recipe = TestRecipe.full()
                .ingredients(Arrays.asList("Käse", "Milch", "Butter"))
                .build();

        assertThat(new V1ManagementRecipe(recipe).getIngridients()).containsExactlyInAnyOrder("Käse", "Milch", "Butter");
    }

    @Test
    public void should_Map_When_IngredientsAreNull() {

        final Recipe recipe = TestRecipe.full()
                .ingredients(null)
                .build();

        assertThat(new V1ManagementRecipe(recipe).getIngridients()).isEqualTo(null);
    }


}
