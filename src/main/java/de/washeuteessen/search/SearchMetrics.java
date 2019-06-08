package de.washeuteessen.search;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Service;

@Service
public class SearchMetrics {

    private final Counter total_searches;

    public SearchMetrics() {
        this.total_searches = Metrics.counter("searches.total");
    }

    public SearchMetrics incrementTotalSearches() {
        this.total_searches.increment();
        return this;
    }

}
