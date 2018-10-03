package com.techmojo.hackathon.getbetter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@RequestMapping(method = RequestMethod.OPTIONS,name = "login")
	public ResponseEntity<Object> login() {
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
