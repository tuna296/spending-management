package com.learn.SpendingManagement.service.spendingmanagement;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.spendingmanagement.TransactionRequest;
import com.learn.SpendingManagement.dto.response.spendingmanagement.TransactionResponse;

public interface TransactionService {
  TransactionResponse create(TransactionRequest request);

  TransactionResponse update(TransactionRequest request, String id);

  PageResponse<TransactionResponse> list(String keyword, int size, int page, boolean isAll, String tagId);

  void delete(String id);
}
