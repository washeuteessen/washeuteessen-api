package de.washeuteessen.search;

import de.washeuteessen.recipe.Recipe;
import de.washeuteessen.recipe.RecipeMetrics;
import lombok.AllArgsConstructor;
import org.apache.lucene.index.IndexReader;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@AllArgsConstructor
public class IndexService {

    private EntityManager em;
    private RecipeMetrics recipeMetrics;

    public long getRecipeCountInIndex() {
        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.em);
        final IndexReader reader = fullTextEntityManager.getSearchFactory().getIndexReaderAccessor().open(Recipe.class);
        return reader.numDocs();
    }

    @Scheduled(fixedRate = 30000)
    @Transactional
    public void countForMetrics() {
        final Long total = this.getRecipeCountInIndex();
        this.recipeMetrics.setIndexedRecipies(total);
    }

}
