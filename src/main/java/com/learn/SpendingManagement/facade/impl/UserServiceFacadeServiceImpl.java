package com.learn.SpendingManagement.facade.impl;

import com.learn.SpendingManagement.dto.request.user.UserRequest;
import com.learn.SpendingManagement.dto.response.User.AccountResponse;
import com.learn.SpendingManagement.dto.response.User.AddressResponse;
import com.learn.SpendingManagement.dto.response.User.RoleResponse;
import com.learn.SpendingManagement.dto.response.User.UserResponse;
import com.learn.SpendingManagement.facade.UserFacadeService;
import com.learn.SpendingManagement.service.user.AccountService;
import com.learn.SpendingManagement.service.user.AddressService;
import com.learn.SpendingManagement.service.user.RoleService;
import com.learn.SpendingManagement.service.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserServiceFacadeServiceImpl implements UserFacadeService {
  private final UserService userService;
  private final AccountService accountService;
  private final AddressService addressService;
  private final RoleService roleService;

  @Override
  @Transactional
  public UserResponse create(UserRequest request) {
    log.info("(create) request: {}", request);
    UserResponse response = userService.create(request);
    AccountResponse accountResponse = accountService.detail(request.getAccountId());
    AddressResponse addressResponse = addressService.detail(request.getAddressId());
    response.setAccountResponse(accountResponse);
    response.setAddressResponse(addressResponse);
    return response;
  }

  @Override
  @Transactional
  public UserResponse update(UserRequest request, String id) {
    log.info("(update) request: {}", request);
    UserResponse response = userService.update(request, id);
    AccountResponse accountResponse = accountService.detail(request.getAccountId());
    AddressResponse addressResponse = addressService.detail(request.getAddressId());
    response.setAccountResponse(accountResponse);
    response.setAddressResponse(addressResponse);
    return response;
  }

}
