package com.learn.SpendingManagement.constant;

public class Constants {
  public static class CommonConstants {
    public static final String ENCODING_UTF_8 = "UTF-8";
    public static final String LANGUAGE = "Accept-Language";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String MESSAGE_SOURCE = "classpath:i18n/messages";
  }

  public static class AuditorConstant {
    public static final String ANONYMOUS = "anonymousUser";
    public static final String SYSTEM = "SYSTEM";
  }

  public static class StatusException {
    public static final Integer NOT_FOUND = 404;
    public static final Integer CONFLICT = 409;
    public static final Integer BAD_REQUEST = 400;
  }

  public static class MessageException {

  }

  public static class AuthConstant {
    public static String TYPE_TOKEN = "Bear ";
    public static String AUTHORIZATION = "Authorization";
  }

  public static class MessageCode {
    public static final String CREATE_ACCOUNT="com.learn.SpendingManagement.controller.account.create";
    public static final String UPDATE_ACCOUNT="com.learn.SpendingManagement.controller.account.update";
    public static final String DELETE_ACCOUNT="com.learn.SpendingManagement.controller.account.delete";
    public static final String LOGIN_SUCCESS="com.learn.SpendingManagement.controller.account.login";
    public static final String LIST="com.learn.SpendingManagement.controller.account.list";
    public static final String ACTIVATE="com.learn.SpendingManagement.controller.account.activate";
    public static final String DEACTIVATE="com.learn.SpendingManagement.controller.account.deactivate";
    public static final String CREATE_ROLE="com.learn.SpendingManagement.controller.role.create";
    public static final String UPDATE_ROLE="com.learn.SpendingManagement.controller.role.update";
    public static final String LIST_ROLE="com.learn.SpendingManagement.controller.role.list";
    public static final String DETAIL_ROLE="com.learn.SpendingManagement.controller.role.detail";
    public static final String DELETE_ROLE="com.learn.SpendingManagement.controller.role.delete";
    public static final String CREATE_USER="com.learn.SpendingManagement.controller.user.create";
    public static final String UPDATE_USER="com.learn.SpendingManagement.controller.user.update";
    public static final String DELETE_USER="com.learn.SpendingManagement.controller.user.delete";
    public static final String LIST_USER="com.learn.SpendingManagement.controller.user.list";
    public static final String DETAIL_USER="com.learn.SpendingManagement.controller.user.detail";
    public static final String CREATE_TAG_FINANCE="com.learn.SpendingManagement.controller.spendingmanagement.TagFinanceController.create";
    public static final String CREATE_TRANSACTION="com.learn.SpendingManagement.controller.spendingmanagement.TransactionController.create";
    public static final String UPDATE_TAG_FINANCE="com.learn.SpendingManagement.controller.spendingmanagement.TagFinanceController.update";
    public static final String LIST_TAG_FINANCE="com.learn.SpendingManagement.controller.spendingmanagement.TagFinanceController.list";
    public static final String DELETE_TAG_FINANCE="com.learn.SpendingManagement.controller.spendingmanagement.TagFinanceController.delete";
    public static final String UPDATE_TRANSACTION="com.learn.SpendingManagement.controller.spendingmanagement.TransactionController.update";
    public static final String DELETE_TRANSACTION="com.learn.SpendingManagement.controller.spendingmanagement.TransactionController.delete";
    public static final String LIST_TRANSACTION="com.learn.SpendingManagement.controller.spendingmanagement.TransactionController.list";




  }

  public static final class MessagesResponse {
    public static final String INVALID_EMAIL = "com.learn.SpendingManagement.anotation.ValidateEmail";

    public static final String INVALID_PHONE_NUMBER = "com.learn.SpendingManagement.anotation.ValidatePhone";

  }
}
