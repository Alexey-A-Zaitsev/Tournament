package Tournament;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
        game.register(vitalya);
        game.register(olya);
        List actual = game.players;
        List expected = Arrays.asList(sveta, vasya, petya, vitalya, olya);

        Assertions.assertArrayEquals(actual.toArray(), expected.toArray());
    }

    @Test
    void shouldRegisterOnePlayer() {

        game.register(olya);
        List actual = game.players;
        List expected = Arrays.asList(sveta, vasya, petya, olya);

        Assertions.assertArrayEquals(actual.toArray(), expected.toArray());
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
