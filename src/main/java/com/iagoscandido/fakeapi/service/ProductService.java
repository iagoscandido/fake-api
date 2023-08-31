package com.iagoscandido.fakeapi.service;

import com.iagoscandido.fakeapi.apiv1.dto.ProductsDTO;
import com.iagoscandido.fakeapi.infrastructure.mapper.ProductMapper;
import com.iagoscandido.fakeapi.infrastructure.model.ProductsEntity;
import com.iagoscandido.fakeapi.infrastructure.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository repository;
    private final ProductMapper mapper;

    public void saveProducts(ProductsEntity entity) {
        try {
            repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Produtos" + e);
        }
    }

    public ProductsDTO saveProductsDTO(ProductsDTO dto) {
        try {
            ProductsEntity entity = mapper.toEntity(dto);
            return mapper.toDTO(repository.save(entity));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Produtos" + e);
        }
    }

    public ProductsDTO getProductByTitle(String title) {
        try {
            return mapper.toDTO(repository.findByTitle(title));
        } catch (Exception e) {
            throw new RuntimeException(format("Erro ao buscar produto: %s", title), e);
        }
    }

    public List<ProductsDTO> getAllProducts() {
        try {
            return mapper.toListDTO(repository.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os produtos", e);
        }
    }

    public ProductsDTO updateProduct(String id, ProductsDTO dto) {
        try {
            ProductsEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Id não existe no banco de " +
                    "dados"));
            saveProducts(mapper.toEntityUpdate(entity, dto, id));
            return mapper.toDTO(repository.findByTitle(entity.getTitle()));
        } catch (Exception e) {
            throw new RuntimeException(format("Erro ao atualizar produto: %S", id));
        }
    }

    public void deleteProduct(String productTitle) {
        try {
            repository.deleteByTitle(productTitle);
        } catch (Exception e) {
            throw new RuntimeException(format("Erro ao deletar produto: %S", productTitle));
        }
    }

    public Boolean existsByTitle(String title) {
        try {
            return repository.existsByTitle(title);
        } catch (Exception e) {
            throw new RuntimeException(format("Erro ao buscar produto por nome: %S", title) + e);
        }
    }
}
