package com.f.udream.service;

import com.f.udream.entity.*;
import com.f.udream.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    IncomeDayRepository incomeDayRepository;

    @Autowired
    OrderTradeRepository orderTradeRepository;

    @Transactional
    @Override
    public void AddOrderTrade(List<OrderTrade> list, String storeId, String month) {
        orderTradeRepository.deleteByStoreIdAndPayTimeStartsWith(storeId, month);
        list.stream().forEach(o -> o.setStoreId(storeId));
        orderTradeRepository.saveAll(list);
    }

    @Transactional
    @Override
    public void add(List<Store> list) {
        storeRepository.deleteAll();
        storeRepository.saveAll(list);
    }

    public Iterable<Store> findAll() {
        return storeRepository.findAllByOrderByStoreName();
    }

    @Transactional
    @Override
    public void addIncomeDay(List<IncomeDay> list) {
        for (IncomeDay incomeDay : list) {
            Optional<IncomeDay> ic = incomeDayRepository.findById(incomeDay.getId());
            if (!(ic.isPresent() && ic.get().getTotalIncome() == incomeDay.getTotalIncome())) {
                incomeDayRepository.save(incomeDay);
            }
        }
    }

    @Override
    public Iterable<IncomeDay> findIncomeByDate(String storeId, String month) {
        return incomeDayRepository.findAllByStoreIdAndStatisticsDateStartsWithOrderByStatisticsDateDesc(storeId, month);
    }

    public Iterable<OrderTrade> queryOrderTrade(String storeId, String month) {
        return orderTradeRepository.findByStoreIdAndPayTimeStartsWithOrderByPayTimeDesc(storeId, month);
    }

    @Override
    public void update(Store store) {
        storeRepository.save(store);
    }

    @Transactional
    @Override
    public void saveOrderTrade(List<OrderTrade> list, String storeId) {
        for (OrderTrade orderTrade : list) {
            orderTradeRepository.deleteByStoreIdAndCraftsmanIdAndPayTime(storeId, orderTrade.getCraftsmanId(), orderTrade.getPayTime());
            orderTrade.setStoreId(storeId);
            orderTradeRepository.save(orderTrade);
        }

    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    SexRepository sexRepository;

    @Autowired
    UsertRepository usertRepository;

    @PostConstruct
    void test() {
        usertRepository.findById(1l);
//        Usert t = new Usert();
//        t.setName("a");
//        userRepository.save(t);
//
//        Sext s = new Sext();
//        s.setName("v");
//        sexRepository.save(s);

//        PageRequest p = PageRequest.of(0, 10);
//        Page<UserInfo> p1 = userRepository.findAll(p);
//        System.out.println(p1.getContent().get(0).getSexName());
    }

}
