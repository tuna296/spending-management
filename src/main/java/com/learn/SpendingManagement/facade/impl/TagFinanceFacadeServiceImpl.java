package com.learn.SpendingManagement.facade.impl;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.spendingmanagement.TagFinanceRequest;
import com.learn.SpendingManagement.dto.response.spendingmanagement.TagFinanceResponse;
import com.learn.SpendingManagement.dto.response.spendingmanagement.TransactionResponse;
import com.learn.SpendingManagement.facade.TagFinanceFacadeService;
import com.learn.SpendingManagement.service.spendingmanagement.TagFinanceService;
import com.learn.SpendingManagement.service.spendingmanagement.TransactionService;
import com.learn.SpendingManagement.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class TagFinanceFacadeServiceImpl implements TagFinanceFacadeService {
  private final TagFinanceService tagFinanceService;
  private final TransactionService transactionService;
  private final UserService userService;

  @Override
  public TagFinanceResponse create(TagFinanceRequest request) {
    log.info("(request) create: {}", request);

    this.checkExist(request);

    TagFinanceResponse response = tagFinanceService.create(request);
    response.setUserResponse(userService.detail(request.getUserId()));
    return response;
  }

  @Override
  public TagFinanceResponse detail(String id) {
    log.info("(request) detail: {}", id);

    PageResponse<TransactionResponse> pageResponse = transactionService.list(null, 100, 0, true, id);
    List<TransactionResponse> transactionList = pageResponse.getContent();

    TagFinanceResponse response = tagFinanceService.detail(id);
    response.setTransactionResponses(transactionList);
    return response;
  }


  private void checkExist(TagFinanceRequest request) {
    if (Objects.nonNull(request.getUserId())) {
      userService.detail(request.getUserId());
    }
  }
}
