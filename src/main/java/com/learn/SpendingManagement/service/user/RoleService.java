package com.learn.SpendingManagement.service.user;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.user.RoleRequest;
import com.learn.SpendingManagement.dto.response.User.RoleResponse;

public interface RoleService {
  RoleResponse create(RoleRequest request);

  RoleResponse update(RoleRequest request, String id);

  void delete(String id);

  PageResponse<RoleResponse> list(String keyword, int size, int page, boolean isAll);

  RoleResponse detail(String id);
}
