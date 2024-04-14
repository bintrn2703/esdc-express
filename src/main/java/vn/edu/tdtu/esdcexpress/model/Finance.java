package vn.edu.tdtu.esdcexpress.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "finance")
@NoArgsConstructor @Getter @Setter
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private double sender_pay;
    private double receiver_pay;
    private String create_at;
    private double weight;
    private String dimension;
    private double shipping_fee;
    private double parcel_value;
    private String cod;

    public User getUser() {
        return this.user;
    }
}
