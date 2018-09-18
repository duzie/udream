package com.f.udream.repository;

import com.f.udream.entity.Store;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<Store, String> {

    Iterable<Store> findAllByOrderByStoreName();
}
