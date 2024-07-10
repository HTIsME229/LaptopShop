package vn.hoidanit.laptopshop.repository;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.cart;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<cart, Long> {
    cart findByUser(User user);

    cart findById(long id);
}
