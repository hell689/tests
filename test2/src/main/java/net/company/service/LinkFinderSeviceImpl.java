package net.company.service;

import net.company.domain.WebLink;
import net.company.parser.LinkParser;
import net.company.parser.ParserException;
import net.company.parser.WebPage;

import java.util.List;

public class LinkFinderSeviceImpl implements LinkFinderService {
    @Override
    public List<WebLink> getLinksFromPage(String url) throws ServiceException {
        WebPage webPage = new WebPage(url, "UTF-8");
        LinkParser parser = new LinkParser();
        try {
            return parser.getLinksFromHtml(webPage.getContent());
        } catch (ParserException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
