package vn.edu.tdtu.esdcexpress.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order")
@NoArgsConstructor @Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String pickup_phone;
    private String pickup_name;
    private String pickup_address;
    private String delivery_phone;
    private String delivery_name;
    private String delivery_address;
    private String delivery_instruction;
    private String parcel_name;
    private float parcel_weight;
    private String parcel_dimension;
    private float parcel_value;
    private int item_quantity;
    private int allow_mutual_check;
    private int cod;
    private int collect_type;
    private int shipping_service;
    private int shipping_fee_payment;
    private float shipping_fee;
    private String created_at;
    private String pickup_at;
    private String delivery_at;
    private String status;


    public Order (String pickup_phone, String pickup_name, String pickup_address, String delivery_phone, String delivery_name, String delivery_address, String delivery_instruction, String parcel_name, float parcel_weight, float parcel_value, int item_quantity, int allow_mutual_check, int cod, int collect_type, int shipping_service, int shipping_fee_payment, float shipping_fee, String created_at, String pickup_at, String delivery_at, String status, String parcel_dimension) {
        this.pickup_phone = pickup_phone;
        this.pickup_name = pickup_name;
        this.pickup_address = pickup_address;
        this.delivery_phone = delivery_phone;
        this.delivery_name = delivery_name;
        this.delivery_address = delivery_address;
        this.delivery_instruction = delivery_instruction;
        this.parcel_name = parcel_name;
        this.parcel_weight = parcel_weight;
        this.parcel_value = parcel_value;
        this.item_quantity = item_quantity;
        this.allow_mutual_check = allow_mutual_check;
        this.cod = cod;
        this.collect_type = collect_type;
        this.shipping_service = shipping_service;
        this.shipping_fee_payment = shipping_fee_payment;
        this.shipping_fee = shipping_fee;
        this.created_at = created_at;
        this.pickup_at = pickup_at;
        this.delivery_at = delivery_at;
        this.status = status;
        this.parcel_dimension = parcel_dimension;
    }
}
