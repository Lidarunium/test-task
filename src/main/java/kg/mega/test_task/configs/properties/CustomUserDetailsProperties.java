package kg.mega.test_task.configs.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "custom-user-details")
public class CustomUserDetailsProperties {

    @NotBlank(message = "CustomUserDetailsProperties::username is cannot be blank")
    private String username;

    @NotBlank(message = "CustomUserDetailsProperties::password is cannot be blank")
    private String password;

    @NotBlank(message = "CustomUserDetailsProperties::email is cannot be blank")
    private String email;
}
