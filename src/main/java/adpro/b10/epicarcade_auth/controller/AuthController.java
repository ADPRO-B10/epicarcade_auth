package adpro.b10.epicarcade_auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class AuthController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "HelloWorld";
    }
}

