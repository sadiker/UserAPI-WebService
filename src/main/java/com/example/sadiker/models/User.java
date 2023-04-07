package com.example.sadiker.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@ApiModel()

@Entity
@Table(name="users")
public class User implements UserDetails, IResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Her kullanıcın unique ID değeridir.")
    private Long id;

    @Email(message = "Geçerli bir email adresi olmalıdır...")
    @ApiModelProperty(notes = "Kullanıcının email adresidir.")
    private String email;

    @NotEmpty(message = "Rol alanı boş olmamalıdır...")
    @ApiModelProperty(notes = "Kullanıcının rolüdür.")
    private String role;

    @ApiModelProperty(notes = "Kullanıcının şifresidir.")
    private String password;

    @NotEmpty(message = "İsim alanı boş olmamalıdır...")
    @ApiModelProperty(notes =  "Kullanıcının ismidir.")
    private String name;

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
   

    
}
