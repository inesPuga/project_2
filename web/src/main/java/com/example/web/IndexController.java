package com.example.web;


import com.example.database.BLL.TipoconservaBLL;
import com.example.database.BLL.UtilizadorBLL;
import com.example.database.DAL.Tipoconserva;
import com.example.database.DAL.Utilizador;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String listReserves(Model model, @RequestParam("id") Optional<Integer> id) {
        List<Tipoconserva> tiposConserva = TipoconservaBLL.readAll();
        if(id.isPresent()) {
            int iduser = (int) id.orElse(0);
            Utilizador user = UtilizadorBLL.readById(iduser);
            model.addAttribute("user", user);
        }
        for(Utilizador i : UtilizadorBLL.readAll()) {
            if(i.getCargo().equals("C")) {
                model.addAttribute("user_client", i);
                //System.out.println(model.toString());
            }
        }
        model.addAttribute("lista", tiposConserva);
        //System.out.println(model.toString());
        return "index";
    }

    @GetMapping("{id}/imagem")
    public void image(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        Tipoconserva asset = TipoconservaBLL.readByCodTipoConserva(id);
        assert asset != null;
        InputStream inputStream = new ByteArrayInputStream(asset.getImagem());
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping("/furniture")
    public String product(Model model, @RequestParam("id") Optional<Integer> id) {
        if(id.isPresent()) {
            int iduser = (int) id.orElse(0);
            Utilizador user = UtilizadorBLL.readById(iduser);
            model.addAttribute("user", user);
        }
        List<Tipoconserva> tiposConserva = TipoconservaBLL.readAll();
        model.addAttribute("lista", tiposConserva);
        return "furniture";
    }

    /*
    @GetMapping("/furniture")
    public String product(Model model) {
        return "furniture";
    }
    */

}