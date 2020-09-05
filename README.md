# A Cloud Native Oauth2 Authorisation Grant  and Access Delegation model


## EPIC and Benefit hypothesis

As an organisation or individual  I want to delegate access to my own resssource ressource to a partner agency or service provider so that they can perform operation on my behalf.

Acceptance criteria:

- I can direct my partners to centralised authorisation service capable of granting access to ressource server (REST-API) managing access to specific owners data ressources


As an athorisation system, I want to provide autorisation code to identified partner (Identity is not part of OAUTH2 But Open Id connect extention)

Acceptance criteria

- Given a trusted partner provide a client_id and client secret matching the authorisation system databased I can provide authorisation code etc...
- I can attach a scope listing  ressource data, type and data categories owners/customer agreed (explicity or implicitly to share)




### Feature1 : Authorization server
Key principle: and client application (e.g. Micro-service instead of sending their client client_id and client_secret the resource it wants to access, request it through 
a third-party server.

Feature2 : Authentication server
Feature3 : Ressource server


![image](https://user-images.githubusercontent.com/17228294/92223475-d9f63680-eee3-11ea-84b0-2f8085208e8e.png)
