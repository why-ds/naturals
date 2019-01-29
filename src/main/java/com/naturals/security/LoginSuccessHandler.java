package com.naturals.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.java.Log;

@Log
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {

		log.info("--------------determineTargetUrl-------------------");

		Object dest = request.getSession().getAttribute("dest");
		String nextURL = null;
		if (dest != null) {
			log.info("dest != null");
			request.getSession().removeAttribute("dest");
			nextURL = (String) dest;
		} else {
			log.info("dest != null else");
			nextURL = super.determineTargetUrl(request, response);
		}
		
//		임시입니다 윤동섭 임시입니다. 190129
		nextURL = "/ta/list";
		
		log.info("-------------------"+nextURL+"-------------------");
		
		return nextURL;
	}	
}