package ru.samara.giftshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.exceptions.ProductAlreadyExistException;
import ru.samara.giftshop.repository.GoodsRepository;

import java.util.List;

@Service
public class GoodsService {

    private GoodsRepository repo;

    @Autowired
    public GoodsService(GoodsRepository repo) {
        this.repo = repo;
    }

    public void saveProduct(ProductEntity product) throws ProductAlreadyExistException {
        if(!repo.existsByName(product.getName()))
            repo.save(product);
        else
            throw new ProductAlreadyExistException();
    }

    public List<ProductEntity> selectAllGoods() {
        return (List<ProductEntity>) repo.findAll();
    }
}
