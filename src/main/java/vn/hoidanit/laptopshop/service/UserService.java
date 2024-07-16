package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.authDTO;
import vn.hoidanit.laptopshop.repository.OrderRepository;
import vn.hoidanit.laptopshop.repository.ProductRespository;
import vn.hoidanit.laptopshop.repository.RoleRespository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRespository roleRespository;
    private OrderRepository orderRepository;
    private ProductRespository productRespository;

    public UserService(UserRepository userRepository, RoleRespository roleRespository, OrderRepository orderRepository,
            ProductRespository productRespository) {
        this.userRepository = userRepository;
        this.roleRespository = roleRespository;
        this.orderRepository = orderRepository;
        this.productRespository = productRespository;
    }

    public String handleHello() {
        return "hello from service";
    }

    public User handleSaveUser(User user)

    {
        return this.userRepository.save(user);
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public User getAllUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    };

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public User deleteUserById(long id) {
        return this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRespository.findByName(name);
    }

    // public Role GetRoleById(long id) {
    // return this.roleRespository.FindById(id);
    // }
    public User registerDTOToUser(authDTO authDTO) {
        User user = new User();
        user.setFullName(authDTO.getFirstName() + " " + authDTO.getLastName());
        user.setEmail(authDTO.getEmail());
        user.setPassword(authDTO.getPassword());
        return user;
    }

    public boolean checkEmailExits(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public long countOrders() {
        return this.orderRepository.count();
    }

    public long countProducts() {
        return this.productRespository.count();
    }

    public long countUsers() {
        return this.userRepository.count();
    }
}
