package de.washeuteessen.recipe;

import de.washeuteessen.recipe.exception.RecipeNotFoundException;
import io.reactivex.Maybe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeMetrics recipeMetrics;

    @Before
    public void init() {

    }

    @Test
    public void should_ReturnSingleResult_On_Get() {

        final Recipe recipe = Recipe.builder()
                .id(2L)
                .build();

        when(this.recipeRepository.findById(eq(2L))).thenReturn(Maybe.just(recipe));

        assertThat(this.recipeService.get(2L)).isEqualTo(recipe);
    }

    @Test(expected = RecipeNotFoundException.class)
    public void should_ThrowRecipeNotFoundException_When_RecipeDoesNotExists_On_Get() {

        when(this.recipeRepository.findById(eq(404L))).thenReturn(Maybe.empty());

        this.recipeService.get(404L);
    }

}
