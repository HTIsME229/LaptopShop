package vn.hoidanit.laptopshop.controller.Admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private UserService userService;
    private UploadService uploadService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {

        String test = this.userService.handleHello();
        model.addAttribute("eric", test);
        return "hello";

    }

    @RequestMapping("/admin/user/create")
    public String getCreatePage(Model model) {
        // String test = this.userService.handleCreate();
        model.addAttribute("newUser", new User());
        return "admin/user/create";

    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {

        User userDetails = this.userService.getUserById(id);
        model.addAttribute("userDetail", userDetails);
        return "admin/user/userDetail";
    }

    @RequestMapping("/admin/user")
    public String getTableUser(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;
        try {
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            } else {

            }
        } catch (Exception e) {

        }
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<User> arrUser = this.userService.getAllUser(pageable);
        List<User> arrUsers = arrUser.getContent();
        int totalPage = arrUser.getTotalPages();
        int currentPage = pageable.getPageNumber();
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("arrUsers", arrUsers);
        return "admin/user/show";

    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") @Valid User hoidanit,
            BindingResult newUserbindingResult,
            @RequestParam("file") MultipartFile file) {
        List<FieldError> errors = newUserbindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
        }

        if (!newUserbindingResult.hasErrors()) {
            String Avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
            String hashPassword = this.passwordEncoder.encode(hoidanit.getPassword());
            hoidanit.setAvatar(Avatar);
            hoidanit.setPassword(hashPassword);
            hoidanit.setRole(this.userService.getRoleByName(hoidanit.getRole().getName()));
            this.userService.handleSaveUser(hoidanit);
            return "redirect:/admin/user";
        }

        return "/admin/user/create";

    }

    @RequestMapping("/admin/user/update/{id}")
    public String updateUserPage(Model model, @PathVariable long id) {
        User currentData = this.userService.getUserById(id);

        model.addAttribute("currentData", currentData);
        return "admin/user/update";

    }

    @RequestMapping(value = "/admin/user/update/{id}", method = RequestMethod.POST)
    public String updateUser(Model model, @ModelAttribute("currentData") User dataUpdate, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        if (dataUpdate != null) {
            currentUser.setAddress(dataUpdate.getAddress());
            currentUser.setFullName(dataUpdate.getFullName());
            currentUser.setPhone(dataUpdate.getPhone());
            currentUser.setPassword(dataUpdate.getPassword());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";

    }

    @RequestMapping("/admin/user/delete/{id}")
    public String getDeletePage(@PathVariable long id, Model model) {

        model.addAttribute("id", id);

        return "admin/user/delete";

    }

    @PostMapping("/admin/user/delete/{id}")
    public String postDeleteUser(@PathVariable long id) {

        this.userService.deleteUserById(id);
        return "redirect:/admin/user";
    }

    @RequestMapping("/manage-account")
    public String getManageAccountPage(Authentication authentication, Model model) {
        String email = authentication.getName();
        User currentData = this.userService.getUserByEmail(email);

        model.addAttribute("currentData", currentData);
        return "client/Auth/account";

    }

    @PostMapping("/manage-account")
    public String postMethodName(Model model, @ModelAttribute("currentData") User dataUpdate,
            Authentication authentication, @RequestParam("file") MultipartFile file) {
        String email = authentication.getName();
        User currentUser = this.userService.getUserByEmail(email);
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");

        if (dataUpdate != null) {
            currentUser.setAddress(dataUpdate.getAddress());
            currentUser.setFullName(dataUpdate.getFullName());
            currentUser.setPhone(dataUpdate.getPhone());
            currentUser.setAvatar(avatar);
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/manage-account";
    }

    @GetMapping("/change-password")
    public String getMethodName(Authentication authentication, Model model) {
        String email = authentication.getName();
        User currentData = this.userService.getUserByEmail(email);
        model.addAttribute("currentData", currentData);
        return "client/Auth/changepassword";
    }

    @PostMapping("/change-password")
    public String postMethodName(@RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword, Authentication authentication) {
        String email = authentication.getName();
        User currentUser = this.userService.getUserByEmail(email);

        String hashNewPassword = this.passwordEncoder.encode(newPassword);

        if (this.passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(hashNewPassword);
            this.userService.handleSaveUser(currentUser);
            return "redirect:/";
        }
        return "redirect:/manage-account";
    }

}
