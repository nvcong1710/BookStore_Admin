package vn.edu.uit.IE104.dto;

<<<<<<< HEAD
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@NoArgsConstructor
public class JwtResponse {
    private Long id;
    private String email;
    private String token;
    private String type = "Bearer";
    private List<String> roles;

    public JwtResponse(Long id, String email, String token, List<String> roles) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.roles = roles;
    }
=======

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data


@NoArgsConstructor
public class JwtResponse {
   private Long id;
   private String email;
   private String token;
   private String type = "Bearer";
   private List<String> roles;


   public JwtResponse(Long id, String email, String token, List<String> roles) {
       this.id = id;
       this.email = email;
       this.token = token;
       this.roles = roles;
   }
>>>>>>> 3deea965b911c1a3ba201979e2609da881c49f57
}
