# sms-java

[![Build Status](https://travis-ci.org/mitaketw/sms-java.svg?branch=master)](https://travis-ci.org/mitaketw/sms-java) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/tw.com.mitake/lib-sms/badge.svg)](https://maven-badges.herokuapp.com/maven-central/tw.com.mitake/lib-sms)

Mitake SMS library for Java

**PLEASE UPDATE TO 0.4.0+**

## Dependency

### Maven

```xml
<dependency>
  <groupId>tw.com.mitake</groupId>
  <artifactId>lib-sms</artifactId>
  <version>0.5.0</version>
</dependency>
```

### Gradle

```groovy
compile 'tw.com.mitake:lib-sms:0.5.0'
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

### Query account point

```java
MitakeSmsQueryAccountPointResult result = MitakeSms.queryAccountPoint();
```

### Query message status

```java
MitakeSmsQueryMessageStatusResult result = MitakeSms.queryMessageStatus("messageid");
```

## Official Document

TODO
