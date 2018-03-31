package com.yevdo.jwildcard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yevdo Abramov
 * Created on 26/03/2018
 */
public class JWildcard {

    private static final JWildcardRule QUESTION_MARK_RULE = new JWildcardRule("?", ".");
    private static final JWildcardRule STAR_RULE = new JWildcardRule("*", ".*");
    private static final JWildcardRules DEFAULT_RULES = new JWildcardRules(new HashSet<>(Arrays.asList(QUESTION_MARK_RULE, STAR_RULE)));

    /**
     * Converts wildcard to regex using default set of rules and strict flag set to true
     *
     * @param wildcard a string representation of wildcard
     * @return <tt>string</tt> representation of regex
     */
    public static String wildcardToRegex(final String wildcard) {
        return wildcardToRegex(wildcard, DEFAULT_RULES, true);
    }

    /**
     * Converts wildcard to regex using default set of rules
     *
     * @param wildcard a string representation of wildcard
     * @param strict   a flag which indicates whether to wrap the result regex with ^ and $
     * @return <tt>string</tt> representation of regex
     */
    public static String wildcardToRegex(final String wildcard, boolean strict) {
        return wildcardToRegex(wildcard, DEFAULT_RULES, strict);
    }

    /**
     * Converts wildcard to regex using rules
     *
     * @param wildcard a string representation of wildcard
     * @param rules    a collection of desired wildcard rules to use in conversion process
     * @param strict   a flag which indicates whether to wrap the result regex with ^ and $
     * @return <tt>string</tt> representation of regex
     * @throws IllegalArgumentException if one of the above is null (wildcard, rules)
     */
    public static String wildcardToRegex(final String wildcard, final JWildcardRules rules, boolean strict) {
        return JWildcardToRegex.wildcardToRegex(wildcard, rules, strict);
    }

    /**
     * Will automatically convert wildcard to regex using the default set of rules,
     * and strict flag set to true, and then run matcher on text
     *
     * @param wildcard the wildcard
     * @param text the string to be matched at provided wildcard
     * @return <tt>true</tt> if the text matches the wildcard
     * @throws IllegalArgumentException if one of the above is null (wildcard, text)
     */
    public static boolean matches(String wildcard, String text) {
        if(text == null) {
            throw new IllegalArgumentException("Text must not be null");
        }

        Pattern pattern = Pattern.compile(wildcardToRegex(wildcard));
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    // ==================
    // == Private ZONE ==
    // ==================

    private static class JWildcardToRegex {

        private static String wildcardToRegex(final String wildcard, final JWildcardRules rules, boolean strict) {
            if (wildcard == null) {
                throw new IllegalArgumentException("Wildcard must not be null");
            }

            if (rules == null) {
                throw new IllegalArgumentException("Rules must not be null");
            }

            List<JWildcardRuleWithIndex> listOfOccurrences = getContainedWildcardPairsOrdered(wildcard, rules);
            String regex = getRegexString(wildcard, listOfOccurrences);

            if (strict) {
                return "^" + regex + "$";
            } else {
                return regex;
            }
        }

        private static String getRegexString(String wildcard, List<JWildcardRuleWithIndex> listOfOccurrences) {
            StringBuilder regex = new StringBuilder();
            int cursor = 0;
            for (JWildcardRuleWithIndex jWildcardRuleWithIndex : listOfOccurrences) {
                int index = jWildcardRuleWithIndex.getIndex();
                if (index != 0) {
                    regex.append(Pattern.quote(wildcard.substring(cursor, index)));
                }
                regex.append(jWildcardRuleWithIndex.getRule().getTarget());
                cursor = index + jWildcardRuleWithIndex.getRule().getSource().length();
            }

            if (cursor <= wildcard.length() - 1) {
                regex.append(Pattern.quote(wildcard.substring(cursor, wildcard.length())));
            }
            return regex.toString();
        }

        private static List<JWildcardRuleWithIndex> getContainedWildcardPairsOrdered(final String wildcard, final JWildcardRules rules) {
            List<JWildcardRuleWithIndex> listOfOccurrences = new LinkedList<>();
            for (JWildcardRule jWildcardRuleWithIndex : rules.getRules()) {
                int index = -1;
                do {
                    index = wildcard.indexOf(jWildcardRuleWithIndex.getSource(), index + 1);
                    if (index > -1) {
                        listOfOccurrences.add(new JWildcardRuleWithIndex(jWildcardRuleWithIndex, index));
                    }
                } while (index > -1);
            }

            listOfOccurrences.sort((o1, o2) -> {
                if (o1.getIndex() == o2.getIndex()) {
                    return 0;
                }

                return o1.getIndex() > o2.getIndex() ? 1 : -1;
            });

            return listOfOccurrences;
        }
    }

    private static class JWildcardRuleWithIndex {
        private final JWildcardRule rule;
        private final int index;

        JWildcardRuleWithIndex(JWildcardRule rule, int index) {
            this.rule = rule;
            this.index = index;
        }

        private JWildcardRule getRule() {
            return rule;
        }

        private int getIndex() {
            return index;
        }
    }
}
