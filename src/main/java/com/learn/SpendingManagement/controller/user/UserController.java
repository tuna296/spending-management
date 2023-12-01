package com.learn.SpendingManagement.controller.user;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.base.PageResponseGeneral;
import com.learn.SpendingManagement.dto.base.ResponseGeneral;
import com.learn.SpendingManagement.dto.request.user.UserRequest;
import com.learn.SpendingManagement.dto.response.User.UserResponse;
import com.learn.SpendingManagement.facade.UserFacadeService;
import com.learn.SpendingManagement.service.MessageService;
import com.learn.SpendingManagement.service.user.UserService;
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
@RequestMapping("api/v1/users")
@RequiredArgsConstructor

public class UserController {
  private final UserFacadeService userFacadeService;
  private final UserService userService;
  private final MessageService messageService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseGeneral<UserResponse> create(
        @Valid @RequestBody UserRequest request,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(create)request: {}", request);

    return ResponseGeneral.ofCreated(
          messageService.getMessage(CREATE_USER, language),
          userFacadeService.create(request)
    );
  }

  @PutMapping("{id}")
  public ResponseGeneral<UserResponse> update(
        @Valid @RequestBody UserRequest request,
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(update)request: {},{}", request, id);

    return ResponseGeneral.ofSuccess(
          messageService.getMessage(UPDATE_USER, language),
          userFacadeService.update(request, id)
    );
  }

  @DeleteMapping("{id}")
  public ResponseGeneral<Void> delete(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(delete) request:{}", id);
    userService.delete(id);
    return ResponseGeneral.ofSuccess(
          messageService.getMessage(DELETE_USER, language)
    );
  }

  @GetMapping
  public PageResponseGeneral<PageResponse<UserResponse>> list(
        @RequestParam(name = "keyword", required = false) String keyword,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "all", defaultValue = "false", required = false) boolean isAll,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
    return PageResponseGeneral.ofSuccess(messageService.getMessage(LIST_USER, language),
          userService.list(keyword, size, page, isAll)
    );
  }

  @GetMapping("{id}")
  public ResponseGeneral<UserResponse> detail(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(detail) request: {}", id);

    return ResponseGeneral.ofCreated(
          messageService.getMessage(DETAIL_USER, language),
          userService.detail(id)
    );
  }
}