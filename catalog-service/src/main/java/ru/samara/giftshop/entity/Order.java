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
        CREATED(0),
        SUBMITTED(1, "Создан"),
        ACCEPTED(2, "Подтвержден"),
        DELIVERED(3, "Доставлен"),
        CANCELLED(4, "Отменен");

        private long statusId;
        private String statusRuName;

        Status(long statusId) {
            this.statusId = statusId;
        }

        Status(long statusId, String statusRuName) {
            this.statusId = statusId;
            this.statusRuName = statusRuName;
        }

        public long getStatusId() {
            return statusId;
        }

        public String getStatusRuName() {
            return statusRuName;
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
