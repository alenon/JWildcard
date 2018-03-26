package com.yevdo.jwildcard.converter;

/**
 * @author Yevdo Abramov
 * Created on 26/03/2018
 */
class JWildcardPairWithIndex {
    private final JWildcardPair jWildcardPair;
    private final int index;

    JWildcardPairWithIndex(JWildcardPair jWildcardPair, int weight) {
        this.jWildcardPair = jWildcardPair;
        this.index = weight;
    }

    public JWildcardPair getjWildcardPair() {
        return jWildcardPair;
    }

    public int getIndex() {
        return index;
    }
}
