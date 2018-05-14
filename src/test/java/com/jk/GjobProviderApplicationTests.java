package com.jk;

import com.jk.service.IUserService;
import com.jk.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GjobProviderApplicationTests {
	@Autowired
	private IUserService userService;
	@Test
	public void contextLoads() {
		List<User> list=userService.getUserList();
		System.out.println(list);
	}

}
