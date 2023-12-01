package com.learn.SpendingManagement.controller.spendingmanagement;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.base.PageResponseGeneral;
import com.learn.SpendingManagement.dto.base.ResponseGeneral;
import com.learn.SpendingManagement.dto.request.spendingmanagement.TransactionRequest;
import com.learn.SpendingManagement.dto.response.spendingmanagement.TransactionResponse;
import com.learn.SpendingManagement.service.MessageService;
import com.learn.SpendingManagement.service.spendingmanagement.TransactionService;
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
@RequestMapping("api/v1/transactions")
public class TransactionController {
  private final TransactionService transactionService;
  private final MessageService messageService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseGeneral<TransactionResponse> create(
        @Valid @RequestBody TransactionRequest request,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(create)request: {}", request);

    return ResponseGeneral.ofCreated(
          messageService.getMessage(CREATE_TRANSACTION, language),
          transactionService.create(request)
    );
  }

  @PutMapping("{id}")
  public ResponseGeneral<TransactionResponse> update(
        @Valid @RequestBody TransactionRequest request,
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(update)request: {}", request);

    return ResponseGeneral.ofSuccess(
          messageService.getMessage(UPDATE_TRANSACTION, language),
          transactionService.update(request, id)
    );
  }

  @DeleteMapping("{id}")
  public ResponseGeneral<Void> delete(
        @PathVariable String id,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(create)request: {}", id);
    transactionService.delete(id);
    return ResponseGeneral.ofSuccess(
          messageService.getMessage(DELETE_TRANSACTION, language));
  }

  @GetMapping("{tagId}")
  public PageResponseGeneral<PageResponse<TransactionResponse>> list(
        @RequestParam(name = "keyword", required = false) String keyword,
        @RequestParam(name = "size", defaultValue = "10") int size,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "all", defaultValue = "false", required = false) boolean isAll,
        @PathVariable String tagId,
        @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
  ) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}, tagId: {}", keyword, size, page, isAll, tagId);
    return PageResponseGeneral.ofSuccess(messageService.getMessage(LIST_TRANSACTION, language),
          transactionService.list(keyword, size, page, isAll, tagId)
    );
  }

}