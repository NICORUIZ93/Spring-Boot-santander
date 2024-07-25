package com.santander.santander.services.impl;

import com.santander.santander.constans.I18NComponent;
import com.santander.santander.exceptions.ErrorMessages;
import com.santander.santander.exceptions.SantanderException;
import com.santander.santander.models.Product;
import com.santander.santander.repositories.ProductRepository;
import com.santander.santander.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final I18NComponent i18NComponent;

    public ProductServiceImpl(ProductRepository productRepository, I18NComponent i18NComponent) {
        this.productRepository = productRepository;
        this.i18NComponent = i18NComponent;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new SantanderException(i18NComponent.getMessage(ErrorMessages.PRODUCT_NOT_FOUND, id)));
    }

    @Override
    public Product createProduct(Product product) {
        if (productRepository.findByNombre(product.getNombre()).isPresent()) {
            throw new SantanderException(i18NComponent.getMessage(ErrorMessages.PRODUCT_ALREADY_EXISTS, product.getNombre()));
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new SantanderException(i18NComponent.getMessage(ErrorMessages.PRODUCT_NOT_FOUND, id));
        }
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new SantanderException(i18NComponent.getMessage(ErrorMessages.PRODUCT_NOT_FOUND, id));
        }
        productRepository.deleteById(id);
    }
}