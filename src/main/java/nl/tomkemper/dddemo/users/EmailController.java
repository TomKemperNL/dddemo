package nl.tomkemper.dddemo.users;

import nl.tomkemper.dddemo.UnauthorizedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("email")
public class EmailController {

    private final LoginService login;
    private final EmailVerificationService verificationService;

    public EmailController(EmailVerificationService verificationService, LoginService login){
        this.login = login;
        this.verificationService = verificationService;
    }

    @PostMapping("resend")
    public void resendVerificationMail(){
        if(login.getLoggedInCustomer() == null){
            throw new UnauthorizedException();
        }else{
            this.verificationService.sendVerificationEmail(login.getLoggedInCustomer());
        }
    }

    @PostMapping("{code}")
    public void acceptVerification(@PathVariable UUID code){
        this.verificationService.acceptVerificationCode(code);
    }
}
