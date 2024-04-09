package vn.edu.tdtu.esdcexpress.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class UserRegistrationDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String bank_name;
    private String bank_account_holder;
    private String bank_account_number;
    private String email;

    public UserRegistrationDto(String username, String password, String confirmPassword, String firstName, String lastName, String bank_name, String bank_account_holder, String bank_account_number, String email) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bank_name = bank_name;
        this.bank_account_holder = bank_account_holder;
        this.bank_account_number = bank_account_number;
        this.email = email;
    }
}
