package it.tirocirapid.filters;

import java.io.IOException;
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

/**
 * Servlet Filter implementation class AreaRiservataFiltro
 */
@WebFilter(
		servletNames = { 
				"CaricaProfiloStudente", 
				"CaricaDatiAzienda", 
				"CaricaTirociniAzienda"
		})
public class AreaRiservataFiltro implements Filter {

    /**
     * Default constructor. 
     */
    public AreaRiservataFiltro() {
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
		if (session.getAttribute("user") == null)
		{
			request.setAttribute("errore", "Per poter accedere a questa sezione devi essere autenticato");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/"); //Login
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
