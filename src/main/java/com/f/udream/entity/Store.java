package com.f.udream.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "U_STORE")
@Data
public class Store {

    private String district;
    private double lat;
    private double lng;
    @Id
    private String storeId;
    private int storeType;
    private int testStatus;


    private String city;//1004774280682008576,
    private String cityName;//深圳,
    private String displayItemName;//吸剪吹,
    private String displayItemPrice;//88.00,38.00,
    private String districtName;//南山,
    private Integer openStatus;// 1,
    private String province;//1004774276416401408,
    private String provinceName;//null,
    private String storeAddress;// 南山区西丽光前工业区（南山睿园）11栋102号,
    private String storeBizHours;// 1030,2300,
    private String storeLevel;//A,
    private String storeName;//光前工作室,

}
