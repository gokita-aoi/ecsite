package com.internous.ecsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.internous.ecsite.model.dao.UserRepository;
import com.internous.ecsite.model.entity.User;
import com.internous.ecsite.model.form.UserForm;

@Controller
@RequestMapping("/ecsite/admin/user")
public class UserController {
	@Autowired
	private UserRepository userRepos;
	
	/*
	 *adminindex.htmlの新規登録ボタンが押されたら、register.htmlに飛ぶ
	 */
	@RequestMapping("/register")
	private String userIndex() {
		return "register";
	}
	
	/*
	 * cheakUserNameボタンが押されたら、scriptに行き、/dulicatedUserNameにて処理される
	 */
	@RequestMapping("/duplicatedUserName")
	@ResponseBody
	public boolean duplicatedUserName(@RequestBody UserForm f) {
		
		int count = userRepos.findCountByUserName(f.getUserName());
		return count > 0;
	}
	
	/*
	 * register.htmlの登録ボタンが押されたら、/addUserにrequestMappingされ、adminindex.htmlに飛ぶ
	 */
	@RequestMapping("/addUser")
	public String addUserApi(UserForm f) {
		User user = new User();
		user.setUserName(f.getUserName());
		user.setPassword(f.getPassword());
		user.setFullName(f.getFullName());
		userRepos.saveAndFlush(user);
		
		return "adminindex";
	}
	
	
}
