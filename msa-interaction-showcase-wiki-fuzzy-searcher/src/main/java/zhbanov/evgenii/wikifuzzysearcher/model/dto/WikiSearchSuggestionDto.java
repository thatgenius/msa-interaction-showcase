package zhbanov.evgenii.wikifuzzysearcher.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "SearchSuggestion")
public class WikiSearchSuggestionDto {

    @JacksonXmlProperty(localName = "Section")
    private Section section;

    @JacksonXmlProperty(localName = "Query")
    private String query;

    @JacksonXmlProperty(isAttribute = true, localName = "version")
    private String version;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Section {

        @JacksonXmlProperty(localName = "Item")
        private Item item;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {

        @JacksonXmlProperty(localName = "Text")
        private String text;

        @JacksonXmlProperty(localName = "Url")
        private String url;
    }
}