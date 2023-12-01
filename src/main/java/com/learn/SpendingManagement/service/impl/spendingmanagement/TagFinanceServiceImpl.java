package com.learn.SpendingManagement.service.impl.spendingmanagement;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.spendingmanagement.TagFinanceRequest;
import com.learn.SpendingManagement.dto.response.spendingmanagement.TagFinanceResponse;
import com.learn.SpendingManagement.entity.spendingmanagement.TagFinance;
import com.learn.SpendingManagement.exception.spendingmanagement.TagFinanceAlreadyExistException;
import com.learn.SpendingManagement.exception.spendingmanagement.TagFinanceNotFoundException;
import com.learn.SpendingManagement.repository.spendingmanagement.TagFinanceRepository;
import com.learn.SpendingManagement.service.spendingmanagement.TagFinanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public class TagFinanceServiceImpl implements TagFinanceService {
  private final TagFinanceRepository repository;

  @Override
  @Transactional
  public TagFinanceResponse create(TagFinanceRequest request) {
    log.info("(request) create: {}", request);
    this.checkExistByName(request.getName());
    TagFinance tagFinance = new TagFinance(
          request.getUserId(),
          request.getName(),
          request.getDescription()
    );
    repository.save(tagFinance);
    return new TagFinanceResponse(
          tagFinance.getId(),
          tagFinance.getName(),
          tagFinance.getDescription()
    );
  }


  @Override
  public TagFinanceResponse update(TagFinanceRequest request, String id) {
    log.info("(request) update: {}", request);
    TagFinance tagFinance = this.checkExistById(id);
    this.checkForUpdate(request.getName(), id);
    this.setPropertyForUpdate(request, tagFinance);
    repository.saveAndFlush(tagFinance);
    return new TagFinanceResponse(
          tagFinance.getId(),
          tagFinance.getName(),
          tagFinance.getDescription()
    );
  }

  @Override
  public PageResponse<TagFinanceResponse> list(String keyword, int size, int page, boolean isAll, String id) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}, username: {}", keyword, size, page, isAll, id);
    Page<TagFinanceResponse> pageResponse = isAll ?
          repository.searchAll(PageRequest.of(page, size), id) :
          repository.searchByKey(PageRequest.of(page, size), keyword, id);
    return PageResponse.of(pageResponse.getContent(), pageResponse.getNumberOfElements());
  }

  @Override
  public void delete(String id) {
    log.info("(delete) create: {}", id);
    this.checkExistById(id);
    repository.delete(id);
  }

  @Override
  public TagFinanceResponse detail(String id) {
    TagFinance tagFinance = this.checkExistById(id);
    return new TagFinanceResponse(
          tagFinance.getId(),
          tagFinance.getName(),
          tagFinance.getDescription()
    );
  }

  private TagFinance checkExistById(String id) {
    log.debug("(checkExistById): {}", id);
    TagFinance tagFinance = repository.findById(id).orElseThrow(TagFinanceNotFoundException::new);
    if (tagFinance.isDeleted()) {
      log.error("Tag Finance Not Found");
      throw new TagFinanceNotFoundException();
    }
    return tagFinance;
  }

  private void checkExistByName(String name) {
    log.debug("checkExistByName: {}", name);

    if (repository.existByName(name)) {
      log.error("TagFinance Already Exist");
      throw new TagFinanceAlreadyExistException();
    }
  }

  private void checkForUpdate(String name, String id) {
    log.debug("(checkForUpdate): {}", name);
    TagFinance tagFinance = this.checkExistById(id);
    if (!name.equals(tagFinance.getName())) {
      this.checkExistByName(name);
    }
  }

  private void setPropertyForUpdate(TagFinanceRequest request, TagFinance tagFinance) {
    tagFinance.setName(request.getName());
    tagFinance.setDescription(request.getDescription());
  }
}
