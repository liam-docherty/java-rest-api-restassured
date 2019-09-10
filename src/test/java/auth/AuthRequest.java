package auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthRequest {

    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
