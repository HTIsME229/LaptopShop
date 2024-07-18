package vn.hoidanit.laptopshop.service.Specfication;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;

public class ProducSpecs {
    public static Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.NAME), "%" + name + "%");
    }

    public static Specification<Product> factoryLike(List<String> factory) {
        return (root, query, criteriaBuilder) -> {

            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(root.get(Product_.FACTORY));
            for (String factoryName : factory) {
                inClause.value(factoryName);
            }
            return inClause;

        };
    }

    public static Specification<Product> targetLike(List<String> target) {
        return (root, query, criteriaBuilder) -> {

            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(root.get(Product_.TARGET));
            for (String targets : target) {
                inClause.value(targets);
            }
            return inClause;

        };
    }

    public static Specification<Product> betweenPrice(int start, int end) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(Product_.PRICE), start, end);
    }

    public static Specification<Product> maxPrice(int max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lt(root.get(Product_.PRICE), max);
    }

    public static Specification<Product> minPrice(int min) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.gt(root.get(Product_.PRICE), min);
    }
}
