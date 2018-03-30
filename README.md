# JWildcard

[![Build Status](https://travis-ci.org/alenon/JWildcard.svg?branch=master)](https://travis-ci.org/alenon/JWildcard)

A collection of wildcard utilities. This library helps to convert wildcards to regex string. Wildcards can be declared using custom made rules.


### Getting Started

[ ![Download](https://api.bintray.com/packages/yevdo/jwildcard/jwildcard/images/download.svg) ](https://bintray.com/yevdo/jwildcard/jwildcard/_latestVersion)

Gradle <code>build.gradle</code>
```gradle
repositories {
    jcenter()
}

dependencies {
    compile 'com.yevdo:jwildcard:1.2'
}
```

Maven <code>pom.xml</code>
```xml
<dependency>
  <groupId>com.yevdo</groupId>
  <artifactId>jwildcard</artifactId>
  <version>1.2</version>
  <type>pom</type>
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
Default wildcard rule are "?" -> ".", "*" -> ".*", but you can change the default behaviour if you wish to, by simply defining the new rules.
```java
JWildcard.wildcardToRegex(wildcard, rules, strict);
```
If you wish to check matching directly you can use this:
```java
JWildcard.matches("mywild*", "mywildcard");
```
