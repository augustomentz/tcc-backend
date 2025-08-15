package tcc.api_checkout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.api_checkout.model.Sale;
import tcc.api_checkout.repository.SaleRepository;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale create(Sale sale) {
        return this.saleRepository.insert(sale);
    }

    @Override
    public Sale findById(String id) {
        return this.saleRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    @Override
    public List<Sale> findAll() {
        return this.saleRepository.findAll();
    }

    @Override
    public String deleteById(String id) {
        this.saleRepository.deleteById(id);

        return id;
    }
}
