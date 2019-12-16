package net.company.domain;

/**
 * class WebLink
 * Класс веб-ссылка.
 *  link - адрес ссылки
 *  name - имя ссылки (текст ссылки)
 */
public class WebLink {
    private String name;
    private String link;

    public WebLink(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
