package nl.tomkemper.dddemo.controllers;

import nl.tomkemper.dddemo.exceptions.UnauthorizedException;
import nl.tomkemper.dddemo.services.EmailVerificationService;
import nl.tomkemper.dddemo.services.LoginService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
