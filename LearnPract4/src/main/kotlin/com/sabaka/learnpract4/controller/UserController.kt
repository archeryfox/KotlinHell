package com.sabaka.learnpract4.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


//@RestController
@Controller
@RequestMapping("/users")
class UserController {

    @GetMapping("/test")
    fun test(model: Model): String {
        model.addAttribute("user", "dddd")
        return "Home"
    }
}

fun m(){

}

