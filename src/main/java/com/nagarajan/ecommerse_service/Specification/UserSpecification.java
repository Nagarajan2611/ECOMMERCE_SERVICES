package com.nagarajan.ecommerse_service.Specification;

import com.nagarajan.ecommerse_service.Model.User.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {
    private final String name;
    private final String address;
    public UserSpecification(String name,String address) {
        this.name=name;
        this.address=address;
    }
    @Override
    public @Nullable Predicate toPredicate(Root<User> root,
                                           CriteriaQuery<?> query,
                                           CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicate=new ArrayList<>();
        if((name!=null)&&(!name.isEmpty())){
            predicate.add(criteriaBuilder.equal(root.get("name"),name));
        }
        if((address!=null)&&(!address.isEmpty())){
            predicate.add(criteriaBuilder.equal(root.get("address"),address));
        }
        if(predicate.isEmpty()){
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.or(predicate.toArray(new Predicate[0]));
    }
}
