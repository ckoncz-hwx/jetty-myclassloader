
package com.ck.webapp1;

import java.io.IOException;
import java.time.ZonedDateTime;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.App;

public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().println("<h1>Hello from HelloServlet " + ZonedDateTime.now() + "</h1>");
		response.getWriter().println("<h2>getClass().getClassLoader().getClass().getName() is</h2>");
		String clName = getClass().getClassLoader().getClass().getName();
		response.getWriter().println("<h2>" + clName + "</h2>");
		response.getWriter().println("<h2>" + App.getUrl() + "</h2>");
	}
}