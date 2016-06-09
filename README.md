# sms-java

三竹資訊 Mitake 簡訊串接 Library for Java

## How to use

### Initialize

```java
MitakeSms.init("username", "password");
```

### Send a sms

```java
MitakeSmsResult result = MitakeSms.send("0912345678", "this is a sample message");
```
