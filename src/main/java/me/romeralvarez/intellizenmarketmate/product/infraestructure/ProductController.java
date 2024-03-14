package me.romeralvarez.intellizenmarketmate.product.infraestructure;

import me.romeralvarez.intellizenmarketmate.product.application.ProductService;
import me.romeralvarez.intellizenmarketmate.product.domain.Product;
import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductDescription;
import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductId;
import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductName;
import me.romeralvarez.intellizenmarketmate.product.domain.vo.ProductPrice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest request) {
    ProductId productId = new ProductId(UUID.randomUUID());
    ProductName productName = new ProductName(request.getName());
    ProductDescription productDescription = new ProductDescription(request.getDescription());
    ProductPrice productPrice = new ProductPrice(request.getPrice());
    LocalDateTime now = LocalDateTime.now();

    Product product = Product.create(productId, productName, productDescription, productPrice, now, now);
    productService.createProduct(product);

    return ResponseEntity.ok("Product created successfully");
  }
}
