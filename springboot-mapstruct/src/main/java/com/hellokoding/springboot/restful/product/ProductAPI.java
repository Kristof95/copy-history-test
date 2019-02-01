package com.hellokoding.springboot.restful.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/products")
public class ProductAPI {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.ok(productMapper.toProductDTOs(productService.findAll()));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productService.save(productMapper.toProduct(productDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toProductDTO(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        Optional<Product> stock = productService.findById(id);

        return ResponseEntity.ok(productMapper.toProductDTO(stock.get()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product product = productService.save(productMapper.toProduct(productDTO));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productMapper.toProductDTO(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        productService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
