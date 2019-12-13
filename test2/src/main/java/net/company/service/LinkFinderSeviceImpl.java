package net.company.service;

import net.company.domain.WebLink;
import net.company.parser.LinkParser;
import net.company.parser.WebPage;

import java.util.List;

public class LinkFinderSeviceImpl implements LinkFinderService {
    @Override
    public List<WebLink> getLinksFromPage(String url) {
        WebPage webPage = new WebPage(url, "UTF-8");
        LinkParser parser = new LinkParser();
        return parser.getLinksFromHtml(webPage.getContent());
    }
}
