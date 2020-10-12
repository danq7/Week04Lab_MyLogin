package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AccountService;
import models.User;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String logoutAction = request.getParameter("action");

        if (logoutAction != null && logoutAction.equals("logout")) {
            session.invalidate();
            session = request.getSession();

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            request.setAttribute("loginMsg", "You have successfully logged out.");
            return;
        }

        if (session.getAttribute("userName") == null) {
            response.sendRedirect("login");
            return;

        } else if (session.getAttribute("userName") != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user;
        AccountService acctServ = new AccountService();

        String userName = request.getParameter("user_input");
        String password = request.getParameter("password");

        user = acctServ.login(userName, password);

        session.setAttribute("userName", user.getUserName());

        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);

    }

}
