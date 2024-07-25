package com.santander.santander.controllers;

import com.santander.santander.DTO.ErrorDto;
import com.santander.santander.exceptions.SantanderException;
import com.santander.santander.models.Product;
import com.santander.santander.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    private ProductService productService;
    private ProductController productController;

    @BeforeEach
    void setUp() {
        productService = Mockito.mock(ProductService.class);
        productController = new ProductController(productService);
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Producto 1", 100.0),
                new Product(2L, "Producto 2", 200.0)
        );
        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void testGetProductById_ProductExists() {
        Product product = new Product(1L, "Producto 1", 100.0);
        when(productService.getProductById(1L)).thenReturn(product);

        ResponseEntity<?> response = productController.getProductById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void testGetProductById_ProductNotFound() {
        when(productService.getProductById(1L)).thenThrow(new SantanderException("Producto no encontrado con ID: 1"));

        ResponseEntity<?> response = productController.getProductById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorDto);
        assertEquals("Producto no encontrado con ID: 1", ((ErrorDto) response.getBody()).getMessage());
    }

    @Test
    void testCreateProduct() {
        Product product = new Product(null, "Producto 2", 200.0);
        when(productService.createProduct(product)).thenReturn(new Product(1L, "Producto 2", 200.0));

        ResponseEntity<?> response = productController.createProduct(product);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Producto 2", ((Product) response.getBody()).getNombre());
    }

    @Test
    void testCreateProduct_ProductAlreadyExists() {
        Product product = new Product(null, "Producto 1", 100.0);
        when(productService.createProduct(product)).thenThrow(new SantanderException("El producto ya existe con el nombre: Producto 1"));

        ResponseEntity<?> response = productController.createProduct(product);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorDto);
        assertEquals("El producto ya existe con el nombre: Producto 1", ((ErrorDto) response.getBody()).getMessage());
    }

    @Test
    void testUpdateProduct_ProductExists() {
        Product product = new Product(1L, "Producto 1", 100.0);
        when(productService.updateProduct(1L, product)).thenReturn(product);

        ResponseEntity<?> response = productController.updateProduct(1L, product);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void testUpdateProduct_ProductNotFound() {
        Product product = new Product(1L, "Producto 1", 100.0);
        when(productService.updateProduct(1L, product)).thenThrow(new SantanderException("Producto no encontrado con ID: 1"));

        ResponseEntity<?> response = productController.updateProduct(1L, product);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorDto);
        assertEquals("Producto no encontrado con ID: 1", ((ErrorDto) response.getBody()).getMessage());
    }

    @Test
    void testDeleteProduct_ProductExists() {
        doNothing().when(productService).deleteProduct(1L);

        ResponseEntity<?> response = productController.deleteProduct(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteProduct_ProductNotFound() {
        doThrow(new SantanderException("Producto no encontrado con ID: 1")).when(productService).deleteProduct(1L);

        ResponseEntity<?> response = productController.deleteProduct(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorDto);
        assertEquals("Producto no encontrado con ID: 1", ((ErrorDto) response.getBody()).getMessage());
    }
}