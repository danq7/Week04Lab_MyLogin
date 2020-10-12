package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AccountService;
import models.User;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String logoutAction = request.getParameter("action");

        if (session.getAttribute("username") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            return;
        }

        if (logoutAction != null && logoutAction.equals("logout")) {
            session.invalidate();
            session = request.getSession();
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            request.setAttribute("loginMsg", "You have successfully logged out.");
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String userName = request.getParameter("user_input");
        String password = request.getParameter("password_input");

        User user;
        AccountService acctServ = new AccountService();

        request.setAttribute("userName", userName);
        request.setAttribute("password", password);

        if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {

            user = acctServ.login(userName, password);

            if (user != null) {
                session.setAttribute("userName", user.getUserName());
                response.sendRedirect("home");

            } else if (user == null) {
                request.setAttribute("loginMsg", "Invalid login");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("loginMsg", "Invalid login");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

}
