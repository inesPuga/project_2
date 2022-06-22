package com.example.web;

import com.example.database.BLL.UtilizadorBLL;
import com.example.database.DAL.Utilizador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String index(Model model) {
        return "login";
    }

    @PostMapping("/logger")
    public String login(Model model, Utilizador user) {
        Utilizador userTemp = UtilizadorBLL.verifyLoginWeb(user);
        IndexController ic = new IndexController();
        //System.out.println(user.getUsername());
        //System.out.println(user.getEmail());
        if(userTemp != null) {
            model.addAttribute("user", userTemp);
            ic.listReserves(model);
            return "index";
        }
        else {
            model.addAttribute("erro", "Dados inválidos");
            return "login";
        }
    }

}