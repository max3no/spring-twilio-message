# Spring boot - Twilio(messaging)

## Getting Started

Need jdk, spring boot, working machine.
A Twilio account

### Prerequisites

Install Spring Boot.

### Steps

Create a new Spring Starter Project (Dependency - Web, mysql, jpa, twilio)

```
https://start.spring.io/
```


Create a mysql database with name sms and change your username and password in application.properties

Create a free Twilio account

```
https://www.twilio.com
```

create a new sms project in twilio

purchase a number from your free credits and insert it in TwilioServiceImpl

register your number you want to send messages to in twilio

```
you can only send messages to number registered in your account
```

get your account ssid and auth token from your dashboard in twilio and paste it in TwilioServiceImpl

Post API to get OTP
```
http://localhost:8080/api/v1/send

{
	"email" : "vaibhav@singh.com",
	"phoneNumber" : "+91**********"
}
```

Get API to verify OTP

```
http://localhost:8080/api/v1/verify?otp=87526&&email=vaibhav@singh.com
```



## Acknowledgments

* Open Source community
