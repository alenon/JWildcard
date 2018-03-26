package com.yevdo.jwildcard.converter;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Yevdo Abramov
 * Created on 26/03/2018
 */
public class JWildcardToRegex {

    public static String wildcardToRegex(final String wildcard, final JWildcardRules rules, boolean strict) {
        if (wildcard == null) {
            throw new IllegalArgumentException("Wildcard must not be null");
        }

        List<JWildcardPairWithIndex> listOfOccurrences = getContainedWildcardPairsOrdered(wildcard, rules);

        StringBuilder regex = new StringBuilder();
        int cursor = 0;
        for (JWildcardPairWithIndex jWildcardPairWithIndex : listOfOccurrences) {
            int index = jWildcardPairWithIndex.getIndex();
            if (index != 0) {
                regex.append(Pattern.quote(wildcard.substring(cursor, index)));
            }
            regex.append(jWildcardPairWithIndex.getjWildcardPair().getValue());
            cursor = index + jWildcardPairWithIndex.getjWildcardPair().getKey().length();
        }

        if (cursor < wildcard.length() - 1) {
            regex.append(Pattern.quote(wildcard.substring(cursor, wildcard.length())));
        }

        if (strict) {
            return "^" + regex.toString() + "$";
        } else {
            return regex.toString();
        }
    }

    private static List<JWildcardPairWithIndex> getContainedWildcardPairsOrdered(final String wildcard, final JWildcardRules rules) {
        List<JWildcardPairWithIndex> listOfOccurrences = new LinkedList<>();
        rules.getRules().forEach(jWildcardPair -> {
            int index = -1;
            do {
                index = wildcard.indexOf(jWildcardPair.getKey(), index + 1);
                if (index > -1) {
                    listOfOccurrences.add(new JWildcardPairWithIndex(jWildcardPair, index));
                }
            } while (index > -1);
        });

        listOfOccurrences.sort((o1, o2) -> {
            if (o1.getIndex() == o2.getIndex()) {
                return 0;
            }

            return o1.getIndex() > o2.getIndex() ? 1 : -1;
        });

        return listOfOccurrences;
    }
}
