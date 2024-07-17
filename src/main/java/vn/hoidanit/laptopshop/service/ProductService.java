package vn.hoidanit.laptopshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Review;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.cart;
import vn.hoidanit.laptopshop.domain.cartDetails;
import vn.hoidanit.laptopshop.repository.CartDetailsRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.OrderDetailsRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.repository.ProductRespository;
import vn.hoidanit.laptopshop.repository.ReviewRepository;
import jakarta.servlet.http.HttpSession;

@Service

public class ProductService {
    private ProductRespository productRespository;
    private UserService userService;
    private CartRepository cartRepository;
    private CartDetailsRepository cartDetailsRepository;
    private ReviewRepository reviewRepository;
    private OrderRepository orderRepository;
    private OrderDetailsRepository orderDetailsRepository;

    public ProductService(CartDetailsRepository cartDetailsRepository, ProductRespository productRespository,
            ReviewRepository reviewRepository, OrderRepository orderRepository,
            OrderDetailsRepository orderDetailsRepository,
            UserService userService,
            CartRepository cartRepository) {
        this.productRespository = productRespository;
        this.userService = userService;
        this.cartRepository = cartRepository;
        this.cartDetailsRepository = cartDetailsRepository;
        this.reviewRepository = reviewRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderRepository = orderRepository;
    }

    public Product handleSaveProduct(Product product) {
        return this.productRespository.save(product);
    }

    public Page<Product> handleGetAllProDuct(Pageable pageable) {
        return this.productRespository.findAll(pageable);
    }

    @Transactional
    public void handleDeleteProduct(long id, User user, HttpSession session) {
        Product p = productRespository.findById(id);
        cart c = cartRepository.findByUser(user);
        int s = c.getSum() - 1;
        c.setSum(c.getSum() - 1);
        session.setAttribute("sum", s);
        cartDetailsRepository.deleteByProduct(p);
        productRespository.deleteById(id);

    }

    public Product handleGetDetailProduct(long id) {
        return productRespository.findById(id);
    }

    public void handleAddProductToCart(String email, long product_id, HttpSession session, Long currentQuantity) {
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
                if (currentQuantity != 0) {
                    cd.setQuantity(currentQuantity);
                } else {
                    cd.setQuantity(1);
                }

                int s = cart.getSum() + 1;
                cart.setSum(cart.getSum() + 1);
                session.setAttribute("sum", s);
                cart = this.cartRepository.save(cart);
                this.cartDetailsRepository.save(cd);

            } else {
                if (currentQuantity != 0) {
                    oldDetails.setQuantity(oldDetails.getQuantity() + currentQuantity);
                } else {
                    oldDetails.setQuantity(oldDetails.getQuantity() + 1);
                }

                this.cartDetailsRepository.save(oldDetails);

            }

        }
    }

    public List<cartDetails> handleGetCartDetailByUser(User user) {

        cart c = this.cartRepository.findByUser(user);
        if (c == null) {
            cart otherCart = new cart();
            otherCart.setUser(user);
            otherCart.setSum(0);
            c = this.cartRepository.save(otherCart);
        }
        return cartDetailsRepository.findByCart(c);

    }

    public cart handleGetCartByUser(User user) {
        cart c = this.cartRepository.findByUser(user);
        if (c == null) {
            cart otherCart = new cart();
            otherCart.setUser(user);
            otherCart.setSum(0);
            c = this.cartRepository.save(otherCart);
            return c;
        } else
            return c;

    }

    @Transactional
    public void handleDeleteProductFromCart(User user, long p_id, HttpSession session) {
        cart c = this.cartRepository.findByUser(user);
        Product p = this.productRespository.findById(p_id);
        if (c != null && p != null) {
            int s = c.getSum() - 1;
            c.setSum(c.getSum() - 1);

            session.setAttribute("sum", s);
            this.cartRepository.save(c);
            this.cartDetailsRepository.deleteInBatchByCartAndProduct(c, p);
        }

    }

    @Transactional
    public void handleUpdateCartBeforeCheckOut(List<cartDetails> cd) {
        for (cartDetails item : cd) {
            Optional<cartDetails> cdOptional = this.cartDetailsRepository.findById(item.getId());
            if (cdOptional.isPresent()) {
                cartDetails currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(item.getQuantity());
                this.cartDetailsRepository.save(currentCartDetail);
            }
        }

    }

    public void handlePostReview(String review, User user, long id) {
        Product p = this.productRespository.findById(id);
        Review currentReview = new Review();
        currentReview.setProduct(p);
        currentReview.setUser(user);
        currentReview.setComment(review);
        reviewRepository.save(currentReview);

    }

    public List<Review> handleGetAllReview(Product p) {
        return this.reviewRepository.findByProduct(p);

    }

    @Transactional
    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverAddress,
            String receiverPhone) {
        Order nOrder = new Order();
        cart c = cartRepository.findByUser(user);
        if (c != null) {
            List<cartDetails> cd = c.getCartDetails();
            nOrder.setUser(user);
            nOrder.setReceiverAddress(receiverAddress);
            nOrder.setReceiverName(receiverName);
            nOrder.setReceiverPhone(receiverPhone);
            nOrder.setStatus("PENDING");
            double total = 0;
            for (cartDetails cartDetails : cd) {
                total = total + cartDetails.getQuantity() * cartDetails.getPrice();
            }
            nOrder.setToalPrice(total);
            Order order = this.orderRepository.save(nOrder);
            List<OrderDetail> ListorderDetail = new ArrayList<>();

            for (cartDetails cartDetails : cd) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProduct(cartDetails.getProduct());
                orderDetail.setPrice(cartDetails.getPrice());
                orderDetail.setQuantity(cartDetails.getQuantity());

                ListorderDetail.add(orderDetail);

            }
            orderDetailsRepository.saveAllAndFlush(ListorderDetail);
            cartDetailsRepository.deleteAllByCart(c);
            cartRepository.deleteById(c.getId());
            int sum = 0;
            session.setAttribute("sum", sum);
        }

    }

    public Page<Order> handleGetAllOrder(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order handleGetOrderById(long id) {

        return orderRepository.findById(id);
    }

    public Order handleSaveOrder(Order order) {
        return this.orderRepository.save(order);
    }

    @Transactional
    public void handleDeleteOrder(long id) {
        Order order = orderRepository.findById(id);
        orderDetailsRepository.deleteByOrder(order);
        orderRepository.deleteById(order.getId());
    }

    public List<Order> handleOrderHistory(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        return orderRepository.findByUser(user);
    }
}
