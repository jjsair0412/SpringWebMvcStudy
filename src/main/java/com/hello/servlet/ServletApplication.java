package com.hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @ServletComponentScan은
 컴포넌트스캔으로 서블릿을 찾아서 자동으로 실행할 수 있도록 도와준다.
  서블릿을 자동으로 등록해준다.
 **/
@ServletComponentScan
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

}
