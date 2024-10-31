package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true; // return true to continue with the request processing, or false to stop it
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
    }
    
//    preHandle: This method is called before the actual handler method is invoked. You can perform any pre-processing logic here, 
//    such as authentication or authorization checks. Returning true indicates that the request processing should continue, 
//    while returning false indicates that the request should be stopped.

//    postHandle: This method is called after the handler method is invoked but before the view is rendered. 
//    You can perform any post-processing logic here, such as modifying the model or view.

//    afterCompletion: This method is called after the request has been completed, including rendering the view.
//    You can perform any cleanup logic here.
}
