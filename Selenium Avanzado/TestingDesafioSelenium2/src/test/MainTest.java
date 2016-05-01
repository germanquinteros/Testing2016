package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class MainTest {
	private static WebDriver driver =null;
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
		driver.get("http://demo.opencart.com/");
		driver.manage().window().maximize();
		WebElement buscador = driver.findElement(By.name("search"));
		buscador.sendKeys(nombreProducto);
		WebElement botonBuscar = driver.findElement(By.xpath("//span[@class='input-group-btn']/button"));
		botonBuscar.click();
		WebElement producto = driver.findElement(By.xpath("//div[@class='image']/a/img"));
		producto.click();
	}
	
	@Parameters
	public static Iterable<Object[]> data1() {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			//Leo el archivo
			int contador = 0;
			br = new BufferedReader(new FileReader("C:\\Users\\Germán\\workspace_luna\\TestingDesafioSelenium2\\src\\test\\nombresProductos"));
			//Guardo los Numeros en el Array
			while ((sCurrentLine = br.readLine()) != null) {
				contador = contador +1;
			}
			String[] elemento;
			String[][] arrayfinal = new String[contador][1];
			//Leo el archivo
			br = new BufferedReader(new FileReader("C:\\Users\\Germán\\workspace_luna\\TestingDesafioSelenium2\\src\\test\\nombresProductos"));
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
