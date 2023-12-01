package com.learn.SpendingManagement.service.impl.user;

import com.learn.SpendingManagement.dto.base.PageResponse;
import com.learn.SpendingManagement.dto.request.user.RoleRequest;
import com.learn.SpendingManagement.dto.response.User.RoleResponse;
import com.learn.SpendingManagement.entity.user.Role;
import com.learn.SpendingManagement.exception.user.RoleAlreadyExistException;
import com.learn.SpendingManagement.repository.user.RoleRepository;
import com.learn.SpendingManagement.service.user.RoleService;
import com.learn.SpendingManagement.exception.user.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
  private final RoleRepository repository;

  @Override
  @Transactional
  public RoleResponse create(RoleRequest request) {
    log.info("(create) request: {}", request);
    this.checkExist(request.getName());
    Role role = new Role(
          request.getName(),
          request.getDescription()
    );
    repository.save(role);
    return new RoleResponse(role.getId(), role.getName(), role.getDescription());
  }


  @Override
  @Transactional
  public RoleResponse update(RoleRequest request, String id) {
    log.info("(update) request: {}", request);
    Role update = find(id);
    this.checkNameForUpdate(update.getName(), request.getName());
    log.debug("check name of role already exists when update");
    setValueUpdate(update, request);
    repository.save(update);
    return new RoleResponse(update.getId(), update.getName(), update.getDescription());
  }

  private void setValueUpdate(Role role, RoleRequest request) {
    log.info("(setValueUpdate)");
    role.setName(request.getName());
    role.setDescription(request.getDescription());
  }

  @Override
  @Transactional
  public void delete(String id) {
    log.info("(delete) id: {}", id);
    this.find(id);
    repository.deleteRole(id);
  }

  @Override
  public PageResponse<RoleResponse> list(String keyword, int size, int page, boolean isAll) {
    log.info("(list) keyword: {}, size : {}, page: {}, isAll: {}", keyword, size, page, isAll);
    Page<RoleResponse> responses = isAll ?
          repository.findAllRole(PageRequest.of(page, size)) : repository.search(PageRequest.of(page, size), keyword);
    return PageResponse.of(responses.getContent(), responses.getNumberOfElements());

  }

  @Override
  public RoleResponse detail(String id) {
    this.find(id);
    return repository.detail(id);
  }

  private void checkExist(String name) {
    log.debug("checkExist() {}", name);
    repository.checkExist(name);
    if (repository.checkExist(name)) {
      log.error("checkExist:{}", name);
      throw new RoleAlreadyExistException();
    }
  }

  private Role find(String id) {
    log.debug("findById() {}", id);
    Role role = repository.findById(id).orElseThrow(RoleNotFoundException::new);
    if (role.isDeleted()) {
      throw new RoleNotFoundException();
    }
    return role;
  }

  private void checkNameForUpdate(String nameRole, String nameRoleRequest) {
    log.debug("checkNameForUpdate() {} {}", nameRole, nameRoleRequest);
    if (!nameRole.equals(nameRoleRequest)) {
      this.checkExist(nameRoleRequest);
    }
  }
}
