package com.example.web;

import com.example.database.BLL.*;
import com.example.database.DAL.*;
import com.example.web.Model.CarItemsService;
import com.example.web.Model.CartItems;
import org.checkerframework.checker.units.qual.C;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
            Estadoencomenda state = new Estadoencomenda();
            state.setCodencomenda(order.getCodencomenda());
            state.setIde(EstadoeBLL.readById(1).getIde());
            state.setDtee(date());
            EstadoencomendaBLL.create(state);
            refreshprice(order);
            model.addAttribute("order", order);
            model.addAttribute("list_orderprod", infoOrder(order));
            model.addAttribute("list_orderprodx", infoOrderX(order));
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
            addproductorder(order, product);
            refreshprice(order);
            model.addAttribute("list_orderprod", infoOrder(order));
            model.addAttribute("list_orderprodx", infoOrderX(order));
        }
        return "order";
    }

    @GetMapping("/remove")
    //@ResponseStatus(value = HttpStatus.OK)
    public String removeproduct(Model model, @RequestParam("idu") Optional<Integer> iduser, @RequestParam("ido") Optional<Integer> idorder, @RequestParam("idp") Optional<Integer> idproduct) {
        Encomenda order = new Encomenda();
        Tipoconserva product = new Tipoconserva();
        Utilizador user = new Utilizador();
        List<Tipoconserva> tiposConserva = TipoconservaBLL.readAll();
        if(iduser.isPresent()) {
            int id_user = (int) iduser.orElse(0);
            user = UtilizadorBLL.readById(id_user);
        }
        if(idorder.isPresent()) {
            int id_order = (int) idorder.orElse(0);
            order = EncomendaBLL.readById(id_order);
        }
        if(idproduct.isPresent()) {
            int id_product = (int) idproduct.orElse(0);
            product = TipoconservaBLL.readByCodTipoConserva(id_product);
        }
        Tipoconservaencomenda tipoconservaencomenda = read(order.getCodencomenda(), product.getCodtipoconserva());
        if(tipoconservaencomenda != null) {
            int qtd = tipoconservaencomenda.getQtd();
            float result = qtd - 0.5F;
            /*
            if(verifyQuantity((int) result, product)==1) {
                model.addAttribute("erro", "Stock insuficiente");
            }
            if(verifyQuantity((int) result, product)==0) {
                tipoconservaencomenda.setQtd((int) result);
            }
            */
            tipoconservaencomenda.setQtd((int) result);
            refreshprice(order);
            TipoconservaencomendaBLL.update(tipoconservaencomenda);
            if(tipoconservaencomenda.getQtd()==0) {
                TipoconservaencomendaBLL.delete(tipoconservaencomenda);
            }
            model.addAttribute("product", product);
            model.addAttribute("order", order);
            model.addAttribute("user", user);
            model.addAttribute("lista", tiposConserva);
            model.addAttribute("list_orderprod", infoOrder(order));
            model.addAttribute("list_orderprodx", infoOrderX(order));
            return "remove";
        }
        else {
            model.addAttribute("product", product);
            model.addAttribute("order", order);
            model.addAttribute("user", user);
            model.addAttribute("lista", tiposConserva);
            model.addAttribute("list_orderprod", infoOrder(order));
            model.addAttribute("list_orderprodx", infoOrderX(order));
            return "remove";
        }
    }

    @GetMapping("/add")
    //@ResponseStatus(value = HttpStatus.OK)
    public String addproduct(Model model, @RequestParam("idu") Optional<Integer> iduser, @RequestParam("ido") Optional<Integer> idorder, @RequestParam("idp") Optional<Integer> idproduct) {
        Encomenda order = new Encomenda();
        Tipoconserva product = new Tipoconserva();
        Utilizador user = new Utilizador();
        List<Tipoconserva> tiposConserva = TipoconservaBLL.readAll();
        if(iduser.isPresent()) {
            int id_user = (int) iduser.orElse(0);
            user = UtilizadorBLL.readById(id_user);
        }
        if(idorder.isPresent()) {
            int id_order = (int) idorder.orElse(0);
            order = EncomendaBLL.readById(id_order);
        }
        if(idproduct.isPresent()) {
            int id_product = (int) idproduct.orElse(0);
            product = TipoconservaBLL.readByCodTipoConserva(id_product);
        }
        Tipoconservaencomenda tipoconservaencomenda = read(order.getCodencomenda(), product.getCodtipoconserva());
        if(tipoconservaencomenda != null) {
            int qtd = tipoconservaencomenda.getQtd();
            int result = qtd + 1;
            /*
            if(verifyQuantity(result, product)==1) {
                model.addAttribute("erro", "Stock insuficiente");
            }
            if(verifyQuantity(result, product)==0) {
                tipoconservaencomenda.setQtd(result);
            }
            */
            tipoconservaencomenda.setQtd(result);
            refreshprice(order);
            TipoconservaencomendaBLL.update(tipoconservaencomenda);
            model.addAttribute("product", product);
            model.addAttribute("order", order);
            model.addAttribute("user", user);
            model.addAttribute("lista", tiposConserva);
            model.addAttribute("list_orderprod", infoOrder(order));
            model.addAttribute("list_orderprodx", infoOrderX(order));
            return "add";
        }
        else {
            model.addAttribute("product", product);
            model.addAttribute("order", order);
            model.addAttribute("user", user);
            model.addAttribute("lista", tiposConserva);
            model.addAttribute("list_orderprod", infoOrder(order));
            model.addAttribute("list_orderprodx", infoOrderX(order));
            return "add";
        }
    }

    public int verifyQuantity(int qtd, Tipoconserva p) {
        if(qtd > p.getQtdstock()) {
            return 1;
        }
        else {
            int qtd_result = p.getQtdstock() - qtd;
            p.setQtdstock(qtd_result);
            TipoconservaBLL.update(p);
        }
        return 0;
    }

    public void refreshprice(Encomenda order) {
        double result_price = 0;
        double price = 0;
        for(Tipoconservaencomenda i : TipoconservaencomendaBLL.readAll()) {
            for(Tipoconserva j : TipoconservaBLL.readAll()) {
                if(order.getCodencomenda() == i.getCodencomenda() && j.getCodtipoconserva() == i.getCodtipoconserva()) {
                    price = j.getPrecoactvenda()*i.getQtd();
                    result_price = result_price + price;
                }
            }
        }
        order.setPrecototal(result_price);
        EncomendaBLL.update(order);
    }

    public Tipoconservaencomenda read(int ido , int idp) {
        for(Tipoconservaencomenda i : TipoconservaencomendaBLL.readAll()) {
            if(i.getCodencomenda() == ido && i.getCodtipoconserva() == idp) {
                return i;
            }
        }
        return null;
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

    public List<Tipoconservaencomenda> infoOrderX(Encomenda order) {
        List<Tipoconservaencomenda> product_list = new ArrayList<>();
        for(Tipoconservaencomenda i : TipoconservaencomendaBLL.readAll()) {
            for(Tipoconserva j : TipoconservaBLL.readAll()) {
                if(order.getCodencomenda() == i.getCodencomenda() && j.getCodtipoconserva() == i.getCodtipoconserva()) {
                    product_list.add(i);
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
