package com.learn.SpendingManagement.repository.spendingmanagement;

import com.learn.SpendingManagement.dto.response.spendingmanagement.TagFinanceResponse;
import com.learn.SpendingManagement.entity.spendingmanagement.TagFinance;
import com.learn.SpendingManagement.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TagFinanceRepository extends BaseRepository<TagFinance> {
  //  @Query("""
//          select new com.learn.SpendingManagement.dto.response.spendingmanagement.TagFinanceResponse
//          (t.id,t.name,t.description,
//          u.id, u.email, u.phone, u.fullName,
//          tr.id, tr.tagFinanceId, tr.title, tr.amount, tr.description
//          )
//          FROM TagFinance t
//          left join User u on t.userId=u.id
//          left join Transaction tr on tr.tagFinanceId= t.id
//          where t.name= :name and t.isDeleted= false
//        """)
//  TagFinance checkExistByName(String name);
  @Query("""
          select new com.learn.SpendingManagement.dto.response.spendingmanagement.TagFinanceResponse
          (t.id,t.name,t.description)
          FROM TagFinance t
          inner join User u on t.userId=u.id
          where u.id= :id and t.isDeleted= false
        """)
  Page<TagFinanceResponse> searchAll(PageRequest pageRequest, String id);

  @Query("""
          select new com.learn.SpendingManagement.dto.response.spendingmanagement.TagFinanceResponse
          (t.id,t.name,t.description)
          FROM TagFinance t
          left join User u on t.userId=u.id
          WHERE(:keyword is null or
          (lower(t.name) LIKE lower(concat('%', :keyword, '%'))))
           AND u.id= :id and t.isDeleted = false
        """)
  Page<TagFinanceResponse>  searchByKey(PageRequest pageRequest, String keyword, String id);

  @Query("""
         SELECT CASE WHEN COUNT(a) > 0
         THEN true ELSE false END FROM TagFinance a
         WHERE a.name = :name AND a.isDeleted= false
        """)
  boolean existByName(String name);

  @Transactional
  @Modifying
  @Query("""
        update TagFinance t set t.isDeleted=true where t.id=:id
        """)
  void delete(String id);
}
