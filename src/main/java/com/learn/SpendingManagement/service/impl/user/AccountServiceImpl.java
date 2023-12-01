package com.learn.SpendingManagement.service.impl.user;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.user.AccountRequest;
import com.learn.SpendingManagement.dto.request.user.AccountUpdateRequest;
import com.learn.SpendingManagement.dto.response.User.AccountResponse;
import com.learn.SpendingManagement.dto.response.User.LoginResponse;
import com.learn.SpendingManagement.entity.user.Account;
import com.learn.SpendingManagement.exception.user.AccountAlreadyExistsException;
import com.learn.SpendingManagement.exception.user.AccountIsDeactivated;
import com.learn.SpendingManagement.exception.user.AccountNotFoundException;
import com.learn.SpendingManagement.exception.user.InvalidAccountException;
import com.learn.SpendingManagement.repository.user.AccountRepository;
import com.learn.SpendingManagement.service.user.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
  private final AccountRepository repository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public AccountResponse create(AccountRequest request) {
    log.info("(create) request: {}", request);
    this.checkAccountExist(request.getUsername());
    Account account = new Account(
          request.getUsername(),
          passwordEncoder.encode(request.getPassword()),
          request.getRoleId()
    );
    repository.saveAndFlush(account);
    return repository.findAccountById(account.getId());
  }


  @Transactional
  @Override
  public AccountResponse update(AccountUpdateRequest request) {
    log.info("(update) request: {}", request);
    this.checkUpdate(request);
    Account account = new Account(
          request.getUsername(),
          passwordEncoder.encode(request.getNewPassword()),
          request.getRoleId()
    );
    repository.saveAndFlush(account);
    return repository.findAccountById(account.getId());
  }

  @Transactional
  @Override
  public void delete(String id) {
    log.info("(delete) id: {}", id);
    this.checkAccountExist(id);
    repository.deleteAccount(id);
  }

  @Override
  public LoginResponse login(AccountRequest request) {
    log.info("(login) request: {}", request);

    Account existingAccount = checkForLogin(request.getUsername());

    if (checkPassword(request.getPassword(), existingAccount.getPassword())) {
      return LoginResponse.of(existingAccount.getUsername());
    } else {
      throw new InvalidAccountException();
    }
  }

  private Account checkForLogin(String username) {
    Account account = repository.findByUsername(username);
    if (account == null) {
      throw new AccountNotFoundException();
    } else if (account.isDeleted()) {
      throw new AccountNotFoundException();
    } else if (!account.isActivated()) {
      throw new AccountIsDeactivated();
    }
    return account;
  }

  @Override
  public PageResponse<AccountResponse> list(String keyword, int size, int page, boolean isAll) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
    Page<AccountResponse> pageResponse = isAll ?
          repository.findAllUsername(PageRequest.of(page, size)) : repository.search(PageRequest.of(page, size), keyword);
    return PageResponse.of(pageResponse.getContent(), pageResponse.getNumberOfElements());
  }

  @Override
  @Transactional
  public void active(String id) {
    log.debug("deactivate: {}", id);
    this.checkById(id);
    repository.activate(id);
  }

  @Override
  @Transactional
  public void deactivate(String id) {
    log.info("deactivate: {}", id);
    this.checkById(id);
    repository.deactivate(id);
  }

  @Override
  public AccountResponse detail(String id) {
    log.info("(detail) request: {}", id);
    checkById(id);
    return repository.findAccountById(id);
  }

  private boolean checkPassword(String rawPassword, String encryptedPassword) {
    return BCrypt.checkpw(rawPassword, encryptedPassword);
  }

  private Account checkById(String id) {
    Account account = repository.findById(id).orElseThrow(AccountNotFoundException::new);
    if (account.isDeleted()) {
      throw new AccountNotFoundException();
    }
    return account;
  }

  private void checkAccountExist(String username) {
    log.debug("check exist: {}", username);
    if (repository.existByUsername(username)) {
      log.error("Account AlreadyExists:{}", username);
      throw new AccountAlreadyExistsException();
    }
  }


  private void checkUpdate(AccountUpdateRequest request) {
    if (!repository.existByUsername(request.getUsername())) {
      log.error("Account not found: {}", request.getUsername());
      throw new AccountNotFoundException();
    }

    if (!repository.checkUpdate(request.getUsername(), request.getOldPassword())) {
      log.error("Invalid old password for account: {}", request.getUsername());
      throw new InvalidAccountException();
    }

    if (!request.getNewPassword().equals(request.getConfirmPassword())) {
      log.error("New password and confirm password do not match for account: {}", request.getUsername());
      throw new InvalidAccountException();
    }

  }


}
