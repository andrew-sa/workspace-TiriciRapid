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
 * Servlet Filter implementation class ResponsabileAziendaFiltro
 */
@WebFilter("/ResponsabileAziendaFiltro")
public class ResponsabileAziendaFiltro implements Filter {

    /**
     * Default constructor. 
     */
    public ResponsabileAziendaFiltro() {
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
		HashMap<String, String> userTypes = (HashMap<String, String>) request.getServletContext().getAttribute("userTypes");
		
		if (session.getAttribute("user") == null)
		{
			request.setAttribute("errore", "Per poter accedere a questa sezione devi essere autenticato come Responsabile Azienda");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/"); //LoginAzienda
			dispatcher.forward(request, response);
		}
		else if (!((UserLoggato) session.getAttribute("user")).equals(userTypes.get("RespAz")))
		{
			request.setAttribute("errore", "Per poter accedere a questa sezione devi essere autenticato come Responsabile Azienda");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/"); //LoginAzienda
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