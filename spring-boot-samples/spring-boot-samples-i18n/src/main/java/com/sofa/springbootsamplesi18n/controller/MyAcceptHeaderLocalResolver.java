package com.sofa.springbootsamplesi18n.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

public class MyAcceptHeaderLocalResolver extends AcceptHeaderLocaleResolver{
	private Locale myLocale;
	@Override
	public Locale resolveLocale(HttpServletRequest request){
		return myLocale == null?request.getLocale():myLocale;
	}
	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		this.myLocale = locale;
	}
	public Locale getLocale(){
		return myLocale;
	}

}
