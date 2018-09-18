package com.f.udream.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "U_INCOME_DAY")
@Data
public class IncomeDay {

    @Id
    private String id;
    private String storeId;
    private String statisticsDate;
    private double groupCount;
    private double groupIncome;
    private double noGroupCount;
    private double noGroupIncome;
    private double totalIncome;
}
