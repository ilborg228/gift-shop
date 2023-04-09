package ru.samara.giftshop.entity;

import lombok.Data;
import ru.samara.giftshop.exceptions.ApiException;
import ru.samara.giftshop.exceptions.DataValidationResponse;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private String address;

    private Date orderCreation;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public enum Status {
        CREATED(0), SUBMITTED(1), ACCEPTED(2), DELIVERED(3), CANCELLED(4);

        private long statusId;

        Status(long statusId) {
            this.statusId = statusId;
        }

        public long getStatusId() {
            return statusId;
        }

        public static Status getByStatusId(Long statusId) throws ApiException {
            for (Status status : Status.values()) {
                if (status.getStatusId() == statusId)
                    return status;
            }
            throw new ApiException(DataValidationResponse.INVALID_STATUS);
        }
    }
}
