package com.learn.SpendingManagement.repository.user;

import com.learn.SpendingManagement.dto.response.User.AddressResponse;
import com.learn.SpendingManagement.entity.user.Address;
import com.learn.SpendingManagement.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AddressRepository extends BaseRepository<Address> {
  @Modifying
  @Transactional
  @Query("UPDATE Address a SET a.isDeleted = true WHERE a.id = :id")
  void delete(String id);

  @Query("""
        SELECT new com.learn.SpendingManagement.dto.response.User.AddressResponse
        (a.id,a.province,a.district,a.ward)
        from Address a
        WHERE a.isDeleted=false
        ORDER BY a.province
        """)
  Page<AddressResponse> finAllAddress(PageRequest pageRequest);

  @Query("""
        SELECT new com.learn.SpendingManagement.dto.response.User.AddressResponse
        (a.id, a.province, a.district, a.ward)
        FROM Address a
        WHERE (:keyword is null or
        lower(a.province) LIKE lower(concat('%', :keyword, '%')) or
        lower(a.district) LIKE lower(concat('%', :keyword, '%')) or
        lower(a.ward) LIKE lower(concat('%', :keyword, '%')))
        AND a.isDeleted = false
        ORDER BY a.province
        """)
  Page<AddressResponse> search(PageRequest pageRequest, @Param("keyword") String keyword);

}
