package net.company.parser;
/**
 * class WebPage
 * Класс для получения html текста
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebPage {
    private String pageAddress;
    private String codePage;
    private String htmlText;

    /**
     * Конструктор
     * @param pageAddress адрес страницы
     * @param codePage кодировка
     */
    public WebPage(String pageAddress, String codePage) {
        this.pageAddress = pageAddress;
        this.codePage = codePage;
    }

    /**
     * Чтение данных
     * @return html текст сраницы
     * @throws ParserException при ошибке чтения бросаем ParserException
     */
    public String getContent() throws ParserException {
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
            throw new ParserException(e.getMessage());
        }
        htmlText = sb.toString();
        return htmlText;
    }

    /**
     * Получение объекта URL из строки с адресом
     * @param httpAdress строка с адресом
     * @return объект Url
     * @throws ParserException при ошибке бросаем ParserException
     */
    private URL getUrl (String httpAdress) throws ParserException {
        URL url;
        try {
            url = new URL(httpAdress);
        } catch (MalformedURLException e) {
            throw new ParserException(e.getMessage());
        }
        return url;
    }

    /**
     * Получение соединения по URL адресу
     * @param url URL адрес
     * @return URLConnection
     * @throws ParserException при ошибке бросаем ParserException
     */
    private URLConnection getURLConnection (URL url) throws ParserException {
        URLConnection urlConnection;
        try {
            urlConnection = url.openConnection();
        } catch (IOException e) {
            throw new ParserException(e.getMessage());
        }
        return urlConnection;
    }

    public String getHtmlText() {
        return htmlText;
    }
}
