package com.nagarajan.ecommerse_service.ServicesImpl;

import com.nagarajan.ecommerse_service.Model.Product.Product;
import com.nagarajan.ecommerse_service.Model.Product.ProductRequest;
import com.nagarajan.ecommerse_service.Model.Product.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ProductServeImp {
    ProductResponse CreateProduct(ProductRequest request);
    ProductResponse UpdateProduct(long id,ProductRequest request);
    void DeleteProductById(long id);
    List<ProductResponse> GetAllProducts(int page, int size, String sortby, String direction,String name,Double price);
    ProductResponse GetByProductId(long id);
    ProductResponse GetByProductName(String name);
}
