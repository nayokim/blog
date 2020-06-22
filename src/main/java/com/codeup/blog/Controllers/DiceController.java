package com.codeup.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model model){
        int randomNumber = (int) (Math.random() * 6 + 1);
        boolean correct = randomNumber == n;

        model.addAttribute("numberGuess", n);
        model.addAttribute("randomNumber",randomNumber);
        model.addAttribute("correct", correct);
        return "roll-dice";
    }


}
