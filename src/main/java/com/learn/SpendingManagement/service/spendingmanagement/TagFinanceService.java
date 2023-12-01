package com.learn.SpendingManagement.service.spendingmanagement;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.spendingmanagement.TagFinanceRequest;
import com.learn.SpendingManagement.dto.response.spendingmanagement.TagFinanceResponse;

public interface TagFinanceService {
  TagFinanceResponse create(TagFinanceRequest request);

  TagFinanceResponse update(TagFinanceRequest request, String id);

  PageResponse<TagFinanceResponse> list(String keyword, int size, int page, boolean isAll, String id);

  void delete(String id);

  TagFinanceResponse detail(String id);
}
