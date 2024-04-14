package vn.edu.tdtu.esdcexpress.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
public class CreateAddressDto {
    private String type;
    private String contact_name;
    private String phone_number;
    private String address;
    private String delivery_instruction;
    private String postal_code;
    private User user;

    public CreateAddressDto(String type, String contact_name, String phone_number, String address, String delivery_instruction, String postal_code, User user) {
        this.type = type;
        this.contact_name = contact_name;
        this.phone_number = phone_number;
        this.address = address;
        this.delivery_instruction = delivery_instruction;
        this.postal_code = postal_code;
        this.user = user;
    }
}
