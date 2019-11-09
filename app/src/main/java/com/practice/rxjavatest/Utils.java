package com.practice.rxjavatest;

import com.practice.rxjavatest.Model.ApiUser;
import com.practice.rxjavatest.Model.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<ApiUser> getApiUserList() {
        List<ApiUser> apiUserList = new ArrayList<>();
        ApiUser apiUser0 = new ApiUser();
        apiUser0.firstname = "mohammad";
        apiUser0.lastname = "davari";
        apiUserList.add(apiUser0);

        ApiUser apiUser1 = new ApiUser();
        apiUser1.firstname = "hossein";
        apiUser1.lastname = "rouhi";
        apiUserList.add(apiUser1);

        ApiUser apiUser2 = new ApiUser();
        apiUser2.firstname = "mohammad";
        apiUser2.lastname = "amin";
        apiUserList.add(apiUser2);
        return apiUserList;
    }
    public static List<User> convertApiUserToUser(List<ApiUser> apiUsers){
        List<User> users = new ArrayList<>();
        for (ApiUser apiUser :apiUsers){
            User user = new User();
            user.firstname = apiUser.firstname;
            user.lastname = apiUser.lastname;
            users.add(user);
        }
        return users;
    }
}
