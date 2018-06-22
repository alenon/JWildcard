package com.yevdo.jwildcard

import spock.lang.Specification

/**
 * @author Yevdo Abramov
 * Created on 28/05/2018
 */
class JWildcardRuleSpec extends Specification {

    def "test constructor"() {
        when: "source is null"
        new JWildcardRule(null, "abc")

        then:
        thrown(IllegalArgumentException)

        when: "target is null"
        new JWildcardRule("abc", null)

        then:
        thrown(IllegalArgumentException)

        when: "valid constructor"
        new JWildcardRule("abc", "cde")

        then:
        noExceptionThrown()

        when: "regexRule is null"
        new JWildcardRule(null)

        then:
        thrown(IllegalArgumentException)

        when: "valid constructor"
        new JWildcardRule("abc")

        then:
        noExceptionThrown()
    }

    def "test equals"() {
        given:
        JWildcardRule rule1 = new JWildcardRule("source1", "target")
        JWildcardRule rule2 = new JWildcardRule("source", "target")

        when: "when sources are different"
        boolean equal = rule1.equals(rule2)

        then: "not equals"
        !equal

        when: "when targets are different"
        rule1 = new JWildcardRule("source", "target1")
        equal = rule1.equals(rule2)

        then: "not equals"
        !equal

        when: "when source and targets are equal"
        rule1 = new JWildcardRule("source", "target")
        equal = rule1.equals(rule2)

        then: "equal"
        equal
    }

    def "test hashCode"() {
        given:
        JWildcardRule rule = new JWildcardRule("source", "target")

        when:
        int expectedHashCode = 31 * rule.source.hashCode() + rule.target.hashCode()
        int hashCode = rule.hashCode()

        then:
        expectedHashCode == hashCode
    }
}
