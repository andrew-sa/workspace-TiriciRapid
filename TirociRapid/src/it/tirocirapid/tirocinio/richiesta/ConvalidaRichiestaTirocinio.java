package it.tirocirapid.tirocinio.richiesta;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa di gestire la decisione da parte del Responsabile Approvazioni, Professore (Tutor), Azienda su una richeista di tirocinio da parte di uno studente
 */
@WebServlet("/convalida_richiesta_tirocinio")
public class ConvalidaRichiestaTirocinio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConvalidaRichiestaTirocinio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void gestioneAzienda(boolean isAccept)
	{
		
	}
	
	private void gestioneTutor(boolean isAccept)
	{
		
	}
	
	private void gestioneResponsabileApprovazioni(boolean isAccept)
	{
		
	}

}
