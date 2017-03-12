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

    final static private String SEARCHED_URI = "dataset_uri";

    @Override
    public SparqlQuery process(final SemanticQuery semanticQuery) {
        Validate.notNull(semanticQuery);

        final Collection<ContextualKeyword> contextualKeywords = semanticQuery.getContextualKeywords();

        String where = "";
        for (final ContextualKeyword contextualKeyword : contextualKeywords) {

            where += createWhere(contextualKeyword);

        }

        SparqlQuery ret = new SparqlQuery();
        ret.setSelect(SEARCHED_URI);
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
                // Search with the altlabels in the title
                "      ?dataset_uri dct:title ?dataset_title. " +
                "      optional {" +
                "           { ?concept skos:prefLabel \"" + keyword + "\" .}" +
                "               union" +
                "           {?concept skos:altLabel \"" + keyword + "\" .}" +
                "       } " +
                "      ?concept skos:altLabel ?altlabels ." +
                "      FILTER regex(?dataset_title, ?altlabels, \"i\" ). " +
                "} UNION {" +
                // Search with the preflabels in the title
                "      ?dataset_uri dct:title ?dataset_title. " +
                "      optional {" +
                "           { ?concept skos:prefLabel \"" + keyword + "\" .}" +
                "               union" +
                "           {?concept skos:altLabel \"" + keyword + "\" .}" +
                "       } " +
                "      ?concept skos:prefLabel ?prefLabel ." +
                "      FILTER regex(?dataset_title, ?prefLabel, \"i\" ). " +
                "} UNION {" +
                // Search with the altlabels in the description
                "      ?dataset_uri dct:description ?dataset_description. " +
                "      optional {" +
                "           { ?concept skos:prefLabel \"" + keyword + "\" .}" +
                "               union" +
                "           {?concept skos:altLabel \"" + keyword + "\" .}" +
                "       } " +
                "      ?concept skos:altLabel ?altlabels ." +
                "      FILTER regex(?dataset_description, ?altlabels, \"i\" ). " +
                "} UNION {" +
                // Search with the preflabels in the description
                "      ?dataset_uri dct:description ?dataset_description. " +
                "      optional {" +
                "           { ?concept skos:prefLabel \"" + keyword + "\" .}" +
                "               union" +
                "           {?concept skos:altLabel \"" + keyword + "\" .}" +
                "       } " +
                "      ?concept skos:prefLabel ?prefLabel ." +
                "      FILTER regex(?dataset_description, ?prefLabel, \"i\" ). " +

                "}";
    }

    private String constructPositionQuery(final String keyword) {
        return "";
    }

}
