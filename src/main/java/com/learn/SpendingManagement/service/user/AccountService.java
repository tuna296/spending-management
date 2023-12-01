package com.learn.SpendingManagement.service.user;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.user.AccountRequest;
import com.learn.SpendingManagement.dto.request.user.AccountUpdateRequest;
import com.learn.SpendingManagement.dto.response.User.AccountResponse;
import com.learn.SpendingManagement.dto.response.User.LoginResponse;

public interface AccountService {
  AccountResponse create(AccountRequest request);

  AccountResponse update(AccountUpdateRequest request);

  void delete(String id);

  LoginResponse login(AccountRequest request);

  PageResponse<AccountResponse> list(String keyword, int size, int page, boolean isAll);

  void active(String id);

  void deactivate(String id);
  AccountResponse detail(String id);
}
