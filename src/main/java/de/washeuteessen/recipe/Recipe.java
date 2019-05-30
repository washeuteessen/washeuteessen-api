package de.washeuteessen.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@Indexed
@AllArgsConstructor
@NoArgsConstructor
@AnalyzerDef(name = "customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "German")
                })
        })
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field(termVector = TermVector.YES)
    @Analyzer(definition = "customanalyzer")
    private String title;

    private String url;

    @IndexedEmbedded
    @Field(termVector = TermVector.YES)
    @Analyzer(definition = "customanalyzer")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> ingredients;

}
