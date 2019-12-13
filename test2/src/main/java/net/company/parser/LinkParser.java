package net.company.parser;

import net.company.domain.WebLink;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkParser {
    private final String LINK_PATTERN = "<a href=\"([A-Za-z0-9:\\/\\._\\-&=?]+)\"[ A-Za-zА-Яа-я0-9-=\"\\/\\._]+>([A-Za-aА-Яа-я0-9\\.\"\\-\\/_=$& ?!]+)<\\/a>";

    public List<WebLink> getLinksFromHtml (String htmlText) {
        List<WebLink> webLinks = new ArrayList<>();
        Pattern pattern = Pattern.compile(LINK_PATTERN);
        Matcher matcher = pattern.matcher(htmlText);
        while (matcher.find()) {
            String link = matcher.group(1);
            String linkName = matcher.group(2)
                    .replace("&nbsp;", " ")
                    .replace("&mdash;", "-");
            webLinks.add(new WebLink(linkName, link));
        }
        return webLinks;
    }
}
