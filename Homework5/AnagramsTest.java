import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

//Marc Sulsenti
//I pledge my honor that I have abided by the Stevens Honor System
class AnagramsTest {

    @Test
    void myHashCode() {
        /* myHashCode is the only method in Anagrams that returns a specfic value that can be accurately tested */
        Anagrams test = new Anagrams();
       assertEquals(236204078, test.myHashCode("alerts") );
       assertEquals(test.myHashCode("alters"), test.myHashCode("alerts") );
       assertEquals(test.myHashCode("salter"), test.myHashCode("alerts") );
       assertEquals(test.myHashCode("brush"), test.myHashCode("shrub") );
       assertEquals(test.myHashCode("sneak"), test.myHashCode("snake") );
       assertNotEquals(test.myHashCode("apple"), test.myHashCode("snake"));
       //passes
    }

}