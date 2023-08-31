package com.iagoscandido.fakeapi.infrastructure.mapper;

import com.iagoscandido.fakeapi.apiv1.dto.ProductsDTO;
import com.iagoscandido.fakeapi.infrastructure.model.ProductsEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class ProductMapper {

    public ProductsEntity toEntity(ProductsDTO dto) {
        return ProductsEntity.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .title(dto.getTitle())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .image(dto.getImage())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public ProductsEntity toEntityUpdate(ProductsEntity entity, ProductsDTO dto, String id) {
        return ProductsEntity.builder()
                .id(id)
                .title(dto.getTitle() != null ? dto.getTitle() : entity.getTitle())
                .category(dto.getCategory() != null ? dto.getCategory() : entity.getCategory())
                .description(dto.getDescription() != null ? dto.getDescription() : entity.getDescription())
                .price(dto.getPrice() != null ? dto.getPrice() : entity.getPrice())
                .image(dto.getImage() != null ? dto.getImage() : entity.getImage())
                .createdAt(entity.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public ProductsDTO toDTO(ProductsEntity entity) {
        return ProductsDTO.builder()
                .entityId(entity.getId())
                .title(entity.getTitle())
                .category(entity.getCategory())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .image(entity.getImage())
                .build();
    }

    public List<ProductsDTO> toListDTO(List<ProductsEntity> entityList) {
        return entityList.stream().map(this::toDTO).toList();
    }
}
