package com.tandemloop.product;

import com.tandemloop.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repo;
    private final ProductMapper mapper;
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
    var productIds=request.stream().map(ProductPurchaseRequest::productId).toList();
    var storedProducts=repo.findAllByIdInOrderById(productIds);
    if(productIds.size() != storedProducts.size())
    throw  new ProductPurchaseException("One or more products does not exists");
    var storedRequest=request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
    var purchasedProducts= new ArrayList<ProductPurchaseResponse>();
    for(int i=0;i<storedProducts.size();i++){
        Product product=storedProducts.get(i);
        ProductPurchaseRequest productRequest=storedRequest.get(i);
        if(productRequest.quantity() > product.getAvailableQuantity()){
            throw new ProductPurchaseException("Insufficient quantity for Product ID:: "+productRequest.productId());
        }
        var newAvailableQuantity=product.getAvailableQuantity()-productRequest.quantity();
        product.setAvailableQuantity(newAvailableQuantity);
        repo.save(product);
        purchasedProducts.add(mapper.toProductPurchaseResponse(product,productRequest.quantity()));
    }
    return purchasedProducts;
    }

    public Integer createProduct( ProductRequest request) {
        var product=mapper.toProduct(request);
        return repo.save(product).getId();
    }

    public ProductResponse findById(Integer id) {
        return repo.findById(id).map(mapper::toProductResponse).orElseThrow(()->new EntityNotFoundException("Product not found with the ID:: "+id));
    }

    public List<ProductResponse> findAll() {
        return repo.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
}
