package com.bestbuy.crudtest;

import com.bestbuy.bestbuyinfo.ProductSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 * Created by Jigar Patel
 */
@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {

    static String name = "Panasonic - AA Batteries" + TestUtils.getRandomValue();
    static String type = "HardGoods" + TestUtils.getRandomValue();
    static String manufacturer = "Panasonic";
    static String model = "PN0123B4Z";
    static String upc = "987456321";
    static String description = "50% Extra long life";
    static String url = "http://www.bestbuy.com";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static double price = 4.99;
    static double shipping = 3.49;
    static int id;

    @Steps
    ProductSteps productSteps;
    @Title("Creating  a new Product")
    @Test
    public void testNo01(){
        ValidatableResponse response = productSteps.createProduct(name,description,type,image, shipping, model, manufacturer,price,upc,url);
        response.statusCode(201);
        id = response.extract().path("id");
        System.out.println(" Id no is :" + id);
    }

    @Title("Verify that new product added")
    @Test
    public void testNo02(){
        productSteps.getProductById(id).statusCode(200);
    }

    @Test
    public void testNo03(){
        price = 5.99;
        ValidatableResponse response = productSteps.updateProduct(id,name,description,type,image, shipping, model, manufacturer,price,upc,url);
        response.statusCode(200);
    }

    @Test
    public void testNo04() {
        productSteps.deleteProduct(id).statusCode(200);
    }
}
