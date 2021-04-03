# JWildcard

A collection of wildcard utilities. 
This library helps to convert wildcards into regex string. 
Wildcards can be declared using custom made rules.

### Examples:

This wildcard:
```java
"mywil?card*"
```
will be converted to this regex string:
```java
"\Qmywil\E.\Qcard\E.*"
```
In order to convert wildcard into regex string use the following example:
```java
JWildcard.wildcardToRegex("mywil?card*");
```
Default wildcard rules are: "?" -> ".", "\*" -> ".\*", but you can change the default behaviour, by simply defining the new rules:
```java
JWildcard.wildcardToRegex(wildcard, rules, strict);
```
In order to check matching directly you can use the following example:
```java
JWildcard.matches("mywild*", "mywildcard");
```

In order to convert wildcard string into SQL like pattern:
```java
JWildcard.wildcardToSqlPattern("?wild*Ca?rd*") // outputs this => _wild%Ca_rd%
```

### Getting Started

![Master Build](https://github.com/alenon/JWildcard/workflows/Java%20CI%20with%20Maven/badge.svg)

[Direct Download](https://alenon.jfrog.io/artifactory/maven-releases/com/yevdo/jwildcard/1.5/jwildcard-1.5.jar)
<br />

Library packages stored in both: Github and Artifactory (https://alenon.jfrog.io/) repositories

#### Using Gradle 

<code>build.gradle</code>
```gradle
repositories {
    jcenter()
}

dependencies {
    compile 'com.yevdo:jwildcard:1.4'
}
```

#### Using Maven 
<code>pom.xml</code>
```xml
<distributionManagement>
    <repository>
        <id>central</id>
        <name>a0yytejrantuj-artifactory-primary-0-releases</name>
        <url>https://alenon.jfrog.io/artifactory/maven-releases</url>
    </repository>
</distributionManagement>

<dependency>
  <groupId>com.yevdo</groupId>
  <artifactId>jwildcard</artifactId>
  <version>1.5</version>
</dependency>
```
### API
API documentation https://alenon.github.io/JWildcard/
