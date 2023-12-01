package com.learn.SpendingManagement.facade;

import com.learn.SpendingManagement.dto.request.user.UserRequest;
import com.learn.SpendingManagement.dto.response.User.UserResponse;

public interface UserFacadeService {
  UserResponse create(UserRequest request);

  UserResponse update(UserRequest request, String id);
}
