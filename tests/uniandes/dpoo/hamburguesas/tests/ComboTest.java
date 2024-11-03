package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ComboTest {
    private Combo combo;
    private ArrayList<ProductoMenu> itemsCombo;
    private ProductoMenu item;
    private ProductoMenu item2;
    
    @BeforeEach
    public void setUp() {
        // Crear productos de ejemplo
        item = new ProductoMenu("Hamburguesa", 5000);
        item2 = new ProductoMenu("PapasFritas", 2000);
        
        itemsCombo = new ArrayList<ProductoMenu>();
        itemsCombo.add(item);
        itemsCombo.add(item2);
        
        

        // Crear el combo con un 20% de descuento
        combo = new Combo("ComboBasico", 0.7, itemsCombo);
        
    }

    @Test
    public void testConstructor() {
        assertNotNull(combo);
        assertEquals("ComboBasico", combo.getNombre());
        assertEquals(0.8, combo.getDescuento(), 0.01);
        assertEquals(2, combo.getItemsCombo().size());
    }

    @Test
    public void testGetNombre() {
        assertEquals("Combo Basico", combo.getNombre());
    }

    @Test
    public void testGetPrecio() {
        // Precio sin descuento: 5000 + 2000 = 7000
        // Aplicando el descuento del 20%: 7000 * 0.8 = 5600
        assertEquals(5600, combo.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        String expectedText = "Combo Combo Basico\n Descuento: 0.8\n            5600\n";
        assertEquals(expectedText, combo.generarTextoFactura());
    }
}


