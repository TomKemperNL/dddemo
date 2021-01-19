package nl.tomkemper.dddemo.users;

import com.fasterxml.jackson.databind.node.ObjectNode;
import nl.tomkemper.dddemo.BadRequestException;
import nl.tomkemper.dddemo.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("")
    public Customer getCurrentCustomer(){
        Customer current = this.loginService.getLoggedInCustomer();
        if(current == null){
            throw new NotFoundException();
        }

        return current;
    }

    @PostMapping("")
    public void login(@RequestBody ObjectNode credentials){
        String rawEmail = credentials.get("emailAddress").asText();

        Optional<EmailAddress> email = EmailAddress.parse(rawEmail);
        if(email.isEmpty()){
            throw new BadRequestException();
        }

        Customer existing = this.loginService.login(email.get(), credentials.get("password").asText());
        if(existing == null){
            throw new NotFoundException();
        }
    }

}
