package de.washeuteessen.search;

import lombok.AllArgsConstructor;
import org.hibernate.search.jpa.Search;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
@AllArgsConstructor
public class IndexConfiguration {

    private EntityManager em;
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void startIndexing() {
        try {
            Search.getFullTextEntityManager(this.entityManagerFactory.createEntityManager())
                    .createIndexer().startAndWait();
        } catch (InterruptedException e) {
            throw new RuntimeException("could not build index", e);
        }
    }

}
