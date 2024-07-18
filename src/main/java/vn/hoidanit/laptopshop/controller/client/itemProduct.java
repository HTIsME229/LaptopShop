package vn.hoidanit.laptopshop.controller.client;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Review;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.cart;
import vn.hoidanit.laptopshop.domain.cartDetails;
import vn.hoidanit.laptopshop.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class itemProduct {
    private final ProductService productService;
    private UserService userService;
    private CartRepository cartRepository;

    public itemProduct(ProductService productService, UserService userService, CartRepository cartRepository) {
        this.productService = productService;
        this.userService = userService;
        this.cartRepository = cartRepository;

    }

    @GetMapping("/product/{id}")
    public String getProductDetail(@PathVariable long id, Model model) {
        Product dataProduct = this.productService.handleGetDetailProduct(id);
        java.util.List<Review> review = this.productService.handleGetAllReview(dataProduct);
        model.addAttribute("dataProduct", dataProduct);

        model.addAttribute("review", review);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String postAddToCart(@PathVariable long id, HttpServletRequest request,
            @RequestParam(defaultValue = "0") long currentQuantity) {
        long product_id = id;
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email, product_id, session, currentQuantity);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, Authentication authentication, HttpSession session) {
        String email = authentication.getName();
        User user = this.userService.getUserByEmail(email);

        java.util.List<cartDetails> cd1 = this.productService.handleGetCartDetailByUser(user);
        cart c = this.productService.handleGetCartByUser(user);
        double total = 0;
        for (cartDetails cartDetails : cd1) {
            total = total + cartDetails.getQuantity() * cartDetails.getPrice();
        }
        model.addAttribute("cd", cd1);
        model.addAttribute("total", total);
        model.addAttribute("cart", c);
        return "client/cart/cartpage";
    }

    @PostMapping("/cart/delete/{p_id}")
    public String postDeleteProductFromCart(Authentication authentication, @PathVariable long p_id,
            HttpSession session) {
        String email = authentication.getName();
        User user = this.userService.getUserByEmail(email);
        this.productService.handleDeleteProductFromCart(user, p_id, session);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String getCheckOutPage(Model model, Authentication authentication, HttpSession session) {
        String email = authentication.getName();
        User user = this.userService.getUserByEmail(email);
        java.util.List<cartDetails> cd = this.productService.handleGetCartDetailByUser(user);
        double total = 0;
        for (cartDetails cartDetails : cd) {
            total = total + cartDetails.getQuantity() * cartDetails.getPrice();
        }
        model.addAttribute("totalPrice", total);
        model.addAttribute("cd", cd);
        return "client/cart/checkout";
    }

    @PostMapping("/confirm-checkout")
    public String postComgirmCheckOut(@ModelAttribute("cart") cart cart) {
        java.util.List<cartDetails> cd = new ArrayList<>();
        if (cart != null) {
            cd = cart.getCartDetails();
        }
        this.productService.handleUpdateCartBeforeCheckOut(cd);

        return "redirect:/checkout";
    }

    @PostMapping("/place-order")
    public String handlePlaceOrder(HttpServletRequest request, @RequestParam("receiverName") String receiverName,
            Authentication authentication, HttpSession session,
            @RequestParam("receiverAddress") String receiverAddress,
            @RequestParam("receiverPhone") String receiverPhone) {
        String email = authentication.getName();
        User user = this.userService.getUserByEmail(email);
        this.productService.handlePlaceOrder(user, session, receiverName, receiverAddress, receiverPhone);
        return "redirect:/thank";
    }

    @GetMapping("/thank")
    public String getThankPage() {
        return "client/cart/thank";
    }

    @PostMapping("/post-review/{id}")
    public String postMethodName(Model model, @PathVariable long id, Authentication authentication,
            @RequestParam("review") String review) {
        String email = authentication.getName();
        User user = this.userService.getUserByEmail(email);
        this.productService.handlePostReview(review, user, id);

        return "redirect:/product/{id}";
    }

    @GetMapping("/products")
    public String getShopsPage(Model model, @RequestParam("page") Optional<String> pageOptional,

            @RequestParam("name") Optional<String> nameOptional,
            @RequestParam("factoryNames") Optional<List<String>> factoryNames,
            @RequestParam("targetNames") Optional<List<String>> targetNames,
            @RequestParam("price_range") Optional<String> price_range) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            } else {

            }
        } catch (Exception e) {

        }
        int max = 0;
        int min = 0;
        int start = 0;
        int end = 1000000000;
        if (price_range.isPresent()) {
            switch (price_range.get()) {
                case "10-15-trieu":
                    start = 10000000;
                    end = 15000000;
                    break;
                case "10-20-trieu":
                    start = 15000000;
                    end = 20000000;
                    break;
                case "duoi-10-trieu":
                    max = 10000000;
                    break;
                case "tren-20-trieu":
                    min = 20000000;
                    break;
                default:
                    break;

            }
        }

        String name = nameOptional.isPresent() ? nameOptional.get() : "";
        List<String> factory = factoryNames.isPresent() ? factoryNames.get() : new ArrayList<>();
        List<String> target = targetNames.isPresent() ? targetNames.get() : new ArrayList<>();

        Pageable pageable = PageRequest.of(page - 1, 8);
        Page<Product> Product = productService.handleGetAllProDuctWithSpec(pageable, name, factory, start,
                end, max, min, target);
        List<Product> arrProduct = Product.getContent();
        int currentPage = pageable.getPageNumber();
        int totalPage = Product.getTotalPages();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("arrProduct", arrProduct);
        return "client/product/shop";
    }

}
