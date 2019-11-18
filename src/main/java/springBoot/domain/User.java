package springBoot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import springBoot.domain.enums.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Long id;

    @Pattern(regexp = "(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}")
    @NotEmpty
    private String username;

    @Pattern(regexp = "(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}")
    @NotEmpty
    private String password;

    @NotNull
    private Set<Role> roles;

    @NotNull
    private boolean active;

    public User(User user, String password) {
        this.id = user.id;
        this.username = user.username;
        this.password = password;
        this.roles = user.roles;
        this.active = user.active;
    }
}
