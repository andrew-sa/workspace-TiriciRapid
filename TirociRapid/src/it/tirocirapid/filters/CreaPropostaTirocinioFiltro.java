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
 * Servlet Filter implementation class CreaPropostaTirocinioFiltro
 */
@WebFilter("/CreaPropostaTirocinioFiltro")
public class CreaPropostaTirocinioFiltro implements Filter {

    /**
     * Default constructor. 
     */
    public CreaPropostaTirocinioFiltro() {
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
		if (session.getAttribute("user") == null && session.getAttribute("azienda") == null)
		{
			request.setAttribute("errore", "Per poter proporre un nuovo tirocinio devi autenticarti come Responsabile Azienda");
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
