package de.washeuteessen.recipe;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class RecipeMetrics {

    private final Counter redirect_recipes;
    private final AtomicLong total_recipes;
    private final AtomicLong indexed_recipes;

    public RecipeMetrics() {
        this.total_recipes = Metrics.gauge("recipes.total", new AtomicLong());
        this.indexed_recipes = Metrics.gauge("recipes.indexed", new AtomicLong());
        this.redirect_recipes = Metrics.counter("recipes.redirects");
    }

    public RecipeMetrics setTotalRecipes(final long count) {
        this.total_recipes.set(count);
        return this;
    }

    public RecipeMetrics incrementRedirectRecipes() {
        this.redirect_recipes.increment();
        return this;
    }

    public RecipeMetrics setIndexedRecipes(final long count) {
        this.indexed_recipes.set(count);
        return this;
    }

}
