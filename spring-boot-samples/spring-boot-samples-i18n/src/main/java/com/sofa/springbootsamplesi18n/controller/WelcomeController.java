package com.sofa.springbootsamplesi18n.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WelcomeController {
	
	@Autowired
	private MessageSource messageSource;
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView gotoWelcomePage(){
		ModelAndView mv= new ModelAndView();
		mv.setViewName("index");
		Locale locale = LocaleContextHolder.getLocale();
		String msg = messageSource.getMessage("index.welcome", null,locale);
		System.out.println(msg);
		return mv;
	}
}
