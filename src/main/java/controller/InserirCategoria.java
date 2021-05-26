package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoriaDAO;
import model.Categoria;

@WebServlet("/InserirCategoria")
public class InserirCategoria extends HttpServlet{
    
    private static final long serialVersionUID = 1L;

    CategoriaDAO dao = new CategoriaDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String nome = request.getParameter("nome");
        Categoria categoria = new Categoria();
        categoria.setNome(nome);

        String retorno = dao.inserir(categoria);

        if(retorno.equals("sucesso")){
            response.sendRedirect("InserirCategoria");
        }else{
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<h2> Não foi possível inserir a categoria!</h2>");
            out.println("<br>");
            out.println("<a href='index.jsp'> Voltar </a>");
            out.println("</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            //String action = request.getServletPath();
            listaCategoria(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listaCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categoria> listaCategoria = dao.listar();
        request.setAttribute("listaCategoria", listaCategoria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
