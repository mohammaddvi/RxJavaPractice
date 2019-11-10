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

    public static List<User> convertApiUserToUser(List<ApiUser> apiUsers) {
        List<User> users = new ArrayList<>();
        for (ApiUser apiUser : apiUsers) {
            User user = new User();
            user.firstname = apiUser.firstname;
            user.lastname = apiUser.lastname;
            users.add(user);
        }
        return users;
    }

    public static List<User> javaFanUserList() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.id = 1;
        user.firstname = "mohammad";
        user.lastname = "davari";
        users.add(user);

        User user1 = new User();
        user1.id = 2;
        user1.firstname = "mohammad";
        user1.lastname = "amin";
        users.add(user1);
        return users;
    }

    public static List<User> kotlinFanOUserList() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.id = 1;
        user.firstname = "mohammad";
        user.lastname = "davari";
        users.add(user);

        User user1 = new User();
        user1.id = 3;
        user1.firstname = "azad";
        user1.lastname = "mohammadi";
        users.add(user1);
        return users;
    }

    public static List<User> filterUserLoveKotlinAndJava(List<User> javaFan, List<User> kotlinFan) {
        List<User> bothLove = new ArrayList<>();
        for (User user : javaFan)
            for (User user1 : kotlinFan)
                if (user.id == user1.id)
                    bothLove.add(user);
        return bothLove;
    }
}
