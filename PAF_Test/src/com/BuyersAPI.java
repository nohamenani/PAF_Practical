package com;

import com.Buyer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class BuyersAPI
 */

@WebServlet("/BuyersAPI")
public class BuyersAPI extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	Buyer Obj = new Buyer();

	
	public BuyersAPI() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
				String output = Obj.insertBuyers(
				request.getParameter("fname"),
				request.getParameter("lname"), 
				request.getParameter("pnumber"), 
				request.getParameter("email"),
				request.getParameter("password"));

		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		Map paras = getParasMap(request); 
		 String output = Obj.updateBuyers(paras.get("hididSave").toString(), 
		 paras.get("fname").toString(), 
		 paras.get("lname").toString(), 
		 paras.get("pnumber").toString(), 
		 paras.get("email").toString(),
		 paras.get("password").toString()); 
		 
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		 Map paras = getParasMap(request); 
		 String output = Obj.deleteBuyers(paras.get("bId").toString()); 
		 response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		 Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
		 String queryString = scanner.hasNext() ? 
		 scanner.useDelimiter("\\A").next() : ""; 
		 
		 scanner.close(); 
		 String[] params = queryString.split("&"); 
		 
		 for (String param : params) 
			 {
				 String[] p = param.split("=");
				 map.put(p[0], p[1]); 
			 } 
		 } 
			catch (Exception e) 
		 { 
		 } 
		return map; 
	}

}
