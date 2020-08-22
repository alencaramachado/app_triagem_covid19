package br.ufsm.csi.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class FiltroController implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        System.out.println(" ");
        System.out.println("ID da Sessão: "+req.getSession().getId());
        System.out.println("Path ... "+req.getRequestURI());
        System.out.println(" ");

        String uri = req.getRequestURI();
        System.out.println("URI = "+uri);

        if(uri.equals("/app_triagem_covid19/login")){
            chain.doFilter(request, response);
        }else{
            if(req.getSession().getAttribute("logado") != null){

                
                    chain.doFilter(request, response);
            }else{
                RequestDispatcher rd = req.getRequestDispatcher("/");
                rd.forward(request, response);
            }
        }






    }

    @Override
    public void destroy() {

    }
}
