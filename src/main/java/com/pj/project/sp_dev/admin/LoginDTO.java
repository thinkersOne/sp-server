package com.pj.project.sp_dev.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginDTO {
    @JsonProperty("Key")
    private String key;
    @JsonProperty("Password")
    private String password;


}
