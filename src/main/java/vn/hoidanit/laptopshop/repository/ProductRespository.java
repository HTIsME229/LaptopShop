package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Product;
import java.util.List;

@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {
    Product findById(long id);
}
