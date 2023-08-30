package com.iagoscandido.fakeapi.infrastructure;

import com.iagoscandido.fakeapi.apiv1.dto.ProductsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "fake-api", url = "${fake-api.url:#{null}}")
public interface IFakeApiClient {

    @GetMapping("/products")
    List<ProductsDTO> getProducts();
}
