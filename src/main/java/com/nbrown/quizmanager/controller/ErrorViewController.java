package com.nbrown.quizmanager.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorViewController implements ErrorController {
	
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    	
    	if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
         
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
            	model.addAttribute("errorMessage", "Resource Not Found");
                model.addAttribute("statusCode", "404");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            	model.addAttribute("errorMessage", "Internal Server Error");
            	model.addAttribute("statusCode", "500");
            }
        }
    	else {
    		model.addAttribute("errorMessage", "Something Went Wrong");
    	}
        return "error";
    }

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
