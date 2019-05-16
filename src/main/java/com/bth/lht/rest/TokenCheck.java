package com.bth.lht.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("checktoken")
public class TokenCheck {

    private String AESKey = "8aiyhlngQjPNO7WGNsniVvwoG93aC4BURssGwnS7yEH";
    @GetMapping("getMessage")
    public String checkToken(){




        return  null;
    }
}
