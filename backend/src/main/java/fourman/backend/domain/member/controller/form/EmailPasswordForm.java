package fourman.backend.domain.member.controller.form;

import fourman.backend.domain.member.service.request.EmailPasswordRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmailPasswordForm {

    private String email;
    private String password;

    public EmailPasswordRequest toEmailPasswordRequest() {
        return new EmailPasswordRequest(email, password);
    }
}