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

@Service
@AllArgsConstructor
public class SearchService {

    private EntityManager em;

    public List<Recipe> search(final String s) {

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
        return jpaQuery.getResultList();
    }

}
