package com.learn.SpendingManagement.repository.user;

import com.learn.SpendingManagement.dto.response.User.RoleResponse;
import com.learn.SpendingManagement.entity.user.Role;
import com.learn.SpendingManagement.repository.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends BaseRepository<Role> {
  @Query("""
         SELECT CASE WHEN COUNT(r) > 0
         THEN true ELSE false END FROM Role r
         WHERE r.name = :name AND r.isDeleted = false
        """)
  boolean checkExist(String name);

@Query("""
       SELECT new com.learn.SpendingManagement.dto.response.User.RoleResponse
      (r.id,r.name, r.description)
       FROM Role r
       WHERE (:keyword is null or
      (lower(r.name) LIKE lower(concat('%', :keyword, '%'))) OR
      (lower(r.description) LIKE lower(concat('%', :keyword, '%')))
      ) AND r.isDeleted = false
      """)
Page<RoleResponse> search(Pageable pageable, String keyword);
  @Query("""
         SELECT new com.learn.SpendingManagement.dto.response.User.RoleResponse
        (r.id,r.name, r.description)
         FROM Role r
         WHERE r.isDeleted= false
        """)
  Page<RoleResponse> findAllRole(Pageable pageable);

  @Modifying
  @Transactional
  @Query("""
        update Role r set r.isDeleted =true where r.id =:id
        """)
  void deleteRole(String id);

  @Query("""
        SELECT new com.learn.SpendingManagement.dto.response.User.RoleResponse
        (r.id,r.name, r.description)
        FROM Role r
        WHERE r.id=:id AND r.isDeleted= false
        """)
  RoleResponse detail(String id);
}
