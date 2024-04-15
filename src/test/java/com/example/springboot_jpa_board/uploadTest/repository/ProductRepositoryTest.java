package com.example.springboot_jpa_board.uploadTest.repository;

import com.example.springboot_jpa_board.uploadTest.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInner(){
        Product product = Product.builder()
                .pname("Test ").pdesc("Test Dest").price(1000).build();

        product.addImageString(UUID.randomUUID()+"_"+"IMAGE1.jpg");
        product.addImageString(UUID.randomUUID()+"_"+"IMAGE2.jpg");

        productRepository.save(product);
    }

}