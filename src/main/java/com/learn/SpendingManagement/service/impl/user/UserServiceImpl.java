package com.learn.SpendingManagement.service.impl.user;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.user.UserRequest;
import com.learn.SpendingManagement.dto.response.User.UserResponse;
import com.learn.SpendingManagement.entity.user.User;
import com.learn.SpendingManagement.exception.user.AccountAlreadyExistsException;
import com.learn.SpendingManagement.exception.user.EmailAlreadyExistException;
import com.learn.SpendingManagement.exception.user.PhoneAlreadyExistException;
import com.learn.SpendingManagement.exception.user.UserNotFoundException;
import com.learn.SpendingManagement.repository.user.UserRepository;
import com.learn.SpendingManagement.service.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository repository;

  @Override
  @Transactional
  public UserResponse create(UserRequest request) {
    log.info("(request) create");
    this.checkPhoneExist(request.getPhone());
    this.checkPhoneExist(request.getEmail());
    this.checkAccountExist(request.getAccountId());
    User user = new User(
          request.getAddressId(),
          request.getAccountId(),
          request.getEmail(),
          request.getPhone(),
          request.getFullName()
    );
    repository.save(user);
    return new UserResponse(
          user.getId(),
          user.getEmail(),
          user.getPhone(),
          user.getFullName()
    );
  }

  @Override
  @Transactional
  public UserResponse update(UserRequest request, String id) {
    User user = this.find(id);
    checkEmailForUpdate(user.getEmail(), request.getEmail());
    checkPhoneForUpdate(user.getPhone(), request.getPhone());
    setRepositoryForUpdate(user, request);
    repository.save(user);
    return new UserResponse(
          user.getId(),
          user.getEmail(),
          user.getPhone(),
          user.getFullName()
    );
  }

  @Override
  public PageResponse<UserResponse> list(String keyword, int size, int page, boolean isAll) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
    Page<UserResponse> list = isAll ?
          repository.findAllUsers(PageRequest.of(page, size)) :
          repository.search(PageRequest.of(page, size), keyword);
    return PageResponse.of(list.getContent(), list.getNumberOfElements());
  }

  @Override
  @Transactional
  public void delete(String id) {
    this.find(id);
    log.info("(delete) request: {}", id);
    repository.deleteUser(id);
  }

  @Override
  public UserResponse detail(String id) {
    log.info("(detail) request: {}", id);
    this.find(id);
    return repository.detail(id);
  }

  private void checkAccountExist(String id) {
    log.debug("(checkAccountExist): {}", id);
    if (repository.checkAccountExist(id)) {
      log.error("Account Already Exist ");
      throw new AccountAlreadyExistsException();
    }
  }

  private void checkEmailExist(String mail) {
    log.debug("(checkEmailExist): {}", mail);
    if (repository.checkMailExist(mail)) {
      log.error("Email Already Exist ");
      throw new EmailAlreadyExistException();
    }
  }

  private void checkPhoneExist(String phone) {
    log.debug("(checkPhoneExist): {}", phone);
    if (repository.checkPhoneExist(phone)) {
      log.error("Phone Already Exist ");
      throw new PhoneAlreadyExistException();
    }
  }

  private User find(String id) {
    log.debug("find by id: {}", id);
    User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
    if (user.isDeleted()) {
      log.error("User not found");
      throw new UserNotFoundException();
    }
    return user;
  }

  private void setRepositoryForUpdate(User user, UserRequest request) {
    user.setAddressId(request.getAddressId());
    user.setAccountId(request.getAccountId());
    user.setEmail(request.getEmail());
    user.setPhone(request.getPhone());
    user.setFullName(request.getFullName());
  }

  private void checkEmailForUpdate(String oldMail, String newMail) {
    log.debug("(checkEmailExist): {}, {}", oldMail, newMail);
    if (!oldMail.equals(newMail)) {
      log.debug("(checkEmailExist): {},", newMail);
      checkEmailExist(newMail);
    }
  }

  private void checkPhoneForUpdate(String oldPhone, String newPhone) {
    log.debug("(checkPhoneForUpdate): {}", newPhone);
    if (!oldPhone.equals(newPhone)) {
      checkPhoneExist(newPhone);
    }
  }


}
