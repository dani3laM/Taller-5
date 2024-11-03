package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
    private ProductoMenu productoMenu;

    @BeforeEach
    public void setUp() {
        // Inicializar un nuevo producto de menú
        productoMenu = new ProductoMenu("Hamburguesa", 5000);
    }

    @Test
    public void testConstructor() {
        // Verifica que el producto no sea nulo
        assertNotNull(productoMenu);
        // Verifica que el nombre del producto sea correcto
        assertEquals("Hamburguesa", productoMenu.getNombre());
        // Verifica que el precio base del producto sea correcto
        assertEquals(5000, productoMenu.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        // Generar el texto de la factura y verificar que contenga el nombre y el precio
        String factura = productoMenu.generarTextoFactura();
        assertTrue(factura.contains("Hamburguesa"));
        assertTrue(factura.contains("            5000")); // Espacio antes del precio para igualar el formato
    }

    @Test
    public void testPrecioCero() {
        // Probar la creación de un producto con precio cero
        ProductoMenu productoGratis = new ProductoMenu("Agua", 0);
        assertEquals(0, productoGratis.getPrecio());
    }

    @Test
    public void testPrecioNegativo() {
        // Probar la creación de un producto con un precio negativo
        ProductoMenu productoNegativo = new ProductoMenu("Error", -1000);
        assertEquals(-1000, productoNegativo.getPrecio());
    }
}

