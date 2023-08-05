package com.pj.project.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginDTO {
    @JsonProperty("Key")
    private String key;
    @JsonProperty("Password")
    private String password;


}
