package com.learn.SpendingManagement.controller.user;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.base.PageResponseGeneral;
import com.learn.SpendingManagement.dto.base.ResponseGeneral;
import com.learn.SpendingManagement.dto.request.user.AccountRequest;
import com.learn.SpendingManagement.dto.request.user.AccountUpdateRequest;
import com.learn.SpendingManagement.dto.response.User.AccountResponse;
import com.learn.SpendingManagement.dto.response.User.LoginResponse;
import com.learn.SpendingManagement.service.MessageService;
import com.learn.SpendingManagement.service.user.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.learn.SpendingManagement.constant.Constants.CommonConstants.DEFAULT_LANGUAGE;
import static com.learn.SpendingManagement.constant.Constants.CommonConstants.LANGUAGE;
import static com.learn.SpendingManagement.constant.Constants.MessageCode.*;

@Slf4j
@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor

public class AccountController {
  private final AccountService accountService;
  private final MessageService messageService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseGeneral<AccountResponse> create(
        @Valid @RequestBody AccountRequest request,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(create)request: {}", request);

    return ResponseGeneral.ofCreated(
          messageService.getMessage(CREATE_ACCOUNT, language),
          accountService.create(request)
    );
  }

  @PutMapping
  public ResponseGeneral<AccountResponse> update(
        @Valid @RequestBody AccountUpdateRequest request,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(update)request: {}", request);

    return ResponseGeneral.ofSuccess(
          messageService.getMessage(UPDATE_ACCOUNT, language),
          accountService.update(request)
    );
  }

  @DeleteMapping("{id}")
  public ResponseGeneral<Void> delete(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(delete)request: {}", id);

    accountService.delete(id);
    return ResponseGeneral.ofSuccess(
          messageService.getMessage(DELETE_ACCOUNT, language)
    );
  }

  @PostMapping("/login")
  public ResponseGeneral<LoginResponse> login(
        @Valid @RequestBody AccountRequest request,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(create)request: {}", request);

    return ResponseGeneral.ofSuccess(
          messageService.getMessage(LOGIN_SUCCESS, language),
          accountService.login(request)
    );
  }

  @GetMapping
  public PageResponseGeneral<PageResponse<AccountResponse>> list(
        @RequestParam(name = "keyword", required = false) String keyword,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "all", defaultValue = "false", required = false) boolean isAll,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
    return PageResponseGeneral.ofSuccess(messageService.getMessage(LIST, language),
          accountService.list(keyword, size, page, isAll)
    );
  }
  @PutMapping("/activate/{id}")
  public ResponseGeneral<Void> activate(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(activate) id : {}", id);
    accountService.active(id);
    return ResponseGeneral.ofSuccess(
          messageService.getMessage(ACTIVATE, language)
    );
  }
  @PutMapping("/deactivate/{id}")
  public ResponseGeneral<Void> deactivate(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(deactivate) id : {}", id);
    accountService.deactivate(id);
    return ResponseGeneral.ofSuccess(
          messageService.getMessage(DEACTIVATE, language)
    );
  }


}

