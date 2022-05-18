package com.example.yakuzo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yakuzo2.data.HatchuData;
import com.example.yakuzo2.repository.HatchuRepository;

@RestController
public class HatchuRestController {

	@Autowired
	HatchuRepository hr;

	@PostMapping("/hatchugetpages")
	public int getPages(HatchuData hd,Model model) {
		//System.out.println(hd);
		return hr.getPages(hd);
	}

	@PostMapping("/t-sansho-getpage")
	public int t_sansho_getPages(HatchuData hd,Model model){
		//System.out.println("取引先参照：" + model.toString());
		return hr.t_sansho_getPages(hd);
	}

	@PostMapping("/y-sansho-getpage")
	public int y_sansho_getPages(HatchuData hd,Model model){
		//System.out.println("取引先参照：" + model.toString());
		return hr.y_sansho_getPages(hd);
	}
}
