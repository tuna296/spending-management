package com.learn.SpendingManagement.facade;

import com.learn.SpendingManagement.dto.request.spendingmanagement.TagFinanceRequest;
import com.learn.SpendingManagement.dto.response.spendingmanagement.TagFinanceResponse;

public interface TagFinanceFacadeService {
  TagFinanceResponse create(TagFinanceRequest request);

  TagFinanceResponse detail(String id);
}
