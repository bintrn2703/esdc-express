package vn.edu.tdtu.esdcexpress.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_history")
@NoArgsConstructor @Getter @Setter
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String date;
    private String time;
    private String location;
    public OrderHistory(Order order, String date, String time, String location) {
        this.order = order;
        this.date = date;
        this.time = time;
        this.location = location;
    }
}
