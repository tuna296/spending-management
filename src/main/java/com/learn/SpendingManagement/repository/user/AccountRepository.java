package com.learn.SpendingManagement.repository.user;

import com.learn.SpendingManagement.dto.response.User.AccountResponse;
import com.learn.SpendingManagement.entity.user.Account;
import com.learn.SpendingManagement.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends BaseRepository<Account> {
  @Query("""
        SELECT new com.learn.SpendingManagement.dto.response.User.AccountResponse
        (a.id, a.username, a.isActivated,r.name)
        FROM Account a
        LEFT JOIN Role r ON a.roleId = r.id
        WHERE a.isDeleted=false
        ORDER BY a.username
        """)
  Page<AccountResponse> findAllUsername(Pageable pageable);

  @Query("""
        SELECT new com.learn.SpendingManagement.dto.response.User.AccountResponse
        (a.id, a.username, a.isActivated, r.name)
        FROM Account a
        LEFT JOIN Role r ON a.roleId = r.id
        WHERE a.id=:id AND a.isDeleted=false
        ORDER BY a.username
        """)
  AccountResponse findAccountById(String id);

  @Query("""
        SELECT new com.learn.SpendingManagement.dto.response.User.AccountResponse
        (a.id, a.username, a.isActivated, r.name)
        FROM Account a
        LEFT JOIN Role r ON a.roleId = r.id
        WHERE(:keyword is null or
        (lower(a.username) LIKE lower(concat('%', :keyword, '%'))))
        AND a.isDeleted = false
        order by a.username
        """)
  Page<AccountResponse> search(PageRequest pageRequest, String keyword);

  @Query("""
        SELECT new com.learn.SpendingManagement.entity.user.Account
        (a.username, a.password, a.isActivated)
        FROM Account a
        WHERE a.username = :username AND a.isDeleted=false
        """)
  Account findByUsername(String username);

  @Query("""
         SELECT CASE WHEN COUNT(a) > 0
         THEN true ELSE false END FROM Account a
         WHERE a.username = :username AND a.isDeleted = false
        """)
  boolean existByUsername(String username);

  @Query("""
         SELECT CASE WHEN COUNT(a) > 0
         THEN true ELSE false END FROM Account a
         WHERE a.username = :username
         AND a.password = :password
         AND a.isDeleted = false
        """)
  boolean checkUpdate(String username, String password);

  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.isDeleted = true WHERE a.id = :id")
  void deleteAccount(@Param("id") String id);
  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.isActivated = false WHERE a.id = :id")
  void deactivate(@Param("id") String id);

  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.isActivated = true WHERE a.id = :id")
  void activate(@Param("id") String id);


}
