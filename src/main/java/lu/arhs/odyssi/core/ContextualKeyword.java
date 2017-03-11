package lu.arhs.odyssi.core;

/**
 * @author bollenma
 */
public class ContextualKeyword {


    private ContextEnum context;
    private String keyword;

    public ContextEnum getContext() {
        return context;
    }

    public void setContext(final ContextEnum context) {
        this.context = context;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(final String keyword) {
        this.keyword = keyword;
    }
}
