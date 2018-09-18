package com.f.udream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "U_ORDER_TRADE")
public class OrderTrade {

    @Id
    @GeneratedValue(generator = "guid")
    @GenericGenerator(name = "guid", strategy = "guid")
    private String id;
    private String craftsmanId;
    private String craftsmanName;
    private Double groupAmount;
    private Double groupCount;
    private Double nonGroupAmount;
    private Double nonGroupCount;
    private String payTime;
    private Double totalAmount;
    private String storeId;
}
