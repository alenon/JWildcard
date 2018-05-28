package com.yevdo.jwildcard;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Yevdo Abramov
 * Created on 26/03/2018
 */
public class JWildcardRules {
    private Set<JWildcardRule> rules;

    /**
     * JWildcardRules represents a set of rules to use while converting
     * wildcard to regex string
     */
    public JWildcardRules() {
        rules = new HashSet<>();
    }

    /**
     * JWildcardRules represents a set of rules to use while converting
     * wildcard to regex string
     *
     * @param rules a collection of JWildcardRule
     */
    public JWildcardRules(final Set<JWildcardRule> rules) {
        this.rules = (rules != null) ? new HashSet<>(rules) : new HashSet<>();
    }

    /**
     * Add a rule to the existing rules
     *
     * @param rule JWildcardRule
     * @return <tt>true</tt> if the rules set did not already contain the specified element
     */
    public boolean addRule(JWildcardRule rule) {
        if (rule == null) {
            throw new IllegalArgumentException("Rule can't be null");
        }
        return rules.add(rule);
    }

    /**
     * Add a set of rules to the existing rules
     *
     * @param rules a collection of JWildcardRule
     * @return <tt>true</tt> if the rules set did not already contain the specified elements
     */
    public boolean addRules(Collection<JWildcardRule> rules) {
        if (rules == null) {
            throw new IllegalArgumentException("Rules list can't be null");
        }

        return this.rules.addAll(rules);
    }

    /**
     * Removes the specified element from this set if it is present
     *
     * @param rule JWildcardRule
     * @return <tt>true</tt> if this set contained the specified element
     */
    public boolean removeRule(JWildcardRule rule) {
        if (rule == null) {
            throw new IllegalArgumentException("Rule to remove can't be null");
        }

        return rules.remove(rule);
    }

    public Set<JWildcardRule> getRules() {
        return rules;
    }
}
