package com.example.projectmilkshop.Api;

public class AccountRepository {
    public static AccountService getAccountService(){
        return APIClient.getClient().create(AccountService.class);
    }
}
