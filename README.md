# JWildcard

A collection of wildcard utilities. This library helps to convert wildcards to regex string. Wildcards can be declared using custom made rules.

### Getting Started

You can use sources or download it directly using maven or gradle from Bintray JCenter: https://bintray.com/yevdo/jwildcard/jwildcard

Gradle <code>build.gradle</code>

    repositories {
        jcenter()
    }
    
    dependencies {
        compile 'com.yevdo:jwildcard:1.2'
    }


Maven <code>pom.xml</code>

    <dependency>
      <groupId>com.yevdo</groupId>
      <artifactId>jwildcard</artifactId>
      <version>1.2</version>
      <type>pom</type>
    </dependency>

### API
API documentation https://alenon.github.io/JWildcard/

### Examples:

This wildcard:

    "mywil?card*"

will be converted to this regex string:

    "\Qmywil\E.\Qcard\E.*"

If you wish to convert wildcard to regex string use:

    JWildcard.wildcardToRegex("mywil?card*");

Default wildcard rule are "?" -> ".", "*" -> ".*", but you can change the default behaviour if you wish to, by simply defining the new rules.

    JWildcard.wildcardToRegex(wildcard, rules, strict);

If you wish to check matching directly you can use this:

    JWildcard.matches("mywild*", "mywildcard");
