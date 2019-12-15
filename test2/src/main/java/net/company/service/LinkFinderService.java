package net.company.service;

import net.company.domain.WebLink;

import java.util.List;

public interface LinkFinderService {

    List<WebLink> getLinksFromPage(String url) throws ServiceException;
}
