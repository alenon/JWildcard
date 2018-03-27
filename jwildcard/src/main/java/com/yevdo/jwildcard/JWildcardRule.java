package com.yevdo.jwildcard;

/**
 * @author Yevdo Abramov
 * Created on 26/03/2018
 */
public class JWildcardRule {
    private final String key;
    private final String value;

    /**
     * JWildcardRule - represents wildcard rule model
     * @param key a substring to seek for, can't be null
     * @param value a substring to replace by, can't be null
     * @throws IllegalArgumentException if one of the above (key, value) is null
     */
    public JWildcardRule(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Empty values are not allowed");
        }

        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (!JWildcardRule.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final JWildcardRule other = (JWildcardRule) obj;
        return this.key.equals(other.key) && this.value.equals(other.value);

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.key.hashCode();
        hash = 53 * hash + this.value.hashCode();
        return hash;
    }
}
