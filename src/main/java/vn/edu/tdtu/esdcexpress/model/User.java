package vn.edu.tdtu.esdcexpress.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@NoArgsConstructor @Getter @Setter
public class User {
    @Id
    private String username;

    private String password;
    private String name;
    private String bank_name;
    private String bank_account_holder;
    private String bank_account_number;
    private String email;

    public User(String username, String password, String name, String bank_name, String bank_account_holder, String bank_account_number, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.bank_name = bank_name;
        this.bank_account_holder = bank_account_holder;
        this.bank_account_number = bank_account_number;
        this.email = email;
    }
}
