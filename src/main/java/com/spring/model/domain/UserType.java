package com.spring.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Builder

public class UserType {
    private Long id;

    private String type;

    private String description;

    private Collection<User> userCollection;
}


