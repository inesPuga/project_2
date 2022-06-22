package com.example.web;


import com.example.database.BLL.ClienteBLL;
import com.example.database.BLL.TipoconservaBLL;
import com.example.database.BLL.UtilizadorBLL;
import com.example.database.DAL.Cliente;
import com.example.database.DAL.Tipoconserva;
import com.example.database.DAL.Utilizador;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String listReserves(Model model) {
        List<Tipoconserva> tiposConserva = TipoconservaBLL.readAll();
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
    public String product(Model model, @RequestParam Integer id) {
        Utilizador user = UtilizadorBLL.readById(id);
        if(user!=null) {
            model.addAttribute("user", user);
        }
        return "furniture";
    }

    /*
    @GetMapping("/furniture")
    public String product(Model model) {
        return "furniture";
    }
    */

}