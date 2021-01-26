# JWildcard

A collection of wildcard utilities. This library helps to convert wildcards to regex string. Wildcards can be declared using custom made rules.


### Getting Started

![Master Build](https://github.com/alenon/JWildcard/workflows/Java%20CI%20with%20Maven/badge.svg)

[![Download](https://api.bintray.com/packages/yevdo/jwildcard/jwildcard/images/download.svg)](https://bintray.com/yevdo/jwildcard/jwildcard/_latestVersion)
<br />
[![Sonar quality gates](https://sonarcloud.io/api/project_badges/measure?project=com.yevdo.jwildcard&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.yevdo.jwildcard)
[![Sonar Lines of code](https://sonarcloud.io/api/project_badges/measure?project=com.yevdo.jwildcard&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.yevdo.jwildcard)
[![Sonar Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.yevdo.jwildcard&metric=coverage&123)](https://sonarcloud.io/dashboard?id=com.yevdo.jwildcard)

Gradle <code>build.gradle</code>
```gradle
repositories {
    jcenter()
}

dependencies {
    compile 'com.yevdo:jwildcard:1.4'
}
```

Maven <code>pom.xml</code>
```xml
<dependency>
  <groupId>com.yevdo</groupId>
  <artifactId>jwildcard</artifactId>
  <version>1.5</version>
</dependency>
```
### API
API documentation https://alenon.github.io/JWildcard/

### Examples:

This wildcard:
```java
"mywil?card*"
```
will be converted to this regex string:
```java
"\Qmywil\E.\Qcard\E.*"
```
If you wish to convert wildcard to regex string use:
```java
JWildcard.wildcardToRegex("mywil?card*");
```
Default wildcard rules are: "?" -> ".", "\*" -> ".\*", but you can change the default behaviour if you wish to, by simply defining the new rules.
```java
JWildcard.wildcardToRegex(wildcard, rules, strict);
```
If you wish to check matching directly you can use this:
```java
JWildcard.matches("mywild*", "mywildcard");
```

If wish to convert wildcard string to SQL like pattern:
```java
JWildcard.wildcardToSqlPattern("?wild*Ca?rd*") // outputs this => _wild%Ca_rd%
```
