package net.company.servlet;

import net.company.domain.WebLink;
import net.company.service.LinkFinderService;
import net.company.service.LinkFinderSeviceImpl;
import net.company.service.ServiceException;
import org.json.JSONObject;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "IndexController", urlPatterns = {"/index.html"}, asyncSupported = true)
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String link = req.getParameter("linkInput");
        LinkFinderService finderService = new LinkFinderSeviceImpl();

        try(PrintWriter writer = resp.getWriter()) {

            JSONObject json = new JSONObject();
            try {
                List<WebLink> webLinks = finderService.getLinksFromPage(link);
                json.put("links", webLinks);
            } catch (ServiceException e) {
                resp.setStatus(400);
                json.put("error", "Link Error");
            }
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            writer.print(json.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
