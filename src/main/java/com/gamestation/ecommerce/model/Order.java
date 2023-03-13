package order.customers;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "email")
    private String email;

    @Column(name = "total")
    private Double total;

    @Column(name = "status")
    private String status;




    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    public Order() {
    }

    public Order(String productId, String email, Double total, String status) {
        this.productId = productId;
        this.email = email;
        this.total = total;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", productId='" + productId + '\'' +
                ", email='" + email + '\'' +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}
