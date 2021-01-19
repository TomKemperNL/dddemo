package nl.tomkemper.dddemo.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import nl.tomkemper.dddemo.exceptions.BadRequestException;
import nl.tomkemper.dddemo.exceptions.NotFoundException;
import nl.tomkemper.dddemo.models.Customer;
import nl.tomkemper.dddemo.models.EmailAddress;
import nl.tomkemper.dddemo.repositories.CustomerRepository;
import nl.tomkemper.dddemo.services.LoginService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
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
