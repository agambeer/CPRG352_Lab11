package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.AccountService;

public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("uuid") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
            return;
        } else {
            request.setAttribute("uuid_set", request.getParameter("uuid"));
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("uuid_set") == null) {
            String reset_email = request.getParameter("reset_email");
            request.setAttribute("reset_email", reset_email);

            if (reset_email != null && !reset_email.equals("")) {
                String url = request.getRequestURL().toString();
                String path = getServletContext().getRealPath("/WEB-INF");
                AccountService as = new AccountService();
                if (as.resetPassword(reset_email, path, url)) {
                    request.setAttribute("message", "Email has been sent to " + reset_email + " for password reset.");
                    getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
                    return;
                }
            }
        } else {
            String reset_password = request.getParameter("reset_password");
            request.setAttribute("reset_password", reset_password);

            if (reset_password != null && !reset_password.equals("")) {
                AccountService as = new AccountService();
                if (as.changePassword(request.getParameter("uuid_set"), reset_password)) {
                    request.setAttribute("message", "Your password has been reset.");
                    getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
                    return;
                } else {
                    request.setAttribute("message", "Your password has NOT been reset. " + request.getParameter("uuid_set") + "  Password = " + reset_password + " uuid = " + request.getParameter("uuid_set"));
                    getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
                    return;
                }
            }
        }
    }
}
