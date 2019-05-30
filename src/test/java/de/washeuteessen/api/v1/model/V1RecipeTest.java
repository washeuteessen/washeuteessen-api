package de.washeuteessen.api.v1.model;

import de.washeuteessen.api.management.v1.model.TestRecipe;
import de.washeuteessen.recipe.Recipe;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class V1RecipeTest {

    @Test
    public void should_MapTitle() {

        final Recipe recipe = TestRecipe.full()
                .title("Lasagne")
                .build();

        assertThat(new V1Recipe(recipe).getTitle()).isEqualTo("Lasagne");
    }

    @Test
    public void should_MapUrl() {

        final Recipe recipe = TestRecipe.full()
                .url("someurl")
                .build();

        assertThat(new V1Recipe(recipe).getUrl()).isEqualTo("someurl");
    }

    @Test
    public void should_MapId() {

        final Recipe recipe = TestRecipe.full()
                .id(3L)
                .build();

        assertThat(new V1Recipe(recipe).getId()).isEqualTo("3");
    }

}
