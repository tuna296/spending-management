package com.learn.SpendingManagement.dto.response.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressResponse {
  private String id;
  private String province;
  private String district;
  private String ward;
}

