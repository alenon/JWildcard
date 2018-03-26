package com.yevdo.jwildcard.converter;

import java.util.Set;

/**
 * @author Yevdo Abramov
 * Created on 26/03/2018
 */
public class JWildcardRules {
    private Set<JWildcardPair> rules;

    public JWildcardRules(Set<JWildcardPair> rules) {
        this.rules = rules;
    }

    public Set<JWildcardPair> getRules() {
        return rules;
    }
}
