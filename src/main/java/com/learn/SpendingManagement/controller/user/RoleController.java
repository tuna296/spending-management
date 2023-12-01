package com.learn.SpendingManagement.controller.user;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.base.PageResponseGeneral;
import com.learn.SpendingManagement.dto.base.ResponseGeneral;
import com.learn.SpendingManagement.dto.request.user.RoleRequest;
import com.learn.SpendingManagement.dto.response.User.RoleResponse;
import com.learn.SpendingManagement.service.MessageService;
import com.learn.SpendingManagement.service.user.RoleService;
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
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
  private final RoleService roleService;
  private final MessageService messageService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseGeneral<RoleResponse> create(
        @Valid @RequestBody RoleRequest request,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    return ResponseGeneral.ofCreated(
          messageService.getMessage(CREATE_ROLE, language),
          roleService.create(request)
    );
  }

  @PutMapping("{id}")
  public ResponseGeneral<RoleResponse> update(
        @Valid @RequestBody RoleRequest request,
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    return ResponseGeneral.ofSuccess(
          messageService.getMessage(UPDATE_ROLE, language),
          roleService.update(request, id)
    );
  }

  @DeleteMapping("{id}")
  public ResponseGeneral<RoleResponse> delete(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    roleService.delete(id);
    return ResponseGeneral.ofSuccess(messageService.getMessage(DELETE_ROLE, language));
  }

  @GetMapping
  public PageResponseGeneral<PageResponse<RoleResponse>> list(
        @RequestParam(name = "keyword", required = false) String keyword,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "all", defaultValue = "false", required = false) boolean isAll,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
    return PageResponseGeneral.ofSuccess(messageService.getMessage(LIST_ROLE, language),
          roleService.list(keyword, size, page, isAll)
    );
  }
  @GetMapping("{id}")
  public ResponseGeneral<RoleResponse> detail(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    return ResponseGeneral.ofSuccess(
          messageService.getMessage(DETAIL_ROLE, language),
          roleService.detail(id)
    );
  }

}
