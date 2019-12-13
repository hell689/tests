package net.company.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebPage {
    private String pageAddress;
    private String codePage;

    public WebPage(String pageAddress, String codePage) {
        this.pageAddress = pageAddress;
        this.codePage = codePage;
    }

    public String getContent() {
        StringBuilder sb = new StringBuilder();
        URL pageURL = getUrl(pageAddress);
        URLConnection urlConnection = getURLConnection(pageURL);
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream(), codePage))){
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine).append('\n');
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return sb.toString();
    }

    private URL getUrl (String httpAdress) {
        URL url = null;
        try {
            url = new URL(httpAdress);
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
        return url;
    }

    private URLConnection getURLConnection (URL url) {
        URLConnection urlConnection = null;
        try {
            urlConnection = url.openConnection();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return urlConnection;
    }
}
