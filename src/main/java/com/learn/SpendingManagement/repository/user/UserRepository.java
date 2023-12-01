package com.learn.SpendingManagement.repository.user;

import com.learn.SpendingManagement.dto.response.User.UserResponse;
import com.learn.SpendingManagement.entity.user.User;
import com.learn.SpendingManagement.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends BaseRepository<User> {
  @Query("""
         SELECT CASE WHEN COUNT(u) > 0
         THEN true ELSE false END FROM User u
         WHERE u.phone = :phone AND u.isDeleted = false
        """)
  boolean checkPhoneExist(String phone);

  @Query("""
         SELECT CASE WHEN COUNT(u) > 0
         THEN true ELSE false END FROM User u
         WHERE u.email = :email AND u.isDeleted = false
        """)
  boolean checkMailExist(String email);

  @Transactional
  @Modifying
  @Query("""
        Update User u SET u.isDeleted= true where u.id=:id
        """)
  void deleteUser(String id);


  @Query("""
        SELECT new com.learn.SpendingManagement.dto.response.User.UserResponse
        (u.id,u.email,u.phone,u.fullName,
            a.id, a.username,
            ad.id, ad.province, ad.district, ad.ward, r.id, r.name, r.description)
        FROM User u
        left join Account a ON a.id = u.accountId
        left join Address ad ON ad.id= u.addressId
        left join Role r ON r.id=a.roleId
        WHERE (:keyword is null or
        lower(u.email) LIKE lower(concat('%', :keyword, '%')) or
        lower(u.phone) LIKE lower(concat('%', :keyword, '%')) or
        lower(u.email) LIKE lower(concat('%', :keyword, '%')) or
        lower(u.fullName) LIKE lower(concat('%', :keyword, '%')))
        AND u.isDeleted = false
        ORDER BY u.fullName
        """)
  Page<UserResponse> search(PageRequest pageRequest, @Param("keyword") String keyword);

  @Query("""
        SELECT new com.learn.SpendingManagement.dto.response.User.UserResponse
        (u.id,u.email,u.phone,u.fullName,
            a.id, a.username,
            ad.id, ad.province, ad.district, ad.ward, r.id, r.name, r.description)
        FROM User u
        left join Account a ON a.id = u.accountId
        left join Address ad ON ad.id= u.addressId
        left join Role r ON r.id=a.roleId
        WHERE u.id=:id and u.isDeleted=false
        ORDER BY u.fullName
        """)
  UserResponse detail(String id);
  @Query("""
        SELECT new com.learn.SpendingManagement.dto.response.User.UserResponse
        (u.id,u.email,u.phone,u.fullName,
            a.id, a.username,
            ad.id, ad.province, ad.district, ad.ward, r.id, r.name, r.description)
        FROM User u
        left join Account a ON a.id = u.accountId
        left join Address ad ON ad.id= u.addressId
        left join Role r ON r.id=a.roleId
        WHERE u.isDeleted=false
        ORDER BY u.fullName
        """)
  Page<UserResponse> findAllUsers(Pageable pageable);
}

