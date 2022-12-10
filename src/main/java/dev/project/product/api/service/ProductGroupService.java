package dev.project.product.api.service;

import dev.project.product.api.domain.ProductGroup;
import dev.project.product.api.dto.ProductGroupCreateDto;
import dev.project.product.api.dto.ProductGroupReadDto;
import dev.project.product.api.dto.ProductGroupUpdateDto;
import dev.project.product.api.mapper.ProductGroupMapper;
import dev.project.product.api.repository.ProductGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupService {

    @Autowired
    private ProductGroupRepository repository;

    @Autowired
    private ProductGroupMapper mapper;


    public ProductGroupReadDto create(ProductGroupCreateDto productGroupCreateDto) {
        ProductGroup productGroup = mapper.toCreateEntity(productGroupCreateDto);
        repository.save(productGroup);
        return mapper.toReadDto(productGroup);
    }

    public ProductGroupReadDto update(Long id, ProductGroupUpdateDto dto) {
        ProductGroup productGroupFound = repository.findById(id)
                .orElseThrow(RuntimeException::new);

        ProductGroup productGroup = mapper.toUpdateEntity(productGroupFound, dto);
        repository.save(productGroup);
        return mapper.toReadDto(productGroup);
    }

    public List<ProductGroupReadDto> findAll() {
        List<ProductGroup> productGroupList = repository.findAll();
        return mapper.toListDto(productGroupList);
    }

    public ProductGroupReadDto findById(Long id) {
        ProductGroup productGroup = repository.findById(id)
                .orElseThrow(RuntimeException::new);
        return mapper.toReadDto(productGroup);
    }

    public List<ProductGroupReadDto> findAllByName(String name){
        List<ProductGroup> productGroupList = repository.findAllByNameContainingIgnoreCase(name);
        return mapper.toListDto(productGroupList);
    }
}
