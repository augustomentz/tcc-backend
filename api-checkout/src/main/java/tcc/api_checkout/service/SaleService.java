package tcc.api_checkout.service;

import tcc.api_checkout.model.Sale;

import java.util.List;

public interface SaleService {
    Sale create(Sale product);

    List<Sale> findAll();

    String deleteById(String id);

    Sale findById(String id);
}

