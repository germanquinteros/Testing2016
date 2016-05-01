package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
	private WebDriver driver;
	
	public PageObject(WebDriver driver){
		this.driver = driver;
	}
	public void IngresarNombreProducto(String nombreProducto){
		WebElement buscador = driver.findElement(By.name("search"));
		buscador.sendKeys(nombreProducto);
	}
	public void BuscarProducto(){
		WebElement botonBuscar = driver.findElement(By.xpath("//span[@class='input-group-btn']/button"));
		botonBuscar.click();
	}
	public String ObtenerNombrePrimerProducto(){
		WebElement primerElemento = driver.findElement(By.className("product-thumb"));
		String nombre = primerElemento.findElement(By.className("caption")).findElement(By.tagName("h4")).findElement(By.tagName("a")).getText();
		return nombre;
	}
	public void SeleccionarPrimerProducto(){
		WebElement primerElemento = driver.findElement(By.className("product-thumb"));
		primerElemento.findElement(By.className("image")).findElement(By.tagName("a")).findElement(By.tagName("img")).click();
	}
	public String ObtenerTituloProductoSelecionado(){
		return driver.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/h1")).getText();
	}
}
