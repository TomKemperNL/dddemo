package nl.tomkemper.dddemo.controllers;

import nl.tomkemper.dddemo.exceptions.UnauthorizedException;
import nl.tomkemper.dddemo.services.EmailVerificationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
@RequestMapping("email")
public class EmailController {

    private final HttpSession session;
    private final EmailVerificationService verificationService;

    public EmailController(EmailVerificationService verificationService, HttpSession session){
        this.session = session;
        this.verificationService = verificationService;
    }

    @PostMapping("resend")
    public void resendVerificationMail(){
        if(LoginController.getLoggedInCustomer(this.session) == null){
            throw new UnauthorizedException();
        }else{
            this.verificationService.sendVerificationEmail(LoginController.getLoggedInCustomer(this.session));
        }
    }

    @PostMapping("{code}")
    public void acceptVerification(@PathVariable UUID code){
        this.verificationService.acceptVerificationCode(code);
    }
}
