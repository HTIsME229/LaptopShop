package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class HomePageController {
    private ProductService productService;

    public HomePageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getHomePage(Model model, HttpServletRequest request) {

        Pageable pageable = PageRequest.of(0, 10);
        String name = "";
        Page<Product> Product = productService.handleGetAllProDuct(pageable);

        List<Product> ListProduct = Product.getContent();
        model.addAttribute("ListProduct", ListProduct);
        return "client/homepage/show";
    }

}
