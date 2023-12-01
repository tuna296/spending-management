package com.learn.SpendingManagement.controller.spendingmanagement;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.base.PageResponseGeneral;
import com.learn.SpendingManagement.dto.base.ResponseGeneral;
import com.learn.SpendingManagement.dto.request.spendingmanagement.TagFinanceRequest;
import com.learn.SpendingManagement.dto.response.spendingmanagement.TagFinanceResponse;
import com.learn.SpendingManagement.facade.TagFinanceFacadeService;
import com.learn.SpendingManagement.service.MessageService;
import com.learn.SpendingManagement.service.spendingmanagement.TagFinanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.learn.SpendingManagement.constant.Constants.CommonConstants.DEFAULT_LANGUAGE;
import static com.learn.SpendingManagement.constant.Constants.CommonConstants.LANGUAGE;
import static com.learn.SpendingManagement.constant.Constants.MessageCode.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/tag-finances")
public class TagFinanceController {
  private final TagFinanceFacadeService tagFinanceFacadeService;
  private final TagFinanceService tagFinanceService;
  private final MessageService messageService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseGeneral<TagFinanceResponse> create(
        @Valid @RequestBody TagFinanceRequest request,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(create)request: {}", request);

    return ResponseGeneral.ofCreated(
          messageService.getMessage(CREATE_TAG_FINANCE, language),
          tagFinanceFacadeService.create(request)
    );
  }

  @PutMapping("{id}")
  public ResponseGeneral<TagFinanceResponse> update(
        @Valid @RequestBody TagFinanceRequest request,
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(update)request: {}", request);

    return ResponseGeneral.ofSuccess(
          messageService.getMessage(UPDATE_TAG_FINANCE, language),
          tagFinanceService.update(request, id)
    );
  }

  @DeleteMapping("{id}")
  public ResponseGeneral<Void> delete(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(create)request: {}", id);
    tagFinanceService.delete(id);
    return ResponseGeneral.ofSuccess(
          messageService.getMessage(DELETE_TAG_FINANCE, language));
  }

  @GetMapping("{id}")
  public PageResponseGeneral<PageResponse<TagFinanceResponse>> list(
        @RequestParam(name = "keyword", required = false) String keyword,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "all", defaultValue = "false", required = false) boolean isAll,
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}, username: {}", keyword, size, page, isAll, id);
    return PageResponseGeneral.ofSuccess(messageService.getMessage(LIST_TAG_FINANCE, language),
          tagFinanceService.list(keyword, size, page, isAll, id)
    );
  }
  @GetMapping("/details/{id}")
  public ResponseGeneral<TagFinanceResponse> detail(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(detail) request: {}", id);

    return ResponseGeneral.ofSuccess(
          messageService.getMessage(LIST_TAG_FINANCE, language),
          tagFinanceFacadeService.detail(id)
    );
  }
}
