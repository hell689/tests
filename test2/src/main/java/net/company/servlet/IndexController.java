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
import java.util.List;

@WebServlet(name = "IndexController", urlPatterns = {"/index.html"})
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String link = req.getParameter("linkInput");
        System.out.println("Ссылка для поиска: " + link);
        LinkFinderService finderService = new LinkFinderSeviceImpl();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        try(PrintWriter writer = resp.getWriter()) {
            try {
                List<WebLink> webLinks = finderService.getLinksFromPage(link);
                System.out.println("Найдено ссылок: " + webLinks.size());
                JSONObject json = new JSONObject();
                json.put("links", webLinks);
                writer.print(json.toString());
            } catch (ServiceException e) {
                System.err.println("Возникла ошибка при парсинге: " + e.getMessage());
                resp.setStatus(400);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
