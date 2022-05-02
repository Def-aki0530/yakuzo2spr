package com.example.yakuzo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.yakuzo2.data.MenuData;
import com.example.yakuzo2.repository.MenuRepository;

@Controller
public class MenuController {

	@Autowired
	MenuRepository mr;
	@GetMapping("/menu")
	public String index(@ModelAttribute("md") MenuData md,Model mode) {
		mr.makeMenuList(md);

		return "menu";

	}
}
