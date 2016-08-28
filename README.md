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
  <version>0.7.0</version>
</dependency>
```

### Gradle

```groovy
compile 'tw.com.mitake:lib-sms:0.7.0'
```

## How to use

### **AT FIRST**

```java
MitakeSms.init("username", "password");
```

### Send a sms

```java
MitakeSmsSendResult result = MitakeSms.send("0912345678", "this is a sample message");
```

### Send multi-body sms

```java
List<SendOptions> optsList = new ArrayList<SendOptions>();

SendOptions opts = new SendOptions().addDestination("0912345678").setMessage("this is a sample message");

optsList.add(opts);

opts = new SendOptions().addDestination("0987654321").setMessage("Hello World");

optsList.add(opts);

MitakeSmsSendResult result = MitakeSms.send(optsList);
```

### Query account point

```java
MitakeSmsQueryAccountPointResult result = MitakeSms.queryAccountPoint();
```

### Query message status (support multiple messages)

```java
MitakeSmsQueryMessageStatusResult result = MitakeSms.queryMessageStatus("messageid1", "messageid2");

// or

MitakeSmsQueryMessageStatusResult result = MitakeSms.queryMessageStatus(List<String> messageIds);
```
