package com.nagarajan.ecommerse_service.Controller;

import com.nagarajan.ecommerse_service.Model.Product.Product;
import com.nagarajan.ecommerse_service.Model.Product.ProductRequest;
import com.nagarajan.ecommerse_service.Model.Product.ProductResponse;
import com.nagarajan.ecommerse_service.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService service;
    @PostMapping
    ResponseEntity<ProductResponse> CreateProduct(@RequestBody ProductRequest request){
        ProductResponse response=service.CreateProduct(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping("/id/{id}")
    ResponseEntity<ProductResponse> UpdateProduct(@PathVariable long id,@RequestBody ProductRequest request){
        ProductResponse response=service.UpdateProduct(id,request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    ResponseEntity<String> DeleteProductById(@PathVariable long id){
        service.DeleteProductById(id);
        return new ResponseEntity<>("Deleted successfully"+id,HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<List<ProductResponse>> GetAllProducts(@RequestParam(required = false,defaultValue = "1") int page,
                                                         @RequestParam(required = false,defaultValue = "20") int size,
                                                         @RequestParam(required = false,defaultValue = "id") String sortby,
                                                         @RequestParam(required = false,defaultValue = "ASC") String direction,
                                                         @RequestParam(required = false) String name,
                                                         @RequestParam(required = false) Double price){
        List<ProductResponse> responses=service.GetAllProducts(page,size,sortby,direction,name,price);
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    ResponseEntity<ProductResponse> GetByProductId(@PathVariable long id){
        ProductResponse response=service.GetByProductId(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    ResponseEntity<ProductResponse> GetByProductName(@PathVariable String name){
        ProductResponse response=service.GetByProductName(name);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
