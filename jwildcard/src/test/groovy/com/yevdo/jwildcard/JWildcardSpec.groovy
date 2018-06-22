package com.yevdo.jwildcard

import spock.lang.Specification

/**
 * @author Yevdo Abramov
 * Created on 26/03/2018
 */
class JWildcardSpec extends Specification {

    def "convert wildcard to regex"() {
        when:
        String regex = JWildcard.wildcardToRegex("mywil?c")

        then:
        regex == "^\\Qmywil\\E.\\Qc\\E\$"

        when:
        regex = JWildcard.wildcardToRegex("mywil?card*")

        then:
        regex == "^\\Qmywil\\E.\\Qcard\\E.*\$"

        when:
        regex = JWildcard.wildcardToRegex("mywil?card*", false)

        then:
        regex == "\\Qmywil\\E.\\Qcard\\E.*"

        when:
        regex = JWildcard.wildcardToRegex("?card*", false)

        then:
        regex == ".\\Qcard\\E.*"

        when:
        regex = JWildcard.wildcardToRegex("?card*wild", false)

        then:
        regex == ".\\Qcard\\E.*\\Qwild\\E"
    }

    def "check wildcard to regex with rules"() {
        given:
        JWildcardRules rules = null

        when: "rules is null"
        JWildcard.wildcardToRegex("abc*efg", rules, false)

        then:
        thrown(IllegalArgumentException)

        when: "rule * -> \\w*"
        JWildcardRule starRule = new JWildcardRule("*", "\\w*")
        rules = new JWildcardRules(Collections.singleton(starRule))
        String regex = JWildcard.wildcardToRegex("abc*efg", rules, false)

        then:
        regex == "\\Qabc\\E\\w*\\Qefg\\E"

        when: "add rule"
        rules.addRule(new JWildcardRule("a", "a+"))
        regex = JWildcard.wildcardToRegex("abc*efg", rules, false)

        then:
        regex == "a+\\Qbc\\E\\w*\\Qefg\\E"

        when: "remove rule"
        rules.removeRule(starRule)
        regex = JWildcard.wildcardToRegex("abc*efg", rules, false)

        then:
        regex == "a+\\Qbc*efg\\E"

        when:
        JWildcardRule regexRule = new JWildcardRule("[4-9]")
        rules = new JWildcardRules([regexRule].toSet())
        regex = JWildcard.wildcardToRegex("test[4-9]", rules, false)

        then:
        regex == "\\Qtest\\E[4-9]"
        "test3" != ~regex
        "test6" ==~ regex
    }

    def "check matcher"() {

        when: "wildcard is null"
        JWildcard.matches(null, "mywildcard")

        then:
        thrown(IllegalArgumentException)

        when: "text is null"
        JWildcard.matches("mywild*", null)

        then:
        thrown(IllegalArgumentException)

        when:
        boolean matches = JWildcard.matches("mywild*", "mywildcard")

        then:
        matches

        when:
        matches = JWildcard.matches("mywild*", "mywilcard")

        then:
        !matches

        when:
        matches = JWildcard.matches("mywildcard", "mywild*")

        then:
        !matches

        when:
        matches = JWildcard.matches("mywildcard", "mywildcard")

        then:
        matches

        when:
        matches = JWildcard.matches("Lond?n", "London")

        then:
        matches
    }
}