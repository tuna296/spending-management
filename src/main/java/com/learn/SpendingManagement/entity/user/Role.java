package com.learn.SpendingManagement.entity.user;

import com.learn.SpendingManagement.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role extends BaseEntity {
  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "is_deleted")
  private boolean isDeleted;

  public Role(String name, String description) {
    this.name = name;
    this.description = description;
    isDeleted = false;
  }
}
