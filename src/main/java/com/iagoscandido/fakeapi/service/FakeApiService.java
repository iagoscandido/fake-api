package com.iagoscandido.fakeapi.service;

import com.iagoscandido.fakeapi.apiv1.dto.ProductsDTO;
import com.iagoscandido.fakeapi.infrastructure.IFakeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeApiService {
    private final IFakeApiClient client;

    public List<ProductsDTO> getProducts() {
        return client.getProducts();
    }
}
