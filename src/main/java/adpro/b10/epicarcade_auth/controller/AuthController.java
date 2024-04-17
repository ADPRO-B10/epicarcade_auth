package adpro.b10.epicarcade_auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("")
    public String authPage(Model model){
        return "HelloWorld";
    }

    
}

