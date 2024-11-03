package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PedidoTest {
    private Pedido pedido;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    public void setUp() {
        // Inicializar los productos de ejemplo
        producto1 = new ProductoMenu("Hamburguesa", 5000);
        producto2 = new ProductoMenu("PapasFritas", 2000);
        
        // Crear un nuevo pedido
        pedido = new Pedido("Juan_Perez", "Calle 123");
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

    }
    @Test
    public void testGenerarTextoFactura() {
        String factura = pedido.generarTextoFactura();

        // Verificar que el texto de la factura contenga el nombre del cliente y dirección
        assertTrue(factura.contains("Juan_Perez"));
        assertTrue(factura.contains("Calle 123"));

        // Verificar que contenga los productos
        assertTrue(factura.contains("Hamburguesa"));
        assertTrue(factura.contains("PapasFritas"));

        // Verificar que el precio neto y total sean correctos
        assertTrue(factura.contains("Precio Neto:  7000")); // 5000 + 2000
        assertTrue(factura.contains("IVA:          1330")); // 19% de 7000
        assertTrue(factura.contains("Precio Total: 8330")); // 7000 + 1330
    }

    @Test
    public void testGuardarFactura() {
        File archivo = new File("factura_test.txt");
        try {
            pedido.guardarFactura(archivo);
            assertTrue(archivo.exists(), "El archivo de factura no se ha creado.");

            // Leer el contenido del archivo para verificarlo
            Scanner scanner = new Scanner(archivo);
            StringBuilder contenido = new StringBuilder();
            while (scanner.hasNextLine()) {
                contenido.append(scanner.nextLine()).append("\n");
            }
         // Verificar que el contenido del archivo coincida con lo esperado
            assertTrue(contenido.toString().contains("Cliente: Juan_Perez"));
            assertTrue(contenido.toString().contains("Dirección: Calle 123"));
            assertTrue(contenido.toString().contains("Hamburguesa"));
            assertTrue(contenido.toString().contains("PapasFritas"));
            assertTrue(contenido.toString().contains("Precio Neto:  7000"));
            assertTrue(contenido.toString().contains("IVA:          1330"));
            assertTrue(contenido.toString().contains("Precio Total: 8330"));

            scanner.close();

            
        } catch (FileNotFoundException e) {
            fail("No se pudo crear el archivo de la factura: " + e.getMessage());
        } finally {
            // Borrar el archivo después de la prueba
            if (archivo.exists()) {
                archivo.delete();
            }
        }
    }

    @Test
    public void testConstructor() {
        // Verifica que el pedido no sea nulo
        assertNotNull(pedido);
        // Verifica que el nombre del cliente sea correcto
        assertEquals("Juan_Perez", pedido.getNombreCliente());
        
        assertEquals(1, pedido.getIdPedido());
    }

    @Test
    public void testAgregarProducto() {
        // Agregar productos al pedido
        
        
        // Verifica que el precio total del pedido sea correcto
        assertEquals(8330, pedido.getPrecioTotalPedido());
    }
    
    
    @Test
    public void testGuardarFacturaThrowsException() {
        // Intentar guardar la factura en un archivo de solo lectura
        File archivoSoloLectura = new File("factura_test.txt");
        try {
            archivoSoloLectura.createNewFile();
            // Hacer el archivo de solo lectura
            archivoSoloLectura.setReadOnly();

            // Probar que se lanza la excepción al intentar guardar
            assertThrows(FileNotFoundException.class, () -> {
                pedido.guardarFactura(archivoSoloLectura);
            });
        } catch (Exception e) {
            fail("Excepción inesperada: " + e.getMessage());
        } finally {
            // Borrar el archivo después de la prueba
            if (archivoSoloLectura.exists()) {
                archivoSoloLectura.delete();
            }
        }
    }

    

}