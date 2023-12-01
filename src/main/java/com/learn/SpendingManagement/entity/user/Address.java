package com.learn.SpendingManagement.entity.user;

import com.learn.SpendingManagement.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "addresses")
public class Address extends BaseEntity {
  @Column(name = "province")
  private String province;
  @Column(name = "district")
  private String district;
  @Column(name = "ward")
  private String ward;
  @Column(name = "is_deleted")
  private boolean isDeleted;

  public Address(String province, String district, String ward) {
    this.province = province;
    this.district = district;
    this.ward = ward;
    isDeleted = false;
  }
}
