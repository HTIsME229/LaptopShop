package vn.hoidanit.laptopshop.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import vn.hoidanit.laptopshop.service.validator.RegisterChecked;

@RegisterChecked
public class authDTO {

    private String firstName;

    private String lastName;
    @NotEmpty(message = "Không thể để trống")
    private String email;
    @NotEmpty(message = "Không thể để trống")
    private String password;
    @NotEmpty(message = "Không thể để trống")
    private String confirmPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
