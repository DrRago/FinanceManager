import authentication.Sha512Hash;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sha512HashTest {
    @Test
    public void hash_provideString_getDefinedHash() {
        String toHash = "password";
        String hash = "b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86";
        assertEquals(hash, Sha512Hash.hash(toHash));
    }
}
