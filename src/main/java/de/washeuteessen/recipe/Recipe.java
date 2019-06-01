package de.washeuteessen.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field(termVector = TermVector.YES)
    private String title;

    private String url;
    private String imageSrc;

    @IndexedEmbedded
    @Field(termVector = TermVector.YES)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "INGREDIENTS",
            joinColumns = @JoinColumn(name = "RECIPE_ID")
    )
    @Column(name = "INGREDIENT")
    private List<String> ingredients;

}
