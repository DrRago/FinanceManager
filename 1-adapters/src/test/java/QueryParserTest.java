import org.junit.jupiter.api.Test;
import query_parser.QueryParser;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryParserTest {
    @Test
    public void QueryParser_ProvideCorrectStringWithoutList_ParseStringCorrectly() {
        String query = "date=2021-05-31&name=Max&surname=Mustermann";

        Map<String, List<String>> params = QueryParser.splitQuery(query);
        assertEquals("2021-05-31", params.get("date").get(0));
        assertEquals("Max", params.get("name").get(0));
        assertEquals("Mustermann", params.get("surname").get(0));
    }

    @Test
    public void QueryParser_ProvideCorrectStringList_ParseStringCorrectly() {
        String query = "item=first&item=second&item=third";

        Map<String, List<String>> params = QueryParser.splitQuery(query);
        assertEquals(3, params.get("item").size());
        assertEquals("first", params.get("item").get(0));
        assertEquals("second", params.get("item").get(1));
        assertEquals("third", params.get("item").get(2));
    }
}
