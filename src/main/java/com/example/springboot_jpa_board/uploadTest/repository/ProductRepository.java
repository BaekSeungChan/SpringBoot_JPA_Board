package com.example.springboot_jpa_board.uploadTest.repository;

import com.example.springboot_jpa_board.uploadTest.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
