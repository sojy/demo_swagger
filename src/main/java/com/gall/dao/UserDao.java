package com.gall.dao;

import com.gall.model.LoginUser;

public interface UserDao {

	public LoginUser getGetUserDetails(String usrname);

}
