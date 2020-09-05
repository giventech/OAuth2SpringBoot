# A Cloud Native Oauth2 Authorisation Grant  and Access Delegation model


## EPIC and Benefit hypothesis

As an organisation or individual  I want to delegate access to my own resssource ressource to a partner agency or service provider so that they can perform operation on my behalf.

Acceptance criteria:

- I direct my partners to an authorization giving a temporary grant to my customers own data that are managed on my server (e.g. REST API, or Micro-services)
- Given a trusted partner (has a registered  client_id and client secret) regisered for the ressource before the  authorisation server, the trusted partner can be provided an authorisation code
- A fine grained scope ressource server  data, type and data categories can be attached to 
- given I an authorisation grant or code, I can provide 



### Feature1 : Authorization server
Key principle: and client application (e.g. Micro-service instead of sending their client client_id and client_secret the resource it wants to access, request it through 
a third-party server.

Feature2 : Authentication server
Feature3 : Ressource server


![image](https://user-images.githubusercontent.com/17228294/92223475-d9f63680-eee3-11ea-84b0-2f8085208e8e.png)
