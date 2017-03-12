package lu.arhs.odyssi.core;

import org.apache.commons.lang3.Validate;

public class SparqlQuery {

    private String namespace = "PREFIX dct: <http://purl.org/dc/terms/> " +
            "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> ";
    private String select = " select distinct ?dataset_id ";
    private String where = "WHERE { " +
            "  { " +
            "    ?concept skos:prefLabel \"poubelle\" . " +
            "    ?dataset_id dct:concept ?concept. " +
            "  } " +
            "  union " +
            "  { " +
            "    ?dataset_id dct:title ?labels. " +
            "          FILTER regex(?labels, \"poubelle\", \"i\" ). " +
            "  } " +
            "  union " +
            "  { " +
            "    ?concept skos:prefLabel \"poubelle\" . " +
            "    ?concept skos:altLabel ?altlabels " +
            "    ?dataset_id dct:title ?dataset_title. " +
            "    FILTER regex(?dataset_title, ?dataset_title, \"i\" ). " +
            "  } " +
            "    union " +
            "  { " +
            "    ?concept skos:prefLabel \"poubelle\" . " +
            "    ?concept skos:altLabel ?altlabels " +
            "    ?dataset_id dct:description ?dataset_title. " +
            "    FILTER regex(?dataset_title, ?dataset_title, \"i\" ). " +
            "  }" +
            "}";

    public SparqlQuery() {
    }

    public SparqlQuery(final String select, final String where) {
        Validate.notNull(select);
        Validate.notNull(where);

        this.select = select;
        this.where = where;
    }

    public SparqlQuery(final String namespace, final String select, final String where) {
        Validate.notNull(namespace);
        Validate.notNull(select);
        Validate.notNull(where);

        this.namespace = namespace;
        this.select = select;
        this.where = where;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(final String select) {
        this.select = select;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(final String where) {
        this.where = where;
    }

    public String constructQuery() {
        return namespace + select + where;
    }

}
