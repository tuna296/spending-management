package com.learn.SpendingManagement.controller.user;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.base.PageResponseGeneral;
import com.learn.SpendingManagement.dto.base.ResponseGeneral;
import com.learn.SpendingManagement.dto.request.user.AddressRequest;
import com.learn.SpendingManagement.dto.response.User.AddressResponse;
import com.learn.SpendingManagement.service.MessageService;
import com.learn.SpendingManagement.service.user.AddressService;
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
@RequestMapping("api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {
  private final AddressService addressService;
  private final MessageService messageService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseGeneral<AddressResponse> create(
        @Valid @RequestBody AddressRequest request,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(create)request: {}", request);

    return ResponseGeneral.ofCreated(
          messageService.getMessage(CREATE_ACCOUNT, language),
          addressService.create(request)
    );
  }

  @PutMapping("{id}")
  public ResponseGeneral<AddressResponse> update(
        @Valid @RequestBody AddressRequest request,
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(update)request: {}", request);

    return ResponseGeneral.ofSuccess(
          messageService.getMessage(UPDATE_ACCOUNT, language),
          addressService.update(request, id)
    );
  }

  @DeleteMapping("{id}")
  public ResponseGeneral<Void> delete(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(delete)request: {}", id);

    addressService.delete(id);
    return ResponseGeneral.ofSuccess(
          messageService.getMessage(DELETE_ACCOUNT, language)
    );
  }

  @GetMapping
  public PageResponseGeneral<PageResponse<AddressResponse>> list(
        @RequestParam(name = "keyword", required = false) String keyword,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "all", defaultValue = "false", required = false) boolean isAll,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
    return PageResponseGeneral.ofSuccess(messageService.getMessage(LIST, language),
          addressService.list(keyword, size, page, isAll)
    );
  }

}
