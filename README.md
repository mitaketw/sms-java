# sms-java

[![Build Status](https://travis-ci.org/mitaketw/sms-java.svg?branch=master)](https://travis-ci.org/mitaketw/sms-java)

Mitake SMS library for Java

**PLEASE UPDATE TO 0.4.0+**

## Dependency

### Maven

```xml
<dependency>
  <groupId>tw.com.mitake</groupId>
  <artifactId>lib-sms</artifactId>
  <version>0.4.2</version>
</dependency>
```

### Gradle

```groovy
compile 'tw.com.mitake:lib-sms:0.4.2'
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
