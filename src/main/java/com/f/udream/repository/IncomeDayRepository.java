package com.f.udream.repository;

import com.f.udream.entity.IncomeDay;
import org.springframework.data.repository.CrudRepository;

public interface IncomeDayRepository extends CrudRepository<IncomeDay, String> {

    public Iterable<IncomeDay> findAllByStoreIdAndStatisticsDateStartsWithOrderByStatisticsDateDesc(String storeid,String StatisticsDate);
}
