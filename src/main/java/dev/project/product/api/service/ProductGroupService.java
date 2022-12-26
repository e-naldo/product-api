package dev.project.product.api.service;

import dev.project.product.api.domain.Product;
import dev.project.product.api.domain.ProductGroup;
import dev.project.product.api.dto.productgroup.ProductGroupCreateDto;
import dev.project.product.api.dto.productgroup.ProductGroupReadDto;
import dev.project.product.api.dto.productgroup.ProductGroupUpdateDto;
import dev.project.product.api.exception.ResourceNotFoundException;
import dev.project.product.api.mapper.ProductGroupMapper;
import dev.project.product.api.repository.ProductGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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
                .orElseThrow(ResourceNotFoundException::new);

        ProductGroup productGroup = mapper.toUpdateEntity(productGroupFound, dto);
        repository.save(productGroup);
        return mapper.toReadDto(productGroup);
    }

    public List<ProductGroupReadDto> findAll() {
        List<ProductGroup> productGroupList = repository.findAll(Sort.by("name").ascending());
        return mapper.toListDto(productGroupList);
    }

    public ProductGroupReadDto findById(Long id) {
        ProductGroup productGroup = repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return mapper.toReadDto(productGroup);
    }

    public List<ProductGroupReadDto> findAllByName(String name){
        List<ProductGroup> productGroupList = repository.findAllByNameContainingIgnoreCaseOrderByName(name);
        return mapper.toListDto(productGroupList);
    }

    public void deleteById(Long id){
        ProductGroup productGroup = repository.getReferenceById(id);
        productGroup.inactivate();
        repository.save(productGroup);
    }

}
