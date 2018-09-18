package com.sofa.springbootsamplesi18n.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
//只能返回字符串，重定向时，不能直接，return "redirect:/index"
@RestController
public class WelcomeController {
	AcceptHeaderLocaleResolver a;
	
	@Autowired
	private MessageSource messageSource;
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView gotoWelcomePage(HttpServletRequest request){
		ModelAndView mv= new ModelAndView();
		mv.setViewName("index");
		Locale locale = LocaleContextHolder.getLocale();
		String msg = messageSource.getMessage("index.welcome", null,locale);
		System.out.println(request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
		System.out.println(msg);
		return mv;
	}
//	@RequestMapping(value="/changeSessionLanauage",method=RequestMethod.GET)
//	public void changeSessionLanauage(HttpServletRequest request,HttpServletResponse response,String lang){
//		if("zh".equals(lang)){
//			//代码中即可通过以下方法进行语言设置
//			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("zh", "CN")); 
//		}else if("en".equals(lang)){
//			//代码中即可通过以下方法进行语言设置
//			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("en", "US")); 
//		}
//		//@RestController 只能返回字符串，重定向时，不能直接，return "redirect:/index"
////		return "redirct:/index";
//		try {
//			response.sendRedirect("/index");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	@RequestMapping("/changeSessionLanauage")
    public void changeSessionLanauage(HttpServletRequest request,HttpServletResponse response,String lang){
              System.out.println(lang);
       LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
       if("zh".equals(lang)){
           localeResolver.setLocale(request, response, new Locale("zh", "CN"));
       }else if("en".equals(lang)){
           localeResolver.setLocale(request, response, new Locale("en", "US"));
       }
       try {
    	   response.sendRedirect("/index");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@Bean
    public LocaleResolver localeResolver() {
		MyAcceptHeaderLocalResolver resolver = new MyAcceptHeaderLocalResolver();
		resolver.setDefaultLocale(Locale.CHINA);
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        //设置默认区域,
//        slr.setDefaultLocale(Locale.CHINA);
        return resolver;
    }
}
