package it.tirocirapid.filters;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import it.tirocirapid.classes.model.UserLoggato;

/**
 * Servlet Filter implementation class AreaVisitatoreFiltro
 */
//@WebFilter("/AreaVisitatoreFiltro")
public class AreaVisitatoreFiltro implements Filter {

    /**
     * Default constructor. 
     */
    public AreaVisitatoreFiltro() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session.getAttribute("user") != null)
		{
			UserLoggato user = (UserLoggato) session.getAttribute("user");
			HashMap<String, String> userTypes = (HashMap<String, String>) request.getServletContext().getAttribute("userTypes");
			String page = "/";
			if (user.getTipo().equals(userTypes.get("Stud")))
			{
				page = "/richieste"; //HomeStudente
			}
			else if (user.getTipo().equals(userTypes.get("Prof")))
			{
				page = "/richieste"; //HomeProfessore
			}
			else if (user.getTipo().equals(userTypes.get("RespAppr")))
			{
				page = "/richieste"; //HomeResponsabileApprovazioni
			}
			else if (user.getTipo().equals(userTypes.get("RespAz")))
			{
				page = "/richieste"; //HomeResponsabileAzienda
			}
			request.setAttribute("errore", "Questa sezione &egrave; accedibile solo se non si &egrave; autenticati");
			RequestDispatcher dispatcher = req.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
