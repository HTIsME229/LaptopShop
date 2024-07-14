package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.cart;
import vn.hoidanit.laptopshop.domain.cartDetails;
import java.util.List;

@Repository
public interface CartDetailsRepository extends JpaRepository<cartDetails, Long> {
    List<cartDetails> findByCart(cart cart);

    void deleteByProduct(Product product);

    void deleteAllByCart(cart cart);

    boolean existsByCartAndProduct(cart cart, Product product);

    cartDetails findByCartAndProduct(cart cart, Product product);

    void deleteInBatchByCartAndProduct(cart cart, Product product);
}
