package vn.hoidanit.laptopshop.controller.client;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.authDTO;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    UserService userService;
    PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/register")
    public String getAuthPage(Model model) {

        model.addAttribute("newRegister", new authDTO());
        return "client/Auth/register";
    }

    @PostMapping("/register")
    public String postRegister(Model model, @ModelAttribute("newRegister") @Valid authDTO authDTO,
            BindingResult newRegisterBindingResult) {

        if (newRegisterBindingResult.hasErrors()) {
            return "client/Auth/register";
        }
        User user = this.userService.registerDTOToUser(authDTO);
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String getLoginPage() {
        return "client/Auth/login";
    }

    @RequestMapping("/403")
    public String get403Page() {
        return "client/Auth/403forbidden";
    }

}
