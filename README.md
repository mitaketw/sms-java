# sms-java

Mitake SMS library for Java

## Dependency

### Maven

```xml
<dependency>
  <groupId>mitake</groupId>
  <artifactId>lib-sms</artifactId>
  <version>0.1.0</version>
</dependency>
```

### Gradle

```groovy
compile 'mitake:lib-sms:0.1.0'
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
