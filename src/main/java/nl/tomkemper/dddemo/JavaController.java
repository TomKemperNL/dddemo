package nl.tomkemper.dddemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaController {

    @GetMapping("/java")
    public String hello(){
        return "hello java";
    }
}
