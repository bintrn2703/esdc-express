package vn.edu.tdtu.esdcexpress.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yaml.snakeyaml.events.Event;

@Entity
@Table(name = "address")
@NoArgsConstructor @Getter @Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String contact_name;
    private String phone_number;
    private String address;
    private String delivery_instruction;
    private String postal_code;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(String type, String contact_name, String phone_number, String address, String delivery_instruction, String postal_code, User user) {
        this.type = type;
        this.contact_name = contact_name;
        this.phone_number = phone_number;
        this.address = address;
        this.delivery_instruction = delivery_instruction;
        this.postal_code = postal_code;
        this.user = user;
    }
}
