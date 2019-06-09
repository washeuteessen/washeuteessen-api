package de.washeuteessen.search;

import de.washeuteessen.recipe.Recipe;
import lombok.AllArgsConstructor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SearchService {

    private EntityManager em;
    private SearchMetrics searchMetrics;

    public List<Recipe> search(final String s, final Optional<Integer> offset, final Optional<Integer> limit) {

        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.em);

        final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Recipe.class)
                .get();

        final org.apache.lucene.search.Query query = queryBuilder.keyword()
                .onField("title")
                .boostedTo(5)
                .andField("ingredients")
                .matching(s)
                .createQuery();

        final FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Recipe.class);
        jpaQuery.setFirstResult(offset.orElse(0));
        jpaQuery.setMaxResults(limit.orElse(20));

        if (!offset.isPresent() || offset.get() == 0) {
            this.searchMetrics.incrementTotalSearches();
        }

        return jpaQuery.getResultList();
    }

}
