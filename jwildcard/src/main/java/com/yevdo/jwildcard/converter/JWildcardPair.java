package com.yevdo.jwildcard.converter;

/**
 * @author Yevdo Abramov
 * Created on 26/03/2018
 */
public class JWildcardPair {
    private final String key;
    private final String value;

    public JWildcardPair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
