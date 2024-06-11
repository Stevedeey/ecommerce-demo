package org.decadev.ecommerce.servlet;

import org.decadev.ecommerce.dao.UserDao;
import org.decadev.ecommerce.dao.impl.UserDaoImpl;
import org.decadev.ecommerce.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("I GOT INTO THE DO POST");
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String email = request.getParameter("email");
//        String roleString = request.getParameter("role");
//
//        User.Role role = User.Role.valueOf(roleString);
//        // I could have encoded the password here but for the lack of time
//        User user = new User(username, email, password, role);
//
//        UserDao userDao = new UserDaoImpl();
//        userDao.persistUser(user);
//
//        response.sendRedirect("registration-success.jsp");
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String roleString = request.getParameter("role");

        User.Role role = User.Role.valueOf(roleString);

        UserDao userDao = new UserDaoImpl();
        if (userDao.doesUserExist(username, email)) {
            request.setAttribute("errorMessage", "Username or email already exists. Please try again.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        } else {
            User user = new User(username, email, password, role);

            boolean registrationSuccess = userDao.registerUser(user);

            if (registrationSuccess) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                request.setAttribute("successMessage", "Registered successfully. You can now login.");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Registration failed. Please try again.");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            }
        }
    }

}
