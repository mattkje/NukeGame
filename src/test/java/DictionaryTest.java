import no.matkje.tools.Dictionary;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DictionaryTest {

    @Test
    public void testReadWordsOfList() throws IOException {
        Dictionary dic = new Dictionary("english");
        System.out.println(dic.readWordsOfList());
        dic.readWordsOfList();
    }
}
