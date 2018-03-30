package com.yevdo.jwildcard;

/**
 * @author Yevdo Abramov
 * Created on 26/03/2018
 */
public class JWildcardRule {
    private final String source;
    private final String target;

    /**
     * JWildcardRule - represents wildcard rule model
     * @param source a substring to seek for, can't be null
     * @param target a substring to replace by, can't be null
     * @throws IllegalArgumentException if one of the above (key, value) is null
     */
    public JWildcardRule(String source, String target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Empty values are not allowed");
        }

        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JWildcardRule that = (JWildcardRule) o;

        if (!source.equals(that.source)) return false;
        return target.equals(that.target);
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + target.hashCode();
        return result;
    }
}
