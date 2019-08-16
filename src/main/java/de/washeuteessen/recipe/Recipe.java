package de.washeuteessen.recipe;

import lombok.Builder;
import lombok.Data;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Indexed
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field(termVector = TermVector.YES, store = Store.YES)
    private String title;

    @Field(index = Index.NO, store = Store.NO)
    private String url;

    @Field(index = Index.NO, store = Store.YES)
    private String imageSrc;

    @Field(index = Index.NO, store = Store.YES)
    private String source;

    @IndexedEmbedded
    @Field(termVector = TermVector.YES, store = Store.NO)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "INGREDIENTS",
            joinColumns = @JoinColumn(name = "RECIPE_ID")
    )
    @Column(name = "INGREDIENT")
    private List<String> ingredients;

    public Recipe() {

    }

    public Recipe(final Object[] objArr) {
        this.id = (Long) objArr[0];
        this.title = (String) objArr[1];
        this.source = (String) objArr[2];
        this.imageSrc = (String) objArr[3];
    }

    @Builder
    public Recipe(Long id, String title, String url, String imageSrc, String source, List<String> ingredients) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.imageSrc = imageSrc;
        this.source = source;
        this.ingredients = ingredients;
    }
}
