package com.project2.database.interfaces;

import com.project2.dao.User;

public interface UserDaoInterface {
public User getUser(String username, String password);
public void insertUser(User user);
public User getUserID(String username);


}
