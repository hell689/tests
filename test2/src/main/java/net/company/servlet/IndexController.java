package net.company.servlet;

import net.company.domain.WebLink;
import net.company.service.LinkFinderService;
import net.company.service.LinkFinderSeviceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "indexServlet", urlPatterns = {"/", "/index.html"})
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String link = req.getParameter("linkInput");
        System.out.println(link);
        LinkFinderService finderService = new LinkFinderSeviceImpl();
        List<WebLink> webLinks = finderService.getLinksFromPage(link);
        webLinks.forEach((a)->{
            System.out.println(a.getName() + "  --  " + a.getLink());
        });
        req.setAttribute("links", webLinks);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
