# JWildcard - wildcard utilities for Java

### Description
JWildcard is using an ultimate way to convert wildcard to regex (enclosing all not wildcard specific parts by regex quotes, so no special chars processing needed):

This wildcard:

    "mywil?card*"

will be converted to this regex string:

    "\Qmywil\E.\Qcard\E.*"

### Examples
If you wish to convert wildcard to regex string use:

    JWildcard.wildcardToRegex("mywil?card*");

If you wish to check the matching directly you can use this:

    JWildcard.matches("mywild*", "mywildcard");


Default wildcard rule are "?" -> ".", "*" -> ".*", but you can change the default behaviour if you wish, by simply defining the new rules.

    JWildcard.wildcardToRegex(wildcard, rules, strict);

### Installation

You can use sources or download it directly using maven or gradle from Bintray JCenter: https://bintray.com/yevdo/jwildcard/jwildcard

Gradle way:

    compile 'com.yevdo:jwildcard:1.0'


Maven way:

    <dependency>
      <groupId>com.yevdo</groupId>
      <artifactId>jwildcard</artifactId>
      <version>1.0</version>
      <type>pom</type>
    </dependency>