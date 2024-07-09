package vn.hoidanit.laptopshop.controller.Admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {
    private PasswordEncoder passwordEncoder;
    private UploadService uploadService;
    private ProductService productService;

    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public String getDasBoard(Model model) {

        List<Product> arrProduct = productService.handleGetAllProDuct();
        System.out.println(arrProduct);
        model.addAttribute("arrProduct", arrProduct);

        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @RequestMapping(value = "/admin/product/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newProduct") @Valid Product newProduct,
            BindingResult newProductBindingResult,
            @RequestParam("file") MultipartFile file) {

        if (newProductBindingResult.hasErrors()) {
            return "admin/product/create";
        }
        String thumbnail = this.uploadService.handleSaveUploadFile(file, "product");
        newProduct.setImage(thumbnail);
        this.productService.handleSaveProduct(newProduct);

        return "redirect:/admin/product";

    }

    @RequestMapping("/admin/product/delete/{id}")
    public String getDeleteProduct(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete/{id}")
    public String postDeleteProduct(@PathVariable long id) {
        productService.handleDeleteProduct(id);
        return "redirect:/admin/product";
    }

    @RequestMapping("/admin/product/detail/{id}")
    public String getDetailProduct(@PathVariable long id, Model model) {
        Product dataProduct = productService.handleGetDetailProduct(id);
        model.addAttribute("dataProduct", dataProduct);
        return "admin/product/detail";
    }

    @RequestMapping("/admin/product/update/{id}")
    public String getUpdateProduct(@PathVariable long id, Model model) {
        Product dataProduct = productService.handleGetDetailProduct(id);
        model.addAttribute("newProduct", dataProduct);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update/{id}")
    public String postUpdateProduct(@PathVariable long id, @ModelAttribute("newProduct") @Valid Product newProduct,
            BindingResult newProductBindingResult, @RequestParam("file") MultipartFile file) {
        if (newProductBindingResult.hasErrors()) {
            return "admin/product/update";
        }
        String img = this.uploadService.handleSaveUploadFile(file, "product");
        Product dataProduct = productService.handleGetDetailProduct(id);
        dataProduct.setName(newProduct.getName());
        dataProduct.setPrice(newProduct.getPrice());
        dataProduct.setDetailDesc(newProduct.getDetailDesc());
        dataProduct.setShortDesc(newProduct.getShortDesc());
        dataProduct.setPrice(newProduct.getPrice());
        if (!img.isEmpty()) {
            dataProduct.setImage(img);
        }
        dataProduct.setQuantity(newProduct.getQuantity());
        dataProduct.setFactory(newProduct.getFactory());
        dataProduct.setTarget(newProduct.getTarget());
        this.productService.handleSaveProduct(dataProduct);
        return "redirect:/admin/product";
    }

}
