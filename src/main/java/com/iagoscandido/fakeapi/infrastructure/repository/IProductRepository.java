package com.iagoscandido.fakeapi.infrastructure.repository;

import com.iagoscandido.fakeapi.infrastructure.model.ProductsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<ProductsEntity, String> {
    Boolean existsByTitle(String title);

    ProductsEntity findByTitle (String title);

    @Transactional
    void deleteByTitle (String title);
}
