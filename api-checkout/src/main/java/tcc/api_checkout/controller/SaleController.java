package tcc.api_checkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tcc.api_checkout.model.Sale;
import tcc.api_checkout.service.SaleServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/checkout")
public class SaleController {
    @Autowired
    private SaleServiceImpl saleService;

    @PostMapping
    public Sale create(@RequestBody Sale sale) {
        return this.saleService.create(sale);
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable String id) {
        return this.saleService.findById(id);
    }

    @GetMapping
    public List<Sale> list() {
        return this.saleService.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return this.saleService.deleteById(id);
    }
}