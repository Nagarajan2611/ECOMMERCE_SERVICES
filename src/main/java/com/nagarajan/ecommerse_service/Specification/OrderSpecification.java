package com.nagarajan.ecommerse_service.Specification;

import com.nagarajan.ecommerse_service.Model.Order.Order;
import com.nagarajan.ecommerse_service.Model.Order.OrderItems;
import com.nagarajan.ecommerse_service.Model.User.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OrderSpecification implements Specification<Order> {
    private final Double totalPrice;
    private final String status;
    public OrderSpecification(Double totalprice,String status) {
        this.status=status;
        this.totalPrice=totalprice;
    }

    @Override
    public @Nullable Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList=new ArrayList<>();
        if((status!=null)&&(!status.isEmpty())){
            predicateList.add(criteriaBuilder.equal(root.get("status"),status));
        }
        if((totalPrice!=null)&&(totalPrice.isNaN())&&(totalPrice>0)){
            predicateList.add(criteriaBuilder.equal(root.get("totalPrice"),totalPrice));
        }
        if(predicateList.isEmpty()){
           return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.or(predicateList.toArray(new Predicate[0]));
    }
}
