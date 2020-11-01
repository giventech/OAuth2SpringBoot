package com.giventech.giventechoauth.model;

public enum OauthScopes {
    GRANT_TYPE_PASSWORD ("password"),
    AUTHORIZATION_CODE ("authorization_code"),
    REFRESH_TOKEN ("refresh_token"),
    SCOPE_READ("read"),
    SCOPE_WRITE ("write"),
    READ_MY_PROFILE_INFO ("read_profile_info");

    private String scope;

    OauthScopes(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
