import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParametrizedTest {
    private Burger burger;
    private Ingredient sauce;
    private Ingredient filling;
    private final String name;
    private final float price;

    public BurgerParametrizedTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Before
    public void createNewInstance() {
        sauce = Mockito.mock(Ingredient.class);
        filling = Mockito.mock(Ingredient.class);
        burger = new Burger();

        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("chili");
        when(sauce.getPrice()).thenReturn(20.0f);

        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cheese");
        when(filling.getPrice()).thenReturn(15.5f);
    }

    @Parameterized.Parameters(name = "{index} : price = {1}")
    public static Object[][] getBunData() {
        return new Object[][]{
                {"original-free", 0.0f},
                {"original", 200.0f}
        };
    }

    @Test
    public void getPrice() {
        Bun bun = Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn(name);
        when(bun.getPrice()).thenReturn(price);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        float expected = price * 2 + sauce.getPrice() + filling.getPrice();
        float actual = burger.getPrice();

        assertEquals(expected, actual, 0);
    }
}
