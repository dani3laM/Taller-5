package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
    private ProductoAjustado productoAjustado;
    private ProductoMenu productoBase;
    private Ingrediente ingrediente1;
    private Ingrediente ingrediente2;

    @BeforeEach
    public void setUp() {
        // Inicializar el producto base
        productoBase = new ProductoMenu("Pizza", 10000);
        
        // Inicializar los ingredientes de ejemplo
        ingrediente1 = new Ingrediente("Extra Queso", 2000);
        ingrediente2 = new Ingrediente("Jalapeños", 1500);
        
        // Crear un nuevo producto ajustado a partir del producto base
        productoAjustado = new ProductoAjustado(productoBase);
        
    }

    @Test
    public void testConstructor() {
        // Verifica que el producto ajustado no sea nulo
        assertNotNull(productoAjustado);
        // Verifica que el nombre del producto base sea correcto
        assertEquals("Pizza", productoAjustado.getNombre());
    }

    @Test
    public void testAgregarIngredientes() {
        // Agregar ingredientes al producto ajustado
    	ArrayList<Ingrediente> agregados = new ArrayList<Ingrediente>();
    	agregados.add(ingrediente1);
    	agregados.add(ingrediente2);
        productoAjustado.setAgregados(agregados);
        
        // Verificar que el precio se actualice correctamente
        int precioEsperado = productoBase.getPrecio() + ingrediente1.getCostoAdicional() + ingrediente2.getCostoAdicional();
        assertEquals(precioEsperado, productoAjustado.getPrecio());
    }

    @Test
    public void testEliminarIngredientes() {
        // Agregar y luego eliminar un ingrediente
        productoAjustado.getAgregados().add(ingrediente1);
        productoAjustado.getEliminados().add(ingrediente1);
        
        // Verificar que el precio no incluya el ingrediente eliminado
        assertEquals(productoBase.getPrecio(), productoAjustado.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        // Agregar ingredientes al producto ajustado
        productoAjustado.getAgregados().add(ingrediente1);
        productoAjustado.getAgregados().add(ingrediente2);
        
        String factura = productoAjustado.generarTextoFactura();
        assertTrue(factura.contains("Pizza"));
        assertTrue(factura.contains("    +Extra Queso"));
        assertTrue(factura.contains("    +Jalapeños"));
        assertTrue(factura.contains(String.valueOf(productoAjustado.getPrecio())));
    }
}
