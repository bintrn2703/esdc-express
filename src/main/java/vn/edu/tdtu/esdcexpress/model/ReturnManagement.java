package vn.edu.tdtu.esdcexpress.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "return_management")
@NoArgsConstructor @Getter @Setter
public class ReturnManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private String message;
    private byte[] image;
    private String created_at;
    private Double similarity;

    public ReturnManagement(Order order, String message, byte[] image, String created_at, Double similarity) {
        this.order = order;
        this.message = message;
        this.image = image;
        this.created_at = created_at;
        this.similarity = similarity;
    }

}
