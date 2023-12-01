package com.learn.SpendingManagement.service.user;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.user.AddressRequest;
import com.learn.SpendingManagement.dto.response.User.AddressResponse;

public interface AddressService {
  AddressResponse create(AddressRequest request);

  AddressResponse update(AddressRequest request, String id);

  void delete(String id);

  PageResponse<AddressResponse> list(String keyword, int size, int page, boolean isAll);
  AddressResponse detail(String id);
}
