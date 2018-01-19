package com.gcu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.gcu.service.IUserService;
import com.gcu.service.UserService;
import com.gcu.controller.CompanyLoginController;
import com.gcu.controller.LoginController;
import com.gcu.controller.RegisterController;
import com.gcu.data.CompanyDAO;
import com.gcu.data.ProductDAO;
import com.gcu.data.ICompanyDAO;
import com.gcu.data.IProductDAO;
import com.gcu.data.IUserDAO;
import com.gcu.data.UserDAO;
import com.gcu.service.CompanyService;
import com.gcu.service.ICompanyService;
import com.gcu.service.IProductService;
import com.gcu.service.ProductService;

@Configuration
public class ApplicationConfig {
	/* Controllers */
	
	@Bean(name="loginController")
	public LoginController getLoginController() {
		return new LoginController();
	}
	
	@Bean(name="registerController")
	public RegisterController getRegisterController() {
		return new RegisterController();
	}
	
	@Bean(name="companyLoginController")
	public CompanyLoginController getCompanyLoginController() {
		return new CompanyLoginController();
	}
	
	/* Services */
	
	@Bean(name="userService")
	@Scope(value="singleton", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public IUserService getUserService() {
		return new UserService();
	}
	
	@Bean(name="productService")
	@Scope(value="singleton", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public IProductService getProductService() {
		return new ProductService();
	}
	
	@Bean(name="companyService")
	@Scope(value="singleton", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public ICompanyService getCompanyService() {
		return new CompanyService();
	}
	
	/* DAO */
	
	@Bean(name="userDAO")
	@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public IUserDAO getUserDAO() {
		return new UserDAO();
	}

	@Bean(name="productDAO")
	@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public IProductDAO getProductDAO() {
		return new ProductDAO();
	}
	
	@Bean(name="companyDAO")
	@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public ICompanyDAO getCompanyDAO() {
		return new CompanyDAO();
	}
}
