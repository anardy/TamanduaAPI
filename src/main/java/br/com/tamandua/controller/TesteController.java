package br.com.tamandua.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/oiMundo")
public class TesteController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("akaka");
		RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/WEB-INF/view/electric.jsp");
        dispatcher.forward(request, response);
	}
}
