package br.ufsm.csi.controller;

import br.ufsm.csi.dao.PacienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexController extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("index ....");


        req.setAttribute("pacientes", new PacienteDAO().getPacientes());
        RequestDispatcher rd = req.getRequestDispatcher("/pacientes.jsp");
        rd.forward(req, resp);


    }
}
