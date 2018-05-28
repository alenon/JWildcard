package com.yevdo.jwildcard

import spock.lang.Specification

/**
 * @author Yevdo Abramov
 * Created on 28/05/2018
 */
class JWildcardRulesSpec extends Specification {

    def "test constructors"() {
        when:
        JWildcardRules rules = new JWildcardRules(null)

        then:
        rules.rules.isEmpty()

        when:
        rules = new JWildcardRules()

        then:
        rules.rules.isEmpty()

        when:
        rules = new JWildcardRules([
                new JWildcardRule("source", "target"),
                new JWildcardRule("source2", "target2")
        ].toSet())

        then:
        rules.rules.size() == 2
    }

    def "test addRule"() {
        given:
        JWildcardRules rules = new JWildcardRules()

        expect:
        rules.rules.isEmpty()

        when:
        rules.addRule(null)

        then:
        thrown(IllegalArgumentException)

        when:
        rules.addRule(new JWildcardRule("source", "target"))

        then:
        rules.rules.size() == 1
    }

    def "test addRules"() {
        given:
        JWildcardRules rules = new JWildcardRules()

        expect:
        rules.rules.isEmpty()

        when:
        rules.addRule(null)

        then:
        thrown(IllegalArgumentException)

        when:
        rules.addRules([
                new JWildcardRule("source", "target"),
                new JWildcardRule("source2", "target2")
        ])

        then:
        rules.rules.size() == 2
    }

    def "test removeRule"() {
        given:
        JWildcardRule theRule = new JWildcardRule("source", "target")
        JWildcardRules rules = new JWildcardRules([theRule].toSet())

        expect:
        rules.rules.size() == 1

        when:
        rules.removeRule(null)

        then:
        thrown(IllegalArgumentException)

        when:
        rules.removeRule(theRule)

        then:
        rules.rules.isEmpty()
    }

    def "test getRules"() {
        when:
        JWildcardRules rules = new JWildcardRules([
                new JWildcardRule("source", "target"),
                new JWildcardRule("source2", "target2")
        ].toSet())

        then:
        rules.getRules().size() == 2
    }
}
