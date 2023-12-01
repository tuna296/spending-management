package com.learn.SpendingManagement.repository.spendingmanagement;

import com.learn.SpendingManagement.dto.response.spendingmanagement.TagFinanceResponse;
import com.learn.SpendingManagement.dto.response.spendingmanagement.TransactionResponse;
import com.learn.SpendingManagement.entity.spendingmanagement.Transaction;
import com.learn.SpendingManagement.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface TransactionRepository extends BaseRepository<Transaction> {
  @Query("""
        select new com.learn.SpendingManagement.dto.response.spendingmanagement.TransactionResponse
        (tr.id, tr.title, tr.amount, tr.description, t.id,t.name,t.description)
        from Transaction tr
        join TagFinance t on t.id= tr.tagFinanceId
        where tr.id=:id and tr.isDeleted = false
        """)
  TransactionResponse searchById(String id);

  @Transactional
  @Modifying
  @Query("""
        update Transaction t set t.isDeleted=true where t.id=:id
        """)
  void delete(String id);

  @Query("""
          select new com.learn.SpendingManagement.dto.response.spendingmanagement.TransactionResponse
          (t.id,t.title,t.amount,t.description)
          FROM Transaction t
          where t.tagFinanceId= :tagId and t.isDeleted= false
        """)
  Page<TransactionResponse> searchAll(PageRequest pageRequest, String tagId);

//  @Query("""
//          select new com.learn.SpendingManagement.dto.response.spendingmanagement.TransactionResponse
//          (t.id,t.title,t.amount,t.description)
//          FROM Transaction t
//          WHERE(:keyword is null or
//          (lower(t.title) LIKE lower(concat('%', :keyword, '%'))) or
//          (lower(t.description) LIKE lower(concat('%', :keyword, '%')))
//          and t.isDeleted = false)
//        """)
//  Page<TransactionResponse> searchByKey(PageRequest pageRequest, String keyword, String tagId);
@Query("""
                select new com.learn.SpendingManagement.dto.response.spendingmanagement.TransactionResponse
                (t.id, t.title, t.amount, t.description)
                FROM Transaction t
      WHERE ((:keyword is null or :keyword = '') or
        (lower(t.title) LIKE lower(concat('%', :keyword, '%'))) or
        (lower(t.description) LIKE lower(concat('%', :keyword, '%')))
      ) and (t.tagFinanceId = :tagId) and t.isDeleted = false
            """)
Page<TransactionResponse> searchByKey(PageRequest pageRequest, String keyword, String tagId);

}
