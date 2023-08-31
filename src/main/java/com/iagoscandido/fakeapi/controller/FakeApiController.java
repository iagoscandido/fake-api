package com.iagoscandido.fakeapi.controller;

import com.iagoscandido.fakeapi.apiv1.dto.ProductsDTO;
import com.iagoscandido.fakeapi.service.FakeApiService;
import com.iagoscandido.fakeapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "fake-api")
public class FakeApiController {

    private final FakeApiService service;
    private final ProductService productService;

    @Operation(description = "Busca todos os produtos no banco de dados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar produtos")
    })
    @GetMapping("/")
    public ResponseEntity<List<ProductsDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(description = "Busca produto por nome", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar produto.")
    })
    @GetMapping("/{title}")
    public ResponseEntity<ProductsDTO> getProductsByTitle(@PathVariable(value = "title") String title) {
        return ResponseEntity.ok(productService.getProductByTitle(title));
    }

    @Operation(description = "salva novos produtos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os produtos")
    })
    @PostMapping("/")
    public ResponseEntity<ProductsDTO> saveProducts(@RequestBody ProductsDTO dto) {
        return ResponseEntity.ok(productService.saveProductsDTO(dto));
    }

    @Operation(description = "Realiza update de novos produtos", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar produto")
    })
    @PutMapping("/")
    public ResponseEntity<ProductsDTO> updateProducts(
            @RequestParam("id") String id,
            @RequestBody ProductsDTO dto
    ) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @Operation(description = "Deleta produtos", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar produto")
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteproduct(@RequestParam("title") String title) {
        productService.deleteProduct(title);
        return ResponseEntity.accepted().build();
    }

    @Operation(description = "Busca produtos da API externa e salva no banco de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
    })
    @PostMapping("/api")
    public ResponseEntity<List<ProductsDTO>> saveProductsApi() {
        return ResponseEntity.ok(service.getProducts());
    }

}
