package com.example.blog.Auth;

import com.example.blog.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistererRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;

}
