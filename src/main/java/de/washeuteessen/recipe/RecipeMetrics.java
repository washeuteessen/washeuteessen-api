package de.washeuteessen.recipe;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class RecipeMetrics {

    private final Counter redirect_recipies;
    private final AtomicLong total_recipies;
    private final AtomicLong indexed_recipies;

    public RecipeMetrics() {
        this.total_recipies = Metrics.gauge("recipies.total", new AtomicLong());
        this.indexed_recipies = Metrics.gauge("recipies.indexed", new AtomicLong());
        this.redirect_recipies = Metrics.counter("recipies.redirects");
    }

    public RecipeMetrics setTotalRecipies(final long count) {
        this.total_recipies.set(count);
        return this;
    }

    public RecipeMetrics incrementRedirectRecipies() {
        this.redirect_recipies.increment();
        return this;
    }

    public RecipeMetrics setIndexedRecipies(final long count) {
        this.indexed_recipies.set(count);
        return this;
    }

}
