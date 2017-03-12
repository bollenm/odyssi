package lu.arhs.odyssi.keywords2sparql.impl;

import java.util.Collection;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import lu.arhs.odyssi.core.ContextEnum;
import lu.arhs.odyssi.core.ContextualKeyword;
import lu.arhs.odyssi.core.SemanticQuery;
import lu.arhs.odyssi.core.SparqlQuery;
import lu.arhs.odyssi.keywords2sparql.Keywords2Sparql;

/**
 * @author bollenma
 */
@Component
public class Keywords2SparqlImpl implements Keywords2Sparql {

    @Override
    public SparqlQuery process(final SemanticQuery semanticQuery) {
        Validate.notNull(semanticQuery);

        final Collection<ContextualKeyword> contextualKeywords = semanticQuery.getContextualKeywords();

        String where = "where {";
        for (final ContextualKeyword contextualKeyword : contextualKeywords) {

            where += createWhere(contextualKeyword);

        }

        where += "}";

        SparqlQuery ret = new SparqlQuery();
        ret.setWhere(where);
        return ret;
    }

    private String createWhere(final ContextualKeyword contextualKeyword) {
        final ContextEnum context = contextualKeyword.getContext();
        final String keyword = contextualKeyword.getKeyword();
        String ret = "";

        switch (context) {
            case SUBJECT:

                ret += constructSubjectQuery(keyword);

                break;
            case LOCATION:

                break;
            default:
                break;
        }

        return ret;
    }

    private String constructSubjectQuery(final String keyword) {
        return "{" +
                "       ?concept skos:prefLabel \"" + keyword + "\" ." +
                "       ?dataset_id dct:concept ?concept." +
                "} UNION {" +
                "      ?concept skos:prefLabel \"" + keyword + "\" ." +
                "      ?concept skos:altLabel ?altlabels " +
                "      ?dataset_id dct:title ?dataset_title. " +
                "      FILTER regex(?dataset_title, ?altlabels, \"i\" ). " +
                "} UNION {" +
                "      ?concept skos:prefLabel \"" + keyword + "\" ." +
                "      ?concept skos:altLabel ?altlabels " +
                "      ?dataset_id dct:description ?dataset_description. " +
                "      FILTER regex(?dataset_description, ?altlabels, \"i\" ). " +
                "}";
    }

    private String constructPositionQuery(final String keyword) {
        return "{" +
                "       ?concept skos:prefLabel \"" + keyword + "\" ." +
                "       ?dataset_id dct:concept ?concept." +
                "} UNION {" +
                "      ?concept skos:prefLabel \"" + keyword + "\" ." +
                "      ?concept skos:altLabel ?altlabels " +
                "      ?dataset_id dct:title ?dataset_title. " +
                "      FILTER regex(?labels, " + keyword + ", \"i\" ). " +
                "} UNION {" +
                "      ?concept skos:prefLabel \"" + keyword + "\" ." +
                "      ?concept skos:altLabel ?altlabels " +
                "      ?dataset_id dct:description ?dataset_title. " +
                "      FILTER regex(?labels, " + keyword + ", \"i\" ). " +
                "}";
    }

}
