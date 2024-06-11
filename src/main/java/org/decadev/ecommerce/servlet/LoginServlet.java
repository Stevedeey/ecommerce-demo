package org.decadev.ecommerce.servlet;

import org.decadev.ecommerce.dao.UserDao;
import org.decadev.ecommerce.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao userDao = new UserDaoImpl();
        String username = request.getParameter("username").toLowerCase();
        String password = request.getParameter("password");

        boolean isAuthenticated = userDao.authenticateUser(username, password);

        System.out.println("Auth status " + isAuthenticated);

        if (isAuthenticated) {
            request.setAttribute("username", username);
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } else {
            response.sendRedirect("failure.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
