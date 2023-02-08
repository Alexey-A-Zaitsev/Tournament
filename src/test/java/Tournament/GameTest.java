package Tournament;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameTest {
    Game game = new Game();

    Player vasya = new Player(23, "Vasya", 23);
    Player petya = new Player(2, "Petya", 19);
    Player vitalya = new Player(945, "Vitalya", 25);

    Player olya = new Player(945, "Olya", 18);
    Player sveta = new Player(945, "Sveta", 18);

    @BeforeEach
    public void beforeAllTests() {
        game.register(sveta);
        game.register(vasya);
        game.register(petya);
    }

    @Test
    void shouldRegisterFewPlayers() {
        Map<String, Player> expected = new HashMap<>();
        expected.put("Sveta", sveta);
        expected.put("Vasya", vasya);
        expected.put("Petya", petya);
        expected.put("Olya", olya);
        expected.put("Vitalya", vitalya);


        game.register(vitalya);
        game.register(olya);
        HashMap actual = game.getPlayers();

        Assertions.assertTrue(actual.equals(expected));
    }

    @Test
    void shouldRegisterOnePlayer() {
        Map<String, Player> expected = new HashMap<>();
        expected.put("Sveta", sveta);
        expected.put("Vasya", vasya);
        expected.put("Petya", petya);
        expected.put("Olya", olya);


        game.register(olya);
        HashMap actual = game.getPlayers();

        Assertions.assertTrue(actual.equals(expected));
    }


    @Test
    public void shouldShowTheWinnerIfTheFirstPlayerIsStronger() {

        int actual = game.round("Vasya", "Petya");
        int expected = 1;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldShowTheWinnerIfTheSecondPlayerIsStronger() {
        game.register(vitalya);

        int actual = game.round("Petya", "Vitalya");
        int expected = -1;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldShowADraw() {
        game.register(olya);

        int actual = game.round("Olya", "Sveta");
        int expected = 0;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowAnExceptionIfTheFirstPlayerIsUnregistered() {

        Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Olya", "Sveta"));
    }

    @Test
    public void shouldThrowAnExceptionIfTheSecondPlayerIsUnregistered() {

        Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Petya", "vitalya"));
    }

    @Test
    public void shouldThrowAnExceptionIfBothPlayersAreUnregistered() {

        Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Olya", "vitalya"));
    }
}
