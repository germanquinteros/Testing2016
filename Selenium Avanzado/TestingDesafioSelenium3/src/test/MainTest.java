package test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class MainTest {

	private static WebDriver driver =null;

	@BeforeClass
	public static void InicializarDriver(){
		driver = new FirefoxDriver();
	}
	@AfterClass
	public static void FinalizarDriver(){
		driver.quit();
	}
	@Test
	public void testPagina() throws IOException {
		driver.get("http://demo.opencart.com/");
		driver.manage().window().maximize();
		//Desplegare todos los elementos al buscar el string espacio " "
		WebElement buscador = driver.findElement(By.name("search"));
		buscador.sendKeys(" ");
		WebElement botonBuscar = driver.findElement(By.xpath("//span[@class='input-group-btn']/button"));
		botonBuscar.click();
		//Hago que la Pagina me muestre el mayor listado de objetos posibles
		Select paginado = new Select(driver.findElement(By.id("input-limit")));
		List<WebElement> opciones = paginado.getOptions();
		paginado.selectByIndex(opciones.size()-1);
		//Obtengo todos los Productos
		List<WebElement> listaProductos = driver.findElements(By.className("product-thumb"));
		//Ahora que tenemos todos los productos vamos a guardarlo en un archivo
		File file = new File("C:\\Users\\Germán\\workspace_luna\\TestingDesafioSelenium3\\src\\test\\listaProductos");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		//Recorro los elementos y los guardo en el archivo
		int contador = 0;
		for(WebElement elemento : listaProductos){
			contador = contador +1;
			//Este if es para no agregarle un \n al ultimo elemento
			if(contador==listaProductos.size())
			{
				bw.write((elemento.findElement(By.className("caption")).findElement(By.tagName("h4")).findElement(By.tagName("a")).getText()));
			}
			else
			{
				bw.write((elemento.findElement(By.className("caption")).findElement(By.tagName("h4")).findElement(By.tagName("a")).getText())+"\n");
			}
		}
		bw.close();
		fw.close();
	}
}
