package com.f.udream.repository;

import com.f.udream.entity.OrderTrade;
import org.springframework.data.repository.CrudRepository;

public interface OrderTradeRepository extends CrudRepository<OrderTrade, String> {

    void deleteByStoreIdAndPayTimeStartsWith(String storeId, String month);

    void deleteByStoreIdAndCraftsmanIdAndPayTime(String storeId, String craftsmanId, String payTime);

    Iterable<OrderTrade> findByStoreIdAndPayTimeStartsWithOrderByPayTimeDesc(String storeId, String payTime);
}
