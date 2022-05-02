package com.example.yakuzo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yakuzo2.data.ShainData;
import com.example.yakuzo2.repository.ShainRepository;

@RestController
public class ShainRestController {

	@Autowired
	ShainRepository sr;

	@PostMapping("/shaingetpages")
	public int getPages(@ModelAttribute("sd") ShainData sd,Model model) {
		return sr.getPages(sd);
	}
}
