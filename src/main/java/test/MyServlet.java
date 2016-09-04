package test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// 一个servlet的demo
@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public MyServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println("this is a simple servlet");
    }
}
