package com.example.web.Model;

import com.example.database.BLL.EncomendaBLL;
import com.example.database.BLL.TipoconservaBLL;
import com.example.database.BLL.TipoconservaencomendaBLL;
import com.example.database.DAL.Encomenda;
import com.example.database.DAL.Tipoconserva;
import com.example.database.DAL.Tipoconservaencomenda;
import com.example.database.DAL.Utilizador;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarItemsService {

    public void addProduct(Tipoconserva product) {
        
    }

    public void removeProduct(Tipoconserva product) {
        if (TipoconservaBLL.readByCodTipoConserva(product.getCodtipoconserva())!=null) {
            TipoconservaBLL.delete(product);
        }
    }

}
