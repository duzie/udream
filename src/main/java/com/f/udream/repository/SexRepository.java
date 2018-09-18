package com.f.udream.repository;

import com.f.udream.entity.Sext;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SexRepository extends PagingAndSortingRepository<Sext, Integer> {

//    @Query(value = "select u.id user_id,u.name,s.name sex_name from usert u , sext s where u.sid = s.id",
//            countQuery = "select count(1) from usert u , sext s where u.sid = s.id",
//            nativeQuery = true)
//    Page<UserInfo> findUserInfo(Pageable pageable);

}
