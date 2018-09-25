package filters;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * A filter that represents the logical and operator
 */
public class OrFilter implements Filter {
    private final Filter before;
    private final Filter after;

    public OrFilter(Filter before, Filter after) {
        this.before = before;
        this.after = after;
    }

    /**
     * An and filter matches when both children match
     * @param s     the tweet to check
     * @return      whether or not it matches
     */
    @Override
    public boolean matches(Status s) {
        return before.matches(s) || after.matches(s);
    }

    @Override
    public List<String> terms() {
        return new ArrayList<String>() { { addAll(before.terms()); addAll(after.terms()); } };
    }

    public String toString() {
        return "(" + before.toString() + " or " + after.toString() + ")";
    }
}
