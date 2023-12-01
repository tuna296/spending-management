package com.learn.SpendingManagement.entity.spendingmanagement;

import com.learn.SpendingManagement.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tag_finances")
public class TagFinance extends BaseEntity {
  @Column(name = "user_id")
  private String userId;
  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "is_deleted")
  private boolean isDeleted;

  public TagFinance(String userId, String name, String description) {
    this.userId = userId;
    this.name = name;
    this.description = description;
  }
}
