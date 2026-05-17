package com.nagarajan.ecommerse_service.Specification;

import com.nagarajan.ecommerse_service.Model.Product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product>{
    private final String name;
    private final Double price;
    public ProductSpecification(String name,Double price) {
      this.name=name;
      this.price=price;
  }
    @Override
    public @Nullable Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList=new ArrayList<>();
        if((name!=null)&&(!name.isEmpty())){
            predicateList.add(criteriaBuilder.equal(root.get("name"),name));
        }
        if((price!=null)&&(price>0)&&(!price.isNaN())){
            predicateList.add(criteriaBuilder.equal(root.get("price"),price));
        }
        if(predicateList.isEmpty()){
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
    }
}
