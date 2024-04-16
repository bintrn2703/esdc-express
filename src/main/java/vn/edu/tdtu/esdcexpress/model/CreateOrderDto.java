package vn.edu.tdtu.esdcexpress.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class CreateOrderDto {
    private User user;
    private String pickup_phone;
    private String pickup_name;
    private String pickup_address;
    private String deliver_phone;
    private String deliver_name;
    private String deliver_address;
    private String delivery_instruction;
    private String parcel_name;
    private float parcel_weight;
    private String parcel_dimension;
    private float parcel_value;
    private int item_quantity;
    private String allow_mutual_check;
    private String cod;
    private String collect_type;
    private String shipping_service;
    private String shipping_fee_payment;
    private float shipping_fee = 0;
    private String create_at;
    private String pickup_at;
    private String delivered_at;
    private String status;

    public CreateOrderDto(User user, String pickup_phone, String pickup_name, String pickup_address, String deliver_phone, String deliver_name, String deliver_address, String delivery_instruction, String parcel_name, float parcel_weight, String parcel_dimension, float parcel_value, int item_quantity, String allow_mutual_check, String cod, String collect_type, String shipping_service, String shipping_fee_payment, float shipping_fee, String create_at, String pickup_at, String delivered_at, String status) {
        this.user = user;
        this.pickup_phone = pickup_phone;
        this.pickup_name = pickup_name;
        this.pickup_address = pickup_address;
        this.deliver_phone = deliver_phone;
        this.deliver_name = deliver_name;
        this.deliver_address = deliver_address;
        this.delivery_instruction = delivery_instruction;
        this.parcel_name = parcel_name;
        this.parcel_weight = parcel_weight;
        this.parcel_dimension = parcel_dimension;
        this.parcel_value = parcel_value;
        this.item_quantity = item_quantity;
        this.allow_mutual_check = allow_mutual_check;
        this.cod = cod;
        this.collect_type = collect_type;
        this.shipping_service = shipping_service;
        this.shipping_fee_payment = shipping_fee_payment;
        this.shipping_fee = shipping_fee;
        this.create_at = create_at;
        this.pickup_at = pickup_at;
        this.delivered_at = delivered_at;
        this.status = status;
    }
}
