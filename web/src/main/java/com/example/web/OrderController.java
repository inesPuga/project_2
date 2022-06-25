package com.example.web;

import com.example.database.BLL.*;
import com.example.database.DAL.*;
import com.example.web.Model.CarItemsService;
import com.example.web.Model.CartItems;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    @GetMapping("/order")
    public String product(Model model, @RequestParam("idu") Optional<Integer> iduser, @RequestParam("ido") Optional<Integer> idorder, @RequestParam("idp") Optional<Integer> idproduct) {
        Encomenda order;
        Utilizador user = new Utilizador();
        Tipoconserva product = new Tipoconserva();
        List<Tipoconserva> tiposConserva = TipoconservaBLL.readAll();
        if(iduser.isPresent()) {
            int id_user = (int) iduser.orElse(0);
            user = UtilizadorBLL.readById(id_user);
            model.addAttribute("user", user);
        }
        if(idorder.isPresent()) {
            int id_order = (int) idorder.orElse(0);
            order = EncomendaBLL.readById(id_order);
            model.addAttribute("order", order);
        }
        else {
            order = new Encomenda();
            order.setData(date());
            order.setCodcliente(searchClient(user).getCodcliente());
            EncomendaBLL.create(order);
            model.addAttribute("order", order);
            model.addAttribute("list_orderprod", infoOrder(order));
        }
        if(idproduct.isPresent()) {
            int id_product = (int) idproduct.orElse(0);
            product = TipoconservaBLL.readByCodTipoConserva(id_product);
            model.addAttribute("product", product);
        }
        else {
            model.addAttribute("lista", tiposConserva);
            return "order";
        }
        model.addAttribute("lista", tiposConserva);
        if(order != null && product != null) {
            model.addAttribute("list_orderprod", infoOrder(order));
        }
        return "order";
    }

    public boolean verifypk(Tipoconservaencomenda po) {
        Tipoconservaencomenda test = TipoconservaencomendaBLL.readUserBypk(po.getCodtipoconserva(), po.getCodencomenda());
        for(Tipoconservaencomenda i : TipoconservaencomendaBLL.readAll()) {
            if(test.getCodtipoconserva() == i.getCodtipoconserva() && test.getCodencomenda() == i.getCodencomenda()) {
                return true;
            }
        }
        return true;
    }

    public List<Tipoconserva> infoOrder(Encomenda order) {
        List<Tipoconserva> product_list = new ArrayList<>();
        for(Tipoconservaencomenda i : TipoconservaencomendaBLL.readAll()) {
            for(Tipoconserva j : TipoconservaBLL.readAll()) {
                if(order.getCodencomenda() == i.getCodencomenda() && j.getCodtipoconserva() == i.getCodtipoconserva()) {
                    product_list.add(j);
                }
            }

        }
        return product_list;
    }

    public Tipoconservaencomenda addproductorder(Encomenda order, Tipoconserva product) {
        Tipoconservaencomenda orderproduct = new Tipoconservaencomenda();
        orderproduct.setQtd(1);
        orderproduct.setCodencomenda(order.getCodencomenda());
        orderproduct.setCodtipoconserva(product.getCodtipoconserva());
        TipoconservaencomendaBLL.create(orderproduct);
        return orderproduct;
    }

    public Cliente searchClient(Utilizador user) {
        for(Cliente i : ClienteBLL.readAll()) {
            if(i.getIduser() == user.getIduser()) {
                return i;
            }
        }
        return null;
    }

    public String date() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(date);
        return strDate;
    }

}
