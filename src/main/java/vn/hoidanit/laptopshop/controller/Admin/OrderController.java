package vn.hoidanit.laptopshop.controller.Admin;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {
    private ProductService productService;
    private OrderRepository orderRepository;

    public OrderController(ProductService productService, OrderRepository orderRepository) {
        this.productService = productService;
        this.orderRepository = orderRepository;

    }

    @GetMapping("/admin/order")
    public String getAdminOrderPage(Model model) {

        List<Order> orders = this.productService.handleGetAllOrder();
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getAdminOrderDetailsPage(Model model, @PathVariable long id) {

        Order order = this.productService.handleGetOrderById(id);
        List<OrderDetail> orderDetails = order.getOrderDetails();
        model.addAttribute("orderDetails", orderDetails);

        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getAdminUpdatePage(Model model, @PathVariable long id) {

        Order order = this.productService.handleGetOrderById(id);
        model.addAttribute("order", order);
        return "admin/order/update";

    }

    @PostMapping("/admin/order/update/{id}")
    public String postMethodName(@PathVariable long id, @ModelAttribute("order") Order order) {

        Order oldOrder = this.productService.handleGetOrderById(id);
        oldOrder.setStatus(order.getStatus());
        this.productService.handleSaveOrder(oldOrder);

        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getMethodName(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete/{id}")
    public String postMethodName(@PathVariable long id) {
        this.productService.handleDeleteOrder(id);
        return "redirect:/admin/order";
    }

    @GetMapping("/order-history")
    public String getOrderHistoryPage(Model model, Authentication authentication) {
        List<Order> order = this.productService.handleOrderHistory(authentication);
        // List<OrderDetail> listOrder = order.getOrderDetails();
        // model.addAttribute("listOrder", listOrder);
        model.addAttribute("order", order);
        return "client/Order/history";
    }

}
