package com.iagoscandido.fakeapi.service;

import com.iagoscandido.fakeapi.apiv1.dto.ProductsDTO;
import com.iagoscandido.fakeapi.infrastructure.IFakeApiClient;
import com.iagoscandido.fakeapi.infrastructure.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeApiService {

    private final IFakeApiClient client;
    private final ProductMapper mapper;
    private final ProductService productService;


    public List<ProductsDTO> getProducts() {
        try {
            List<ProductsDTO> dto = client.getProducts();
            dto.forEach(product -> {
                Boolean duplicated = productService.existsByTitle(product.getTitle());
                if (duplicated.equals(false)) {
                    productService.saveProducts(mapper.toEntity(product));
                }
            });

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar e gravar produtos no banco de dados");
        }
        return productService.getAllProducts();
    }

}
