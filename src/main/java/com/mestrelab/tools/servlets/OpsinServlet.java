package com.mestrelab.tools.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mestrelab.tools.OPSINFacade;
import com.mestrelab.tools.ResponseVO;

public class OpsinServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private OPSINFacade facade;
	private Gson gson = null;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		this.facade = new OPSINFacade();
		this.gson= new Gson();

	}

//	public String getSmiles(String mol) {
//		ResponseVO response = this.facade.getSmiles(mol);
//
//		return gson.toJson(response);
//	}
//	
//	public String getMolfile(String mol) {
//		ResponseVO response = this.facade.getMolfile(mol);
//
//		
//		return gson.toJson(response);
//	}
//	public String getCml(String mol) {
//		ResponseVO response = this.facade.getCml(mol);
//
//		
//		return gson.toJson(response);
//	}
//
//	public String getInChi(String mol) {
//		ResponseVO response = this.facade.getInChi(mol);
//
//		
//		return gson.toJson(response);
//	}
//
//	public String getStandardInChi(String mol) {
//		ResponseVO response = this.facade.getStandardInChi(mol);
//
//		
//		return gson.toJson(response);
//	}
//
//	public String toString() {
//		String cadea = null;
//		cadea = "</br>" + "Smiles: " + getSmiles("2,4,6-trinitrotoluene")
//				+ "</br></br>" + "Cml: "
//				+ StringEscapeUtils.escapeXml(getCml("2,4,6-trinitrotoluene"))
//				+ "</br></br>" + "InChi: " + getInChi("2,4,6-trinitrotoluene")
//				+ "</br></br>" + "Standar InChi: " + getStandardInChi("ethanol")
//				+ "</br></br>" + "Molfile: " + getMolfile("ethanol");
//
//		return cadea;
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (req.getParameter("callback") != null)
			resp.setContentType("text/javascript");
		else
			resp.setContentType("application/json");
		
		PrintWriter out = resp.getWriter();

		String nameStructure = req.getParameter("name");
		String format = (req.getParameter("format")!= null) ? req.getParameter("format") : "";
		
		List<ResponseVO> listResponse= new ArrayList<ResponseVO>();
		
		
		
		if ( "SMILES".equals(format.toUpperCase())) {
			//out.println(getSmiles(nameStructure));
			listResponse.add(this.facade.getSmiles(nameStructure));
		} else if ("CML".equals(format.toUpperCase())) {
			listResponse.add(this.facade.getCml(nameStructure));
		} else if ("INCHI".equals(format.toUpperCase())) {
			listResponse.add(this.facade.getInChi(nameStructure));
		} else if ("STANDARDINCHI".equals(format.toUpperCase())) {
			listResponse.add(this.facade.getStandardInChi(nameStructure));
		} else if ("MOLFILE".equals(format.toUpperCase())){
			listResponse.add(this.facade.getMolfile(nameStructure));	
		}else
			imprimirTodo(req, resp, listResponse);
		
		Type type = new TypeToken<List<ResponseVO>>(){}.getType();
		if (req.getParameter("callback") != null)
			out.println(""+req.getParameter("callback")+"(");
		out.println(gson.toJson(listResponse, type));
		if (req.getParameter("callback") != null)
			out.println(");");
		out.flush();
	}
	
	protected void imprimirTodo(HttpServletRequest req, HttpServletResponse resp, List<ResponseVO> listResponsePar)
			throws ServletException, IOException {

		String nameStructure = req.getParameter("name");		
		
		listResponsePar.add(this.facade.getSmiles(nameStructure));
		listResponsePar.add(this.facade.getCml(nameStructure));
		listResponsePar.add(this.facade.getInChi(nameStructure));
		listResponsePar.add(this.facade.getStandardInChi(nameStructure));
		listResponsePar.add(this.facade.getMolfile(nameStructure));
		
	}
	

}
