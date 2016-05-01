package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(value = Parameterized.class)
public class MainTest {
	private static WebDriver driver =null;
	private static WebDriverWait esperar=null;

	private String nombreProducto;
	
	public MainTest(String nombre){
		nombreProducto = nombre;
	}

	@BeforeClass
	public static void InicializarDriver(){
		driver = new FirefoxDriver();
	}
	@AfterClass
	public static void FinalizarDriver(){
		driver.quit();
	}
	@Test
	public void testPagina() {
		//Desplegare todos los elementos al buscar el string espacio " "
		WebElement buscador = driver.findElement(By.name("search"));
		buscador.sendKeys(nombreProducto);
		WebElement botonBuscar = driver.findElement(By.xpath("//span[@class='input-group-btn']/button"));
		botonBuscar.click();
		//Busque el Elemento
		//Espero 20 segundoss
		esperar = new WebDriverWait(driver, 20);
		//Selecciono el primero 
		WebElement primerElemento = driver.findElement(By.className("product-thumb"));
		assertEquals(nombreProducto, primerElemento.findElement(By.className("caption")).findElement(By.tagName("h4")).findElement(By.tagName("a")).getText());
		//Hago Click en el Producto
		primerElemento.findElement(By.className("image")).findElement(By.tagName("a")).findElement(By.tagName("img")).click();
		//Comparo el Titulo que aparece al seleccionar el producto con el nombre del Producto que yo tengo en el txt
		assertEquals(nombreProducto,driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/h1")).getText());
		//Espero 5 segundos
		esperar = new WebDriverWait(driver, 5);
	}
	@Parameters
	public static Iterable<Object[]> data1() {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			//Leo el archivo
			int contador = 0;
			br = new BufferedReader(new FileReader("C:\\Users\\Germán\\workspace_luna\\TestingDesafioSelenium4\\src\\test\\listaProductos"));
			//Guardo los Numeros en el Array
			while ((sCurrentLine = br.readLine()) != null) {
				contador = contador +1;
			}
			String[] elemento;
			String[][] arrayfinal = new String[contador][1];
			//Leo el archivo
			br = new BufferedReader(new FileReader("C:\\Users\\Germán\\workspace_luna\\TestingDesafioSelenium4\\src\\test\\listaProductos"));
			//Guardo los Nombres en el Array
			contador = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				elemento = new String[1];
				elemento[0] = sCurrentLine;
				arrayfinal[contador] = elemento;
				contador = contador +1;
			}
			return Arrays.asList(arrayfinal);
		} 
		catch (IOException e) {
			e.printStackTrace();
			return Arrays.asList();
		} 
		finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
