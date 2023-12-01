package com.learn.SpendingManagement.service.impl.spendingmanagement;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.spendingmanagement.TransactionRequest;
import com.learn.SpendingManagement.dto.response.spendingmanagement.TagFinanceResponse;
import com.learn.SpendingManagement.dto.response.spendingmanagement.TransactionResponse;
import com.learn.SpendingManagement.entity.spendingmanagement.Transaction;
import com.learn.SpendingManagement.exception.spendingmanagement.TransactionNotFoundException;
import com.learn.SpendingManagement.repository.spendingmanagement.TransactionRepository;
import com.learn.SpendingManagement.service.spendingmanagement.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository repository;

  @Override
  @Transactional
  public TransactionResponse create(TransactionRequest request) {
    log.info("(request) create: {}", request);
    Transaction transaction = new Transaction(
          request.getTagFinanceId(),
          request.getTitle(),
          request.getAmount(),
          request.getDescription()
    );
    repository.saveAndFlush(transaction);
    return repository.searchById(transaction.getId());
  }

  @Override
  public TransactionResponse update(TransactionRequest request, String id) {
    log.info("(request) update: {}", request);
    Transaction transaction = this.find(id);
    this.setValueForUpdate(request, transaction);
    repository.save(transaction);
    return new TransactionResponse(
          transaction.getId(),
          transaction.getTitle(),
          transaction.getAmount(),
          transaction.getDescription()
    );
  }

  private Transaction find(String id) {
    log.debug("(find):{}", id);
    Transaction transaction = repository.findById(id).orElseThrow(TransactionNotFoundException::new);
    if (transaction.isDeleted()) {
      throw new TransactionNotFoundException();
    }
    return transaction;
  }

  private void setValueForUpdate(TransactionRequest request, Transaction transaction) {
    log.info("(setValueForUpdate), request: {}, transaction: {}", request, transaction);
    transaction.setTagFinanceId(request.getTagFinanceId());
    transaction.setDescription(request.getDescription());
    transaction.setTitle(request.getTitle());
    transaction.setAmount(request.getAmount());
  }

  @Override
  public PageResponse<TransactionResponse> list(String keyword, int size, int page, boolean isAll, String tagId) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}, tagId: {}", keyword, size, page, isAll, tagId);
    Page<TransactionResponse> pageResponse = isAll ?
          repository.searchAll(PageRequest.of(page, size), tagId) :
          repository.searchByKey(PageRequest.of(page, size), keyword, tagId);
    return PageResponse.of(pageResponse.getContent(), pageResponse.getNumberOfElements());
  }

  @Override
  public void delete(String id) {
    log.debug("(delete) request:{}", id);
    this.find(id);
    repository.delete(id);
  }
}
