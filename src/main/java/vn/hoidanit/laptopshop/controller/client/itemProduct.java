package vn.hoidanit.laptopshop.controller.client;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.cart;
import vn.hoidanit.laptopshop.domain.cartDetails;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class itemProduct {
    private final ProductService productService;

    public itemProduct(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("/product/{id}")
    public String getProductDetail(@PathVariable long id, Model model) {
        Product dataProduct = this.productService.handleGetDetailProduct(id);
        model.addAttribute("dataProduct", dataProduct);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String postAddToCart(@PathVariable long id, HttpServletRequest request) {
        long product_id = id;
        HttpSession session = request.getSession(false);

        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email, product_id, session);
        return "redirect:/";
    }

    @GetMapping("/cart/{id}")
    public String getCartPage(@PathVariable long id, Model model) {
        java.util.List<cartDetails> cd = this.productService.handleGetCartDetailByUser(id);
        model.addAttribute("cd", cd);
        model.addAttribute("id", id);
        return "client/cart/cartpage";
    }

    @PostMapping("/cart/{u_id}/delete/{c_id}/{p_id}")
    public String postDeleteProductFromCart(@PathVariable long u_id, @PathVariable long c_id, @PathVariable long p_id,
            HttpSession session) {

        this.productService.handleDeleteProductFromCart(c_id, p_id, session);
        return "redirect:/cart/{u_id}";
    }

}
