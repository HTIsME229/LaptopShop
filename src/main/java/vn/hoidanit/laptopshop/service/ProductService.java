package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.cart;
import vn.hoidanit.laptopshop.domain.cartDetails;
import vn.hoidanit.laptopshop.repository.CartDetailsRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRespository;
import jakarta.servlet.http.HttpSession;

@Service

public class ProductService {
    private ProductRespository productRespository;
    private UserService userService;
    private CartRepository cartRepository;
    private CartDetailsRepository cartDetailsRepository;

    public ProductService(CartDetailsRepository cartDetailsRepository, ProductRespository productRespository,
            UserService userService,
            CartRepository cartRepository) {
        this.productRespository = productRespository;
        this.userService = userService;
        this.cartRepository = cartRepository;
        this.cartDetailsRepository = cartDetailsRepository;
    }

    public Product handleSaveProduct(Product product) {
        return this.productRespository.save(product);
    }

    public List<Product> handleGetAllProDuct() {
        return this.productRespository.findAll();
    }

    public void handleDeleteProduct(long id) {
        productRespository.deleteById(id);
    }

    public Product handleGetDetailProduct(long id) {
        return productRespository.findById(id);
    }

    public void handleAddProductToCart(String email, long product_id, HttpSession session) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                cart otherCart = new cart();
                otherCart.setUser(user);
                otherCart.setSum(0);
                cart = this.cartRepository.save(otherCart);
            }
            // save cart detail
            Product p = this.productRespository.findById(product_id);
            cartDetails oldDetails = this.cartDetailsRepository.findByCartAndProduct(cart, p);
            if (oldDetails == null) {
                cartDetails cd = new cartDetails();
                cd.setCart(cart);
                cd.setProduct(p);
                cd.setPrice(p.getPrice());
                cd.setQuantity(1);
                int s = cart.getSum() + 1;
                cart.setSum(cart.getSum() + 1);
                session.setAttribute("sum", s);
                cart = this.cartRepository.save(cart);
                this.cartDetailsRepository.save(cd);

            } else {
                oldDetails.setQuantity(oldDetails.getQuantity() + 1);

                this.cartDetailsRepository.save(oldDetails);

            }

        }
    }

    public List<cartDetails> handleGetCartDetailByUser(long id) {
        User user = this.userService.getUserById(id);
        cart c = this.cartRepository.findByUser(user);
        if (c == null) {
            cart otherCart = new cart();
            otherCart.setUser(user);
            otherCart.setSum(1);
            c = this.cartRepository.save(otherCart);
        }
        return cartDetailsRepository.findByCart(c);

    }

    @Transactional
    public void handleDeleteProductFromCart(long c_id, long p_id, HttpSession session) {
        cart c = this.cartRepository.findById(c_id);
        Product p = this.productRespository.findById(p_id);
        if (c != null && p != null) {
            int s = c.getSum() - 1;
            c.setSum(c.getSum() - 1);

            session.setAttribute("sum", s);
            this.cartRepository.save(c);
            this.cartDetailsRepository.deleteInBatchByCartAndProduct(c, p);
        }

    }

}
