package com.emart.ecommerce.repository;

import com.emart.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllById(Long id);

    @Query(value = "select * from product where category_id = ?1", nativeQuery = true)
    List<Product> findAllByCategoryId(int id);
}
