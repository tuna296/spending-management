package com.learn.SpendingManagement.service.impl.user;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.user.AddressRequest;
import com.learn.SpendingManagement.dto.response.User.AddressResponse;
import com.learn.SpendingManagement.entity.user.Address;
import com.learn.SpendingManagement.exception.user.AddressNotFoundException;
import com.learn.SpendingManagement.repository.user.AddressRepository;
import com.learn.SpendingManagement.service.user.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {
  private final AddressRepository repository;

  @Override
  @Transactional
  public AddressResponse create(AddressRequest request) {
    log.info("(request) create");
    Address address = new Address(
          request.getProvince(),
          request.getDistrict(),
          request.getWard()
    );
    repository.save(address);
    return new AddressResponse(
          address.getId(),
          address.getProvince(),
          address.getDistrict(),
          address.getWard()
    );
  }

  @Override
  @Transactional
  public AddressResponse update(AddressRequest request, String id) {
    log.info("(request) update id: {}", id);
    Address address = this.checkById(id);
    this.setValueForUpdate(address, request);
    repository.save(address);
    return new AddressResponse(
          address.getId(),
          address.getProvince(),
          address.getDistrict(),
          address.getWard()
    );
  }

  private void setValueForUpdate(Address address, AddressRequest request) {
    address.setProvince(request.getProvince());
    address.setDistrict(request.getDistrict());
    address.setWard(request.getWard());
  }

  private Address checkById(String id) {
    log.debug("(checkById) id: {}", id);
    Address address = repository.findById(id).orElseThrow(AddressNotFoundException::new);
    if (address.isDeleted()) {
      log.error("(AddressNotFoundException) id: {}", id);
      throw new AddressNotFoundException();
    }
    return address;
  }

  @Override
  @Transactional
  public void delete(String id) {
    log.debug("(checkById) id: {}", id);
    this.checkById(id);
    repository.delete(id);
  }

  @Override
  public PageResponse<AddressResponse> list(String keyword, int size, int page, boolean isAll) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
    Page<AddressResponse> responses = isAll ?
          repository.finAllAddress(PageRequest.of(page, size))
          : repository.search(PageRequest.of(page, size), keyword);
    return PageResponse.of(responses.getContent(), responses.getNumberOfElements());
  }

  @Override
  public AddressResponse detail(String id) {
    log.info("detail) request: {}", id);
    Address address = this.checkById(id);
    return new AddressResponse(
          address.getId(),
          address.getProvince(),
          address.getDistrict(),
          address.getWard()
    );
  }
}
