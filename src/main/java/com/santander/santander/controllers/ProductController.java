package com.santander.santander.controllers;

import com.santander.santander.DTO.ErrorDto;
import com.santander.santander.exceptions.SantanderException;
import com.santander.santander.models.Product;
import com.santander.santander.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.santander.santander.constans.CoreRouter.ProductRoutes.*;

@RestController
@RequestMapping(ROOT)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(GET_BY_ID)
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (SantanderException ex) {
            ErrorDto errorResponse = ErrorDto.builder().message(ex.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(CREATE)
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
        } catch (SantanderException ex) {
            ErrorDto errorResponse = ErrorDto.builder().message(ex.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
    }

    @PutMapping(UPDATE)
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
        } catch (SantanderException ex) {
            ErrorDto errorResponse = ErrorDto.builder().message(ex.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SantanderException ex) {
            ErrorDto errorResponse = ErrorDto.builder().message(ex.getMessage()).build();
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}