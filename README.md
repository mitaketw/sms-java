# sms-java

[![Build Status](https://travis-ci.org/mitaketw/sms-java.svg?branch=master)](https://travis-ci.org/mitaketw/sms-java)

Mitake SMS library for Java

**Removed versions 0.1.0 ~ 0.3.0, please update to 0.3.1+**

## Dependency

### Maven

```xml
<dependency>
  <groupId>mitake</groupId>
  <artifactId>lib-sms</artifactId>
  <version>0.3.2</version>
</dependency>
```

### Gradle

```groovy
compile 'mitake:lib-sms:0.3.2'
```

## How to use

### Initialize

```java
MitakeSms.init("username", "password");
```

### Send a sms

```java
MitakeSmsResult result = MitakeSms.send("0912345678", "this is a sample message");
```

## Official Document

TODO
