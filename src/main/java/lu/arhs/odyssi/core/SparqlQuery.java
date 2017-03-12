package lu.arhs.odyssi.core;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SparqlQuery {

    private String namespace = "prefix dct: <http://purl.org/dc/terms/> \n" +
            "prefix dcat:  <http://www.w3.org/ns/dcat#> \n" +
            "prefix skos: <http://www.w3.org/2004/02/skos/core#>\n" +
            "prefix eurovoc: <http://eurovoc.europa.eu/>\n" +
            "prefix dataset:      <https://data.public.lu/en/datasets/> ";

    private String select = "dataset_uri";
    private String where = "  { " +
            "    ?concept skos:prefLabel \"garbage\" . " +
            "    ?dataset_uri dct:concept ?concept. " +
            "  } " +
            "  union " +
            "  { " +
            "    ?dataset_uri dct:title ?labels. " +
            "          FILTER regex(?labels, \"garbage\", \"i\" ). " +
            "  } " +
            "  union " +
            "  { " +
            "    ?concept skos:prefLabel \"garbage\" . " +
            "    ?concept skos:altLabel ?altlabels " +
            "    ?dataset_uri dct:title ?dataset_title. " +
            "    FILTER regex(?dataset_title, ?dataset_title, \"i\" ). " +
            "  } " +
            "    union " +
            "  { " +
            "    ?concept skos:prefLabel \"garbage\" . " +
            "    ?concept skos:altLabel ?altlabels " +
            "    ?dataset_uri dct:description ?dataset_title. " +
            "    FILTER regex(?dataset_title, ?dataset_title, \"i\" ). " +
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
        return namespace + "select distinct ?" + select + " where { " + where + " } ";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final SparqlQuery that = (SparqlQuery) o;

        return new EqualsBuilder()
                .append(namespace, that.namespace)
                .append(select, that.select)
                .append(where, that.where)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(namespace)
                .append(select)
                .append(where)
                .toHashCode();
    }
}
