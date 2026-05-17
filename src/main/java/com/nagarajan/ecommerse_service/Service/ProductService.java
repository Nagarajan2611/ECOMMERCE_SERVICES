package com.nagarajan.ecommerse_service.Service;

import com.nagarajan.ecommerse_service.Model.Product.Product;
import com.nagarajan.ecommerse_service.Model.Product.ProductRequest;
import com.nagarajan.ecommerse_service.Model.Product.ProductResponse;
import com.nagarajan.ecommerse_service.Repo.ProductRepo;
import com.nagarajan.ecommerse_service.ServicesImpl.ProductServeImp;
import com.nagarajan.ecommerse_service.Specification.ProductSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServeImp {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductRepo repo;
    public ProductResponse CreateProduct(ProductRequest request) {
        Product product=mapper.map(request,Product.class);
        product.setDate(LocalDate.now());
        repo.save(product);
        return mapper.map(product,ProductResponse.class);
    }
    public ProductResponse UpdateProduct(long id, ProductRequest request) {
        Product product=repo.findById(id).orElseThrow();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setDate(LocalDate.now());
        repo.save(product);
        return mapper.map(product,ProductResponse.class);
    }
    public void DeleteProductById(long id) {
        repo.deleteById(id);
    }
    public List<ProductResponse> GetAllProducts(int page, int size, String sortby, String direction,
                                                String name,Double price) {
        Sort sort=null;
        if(!direction.equalsIgnoreCase("DESC")){
            sort=Sort.by(sortby).descending();
        }
        if(!direction.equalsIgnoreCase("ASC")){
            sort=Sort.by(sortby).ascending();
        }
        else{
            sort=Sort.by(sortby).ascending();
        }
        PageRequest pageRequest=PageRequest.of(page-1,size,sort);
        Specification<Product> specification=new ProductSpecification(name,price);
        Page<Product> page1=repo.findAll(specification,pageRequest);
        return page1.getContent()
                .stream()
                .map(page2->mapper.map(page2,ProductResponse.class)).toList();
    }
    public ProductResponse GetByProductId(long id) {
        Product product=repo.findById(id).orElseThrow();
        return mapper.map(product,ProductResponse.class);
    }
    public ProductResponse GetByProductName(String name) {
        Optional<Product> product=repo.findByName(name);
        return mapper.map(product,ProductResponse.class);
    }
}
