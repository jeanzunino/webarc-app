package com.webarc.app.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webarc.app.model.User;
import com.webarc.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

	private static final Logger log = LoggerFactory.getLogger(UserRestController.class);
	private static final String DEFAULT_PAGE_NUM = "0";
	private static final String DEFAULT_PAGE_SIZE = "100";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getByEmail", method = GET)
	public Page<User> getByEmail(@RequestParam(value = "email", required = false) final String email,
			@RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUM) final Integer page,
			@RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) final Integer size) {
		return userService.getAllFromEmail(email, page, size);
	}

	@RequestMapping(method = GET)
	public Page<User>  page(
			@RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUM) final Integer page,
			@RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) final Integer size) {
		return userService.getAll(page, size);
	}

	@RequestMapping(method = POST)
	void save(@RequestBody final User reading) {
		userService.save(reading);
	}
	
	@RequestMapping(method = PUT)
	void update(@RequestBody final User reading) {
		userService.save(reading);
	}
}
