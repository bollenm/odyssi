package lu.arhs.odyssi.core;

import java.util.Collection;

/**
 * @author bollenma
 */
public class SemanticQuery {

    private Collection<ContextualKeyword> contextualKeywords;

    public Collection<ContextualKeyword> getContextualKeywords() {
        return contextualKeywords;
    }

    public void setContextualKeywords(final Collection<ContextualKeyword> contextualKeywords) {
        this.contextualKeywords = contextualKeywords;
    }
}
