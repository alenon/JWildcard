package com.yevdo.jwildcard;

import com.yevdo.jwildcard.converter.JWildcardPair;
import com.yevdo.jwildcard.converter.JWildcardRules;
import com.yevdo.jwildcard.converter.JWildcardToRegex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yevdo Abramov
 * Created on 26/03/2018
 */
public class JWildcard {

    private static final JWildcardPair QUESTION_MARK = new JWildcardPair("?", ".");
    private static final JWildcardPair STAR = new JWildcardPair("*", ".*");
    private static final JWildcardRules DEFAULT_RULES = new JWildcardRules(new HashSet<>(Arrays.asList(QUESTION_MARK, STAR)));

    public static String wildcardToRegex(final String wildcard) {
        return JWildcardToRegex.wildcardToRegex(wildcard, DEFAULT_RULES, true);
    }

    public static String wildcardToRegex(final String wildcard, boolean strict) {
        return JWildcardToRegex.wildcardToRegex(wildcard, DEFAULT_RULES, strict);
    }

    public static boolean matches(String wildcard, String text) {
        Pattern pattern = Pattern.compile(wildcardToRegex(wildcard));
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}

