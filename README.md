


##  Epic 1: Authorisation server

> *As an entity (individual or organisation),  I want to delegate access to my own  ressources to a partner agency or service provider whom I
> trust, so that they can perform operation on my behalf.*

*Acceptance criteria:*

 1. I can direct my partners to centralised authorisation service
 2. I can protect my  (REST-API)  specific owners data ressources

> As an authorization system, I want to provide and autorisation code to
> registered  partner who identifies  (We are not refering to authenication there as it isi this will be covered in the Open Id Connect)

Acceptance criteria:

 1. Given a trusted partner provide a **client_id** and **clientsecret** matching the authorisation system database,  I can provide
    authorisation code etc...
 2. I can attach a scope listing  ressource data, type and data categories owners/customer agreed (explicity or implicitly to share)

OAuth 2 and OpenID Connect Specification
-  
The key references for this work are the the ieltf specification. The reading of it can be overwhelming. Plase the article from [Okta provides a high level view of the spec](https://www.oauth.com/oauth2-servers/map-oauth-2-0-specs/) so can map the section 

## OAuth 2 and OpenID Connect Specification

- Owner: The ressource Owner
- Client: The System wich will be issuing the authorisation requests
- Authorisation Server: The System wich will grant or reject authorisation requests and later provide token so that an authorized cliend can access the ressources
- Resource Server: The server (e.g. rest-api) that holds the ressources


##   Authorisation/Delegation flows VS application types

 - Authorisation code :  for apps running on a  [web server] https://aaronparecki.com/oauth-2-simplified/#web-server-apps),
   [browser-based]
 - Password: or logging in with a  [username and password] https://aaronparecki.com/oauth-2-simplified/#other-app-types)
   (only for first-partyapps)
 - client credential : for  [application access](https://aaronparecki.com/oauth-2-simplified/#client-credentials)    without a user present
 - implicit ( deprecated) :  was previously recommended for clients
   without a secret, but has been superseded by using the Authorization
   Code grant with PKCE.

## Feature backlogs
 - [x] Authorisation server
 - [ ] Ressource server
 - [ ] Owner is a web-application: ( "password") grant type
 - [ ] Owner is 

## Feature 1 : Authorization server

**Key principle:**

A client application (e.g. Micro-service)  instead of sending their client client_id and client_secret the resource it wants to access, request it through a third-party server.

-> The screen shot below show how the server installed the Authorositation server by mapping the key OAuth2 URLs
* /oauth/authorization
* /auth/token
* /oauth/refresh_token

![image](https://user-images.githubusercontent.com/17228294/92223475-d9f63680-eee3-11ea-84b0-2f8085208e8e.png)


### Testing 

 1. Get the authorization code provididing the parameter below

> http://localhost:8080/oauth/authorize?client_id=clientapp&response_type=code&scope=read_profile_info

- You will be presented with the screen below 

![image](https://user-images.githubusercontent.com/17228294/92304384-d84e7080-efc0-11ea-950d-27856ca8b54b.png)

 2. Click "Authorize" and then you  will be redirected to the login page with an authorization code

![image](https://user-images.githubusercontent.com/17228294/92304193-1185e100-efbf-11ea-845d-56e5ad38263b.png)


*:-) That's it that what the Authorization server is meant to do :-)* 




## Feature 2:  Resource server


### TESTING RESSOURCE SERVER

#### Access token with Autorization flow
 Obtain a access token from a resource server

    curl -X POST --user clientapp:123456 http://localhost:8080/oauth/token  -H "content-type: application/x-www-form-urlencoded" -d "code=A6A8Tz&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Flogin&scope=read_profile_info"

![image](https://user-images.githubusercontent.com/17228294/92304085-43e30e80-efbe-11ea-8eb4-bd4d467aaca2.png)

#### Access token with Password flow 
 Obtain an access token using password flow
 
```
 curl -X POST --user my-client:my-secret localhost:8080/oauth/token -d 'grant_type=password&client_id=my-client&scope=read&username=test@test.com&password=tester' -H "Accept: application/json"
```

```
http -a my-client:my-secret --form POST http://localhost:8080/oauth/token username='test@test.com' password='tester' grant_type='password' --ignore-stdin
```

#### Access token with scope

Scopes can be created at the same time that a token is created. It requires the below changes in spring to enable annotation based scope checking.


1. Specifying a expression handler to handle security at method level expression  


```java
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class MethodSecurityConfig
        extends GlobalMethodSecurityConfiguration {
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}

```
2. Loading this configuration so that it is applied to the whole application
Here I imported the configuraration jointly with CorsConfiguration
```java

@Configuration
@Import({SecurityConfig.class, MethodSecurityConfig.class})
public class CorsConfig {
/**
rest of the code omitted 
**/
}
```

```java

@Controller
public class HelloWorldController {


    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/api/hello")
    @ResponseBody
    public ResponseEntity<Welcome> getWelcome(@RequestParam String name) {
        Welcome welcomePerson = new Welcome(name);
        ResponseEntity<Welcome> responseEntity = ResponseEntity.ok(welcomePerson);
        return responseEntity;
    }
}

```

1. Creating an access token with write scope access token will result in access error for an endpoint
which nas read only

![image](docs/scope-failure.jpg)


2. Creating an access token with read will results in a success full ressource + access token call
as the token was created with 'read' and authorisation is for read mode

![image](docs/scope-success.jpg)

## TODO

 - [ ] Rewrite the Feature or epic statement
 - [ ] Plan features in several branches or repositories
 - [ ] Proof reading
 - [ ] Organise link and ressources
 - [ ] Explain  spring boot facilitate the  imlementation of the solution 
 

# REFERENCES

### Specification for OAuth
 - https://tools.ietf.org/id/draft-ietf-oauth-v2-31.html    *
 - https://howtodoinjava.com/spring-boot2/oauth2-auth-server/    *
 
 ### Authorization, Delegation and Scope handling examples
 
 - https://www.javainuse.com/spring/spring-boot-oauth-authorization-code
 - https://pattern-match.com/blog/springboot2-with-oauth2-integration/  
 
 
 ### Using scopes wjith spring boot
 - http://www.zakariaamine.com/2018-03-01/using-oauth2-in-spring-scopes
 - https://www.youtube.com/watch?v=996OiexHze0


