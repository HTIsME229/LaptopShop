package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.cart;
import vn.hoidanit.laptopshop.domain.cartDetails;
import vn.hoidanit.laptopshop.repository.CartDetailsRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRespository;

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

    public void handleAddProductToCart(String email, long product_id) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                cart otherCart = new cart();
                otherCart.setUser(user);
                otherCart.setSum(1);
                cart = this.cartRepository.save(otherCart);
            }
            // save cart detail
            Product p = this.productRespository.findById(product_id);

            cartDetails cd = new cartDetails();
            cd.setCart(cart);
            cd.setProduct(p);
            cd.setPrice(p.getPrice());
            cd.setQuantity(1);
            this.cartDetailsRepository.save(cd);

        }
    }

}
