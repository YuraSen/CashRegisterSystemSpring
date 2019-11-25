package com.spring.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Builder

public class UserType implements GrantedAuthority {
    private Long id;

    private String type;

    private String description;

    private Collection<User> userCollection;

    @Override
    public String getAuthority() {
        return type;
    }
}


