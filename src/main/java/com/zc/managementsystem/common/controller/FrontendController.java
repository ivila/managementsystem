package com.zc.managementsystem.common.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FrontendController {
    @Autowired
    ResourceLoader resourceLoader;

	@GetMapping("/")
    @ResponseBody
	public Resource home() {
        return resourceLoader.getResource("classpath:/static/dist/index.html");
	}


	@GetMapping("/index")
    @ResponseBody
	public Resource indexReturn() {
        return resourceLoader.getResource("classpath:/static/dist/index.html");
	}

	@GetMapping("/favicon.ico")
    @ResponseBody
	public Resource ico() {
        return resourceLoader.getResource("classpath:/static/dist/favicon.ico");
	}

	@GetMapping("/css/{file}")
    @ResponseBody
	public Resource css(@PathVariable String file) {
        return resourceLoader.getResource("classpath:/static/dist/css/"+file);
	}

	@GetMapping("/js/{file}")
    @ResponseBody
	public Resource js(@PathVariable String file) {
        return resourceLoader.getResource("classpath:/static/dist/js/"+file);
	}
}
