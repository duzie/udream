package com.f.udream.service;

import com.f.udream.entity.IncomeDay;
import com.f.udream.entity.OrderTrade;
import com.f.udream.entity.Store;

import java.util.List;

public interface StoreService {


    public void AddOrderTrade(List<OrderTrade> list, String storeId, String month);

    public void add(List<Store> list);

    public Iterable<Store> findAll();

    public void update(Store store);

    public void addIncomeDay(List<IncomeDay> list);

    public Iterable<IncomeDay> findIncomeByDate(String storeId, String statisticsDate);

    Iterable<OrderTrade> queryOrderTrade(String storeId, String month);

    void saveOrderTrade(List<OrderTrade> list, String yuanlingStoreId);
}
