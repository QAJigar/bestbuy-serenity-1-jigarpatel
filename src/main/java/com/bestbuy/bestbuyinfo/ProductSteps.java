package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class ProductSteps {
    @Step("Creating new product")
    public ValidatableResponse createProduct(String name, String description,String type, String image, double shipping,String model,
                                             String manufacturer, double price, String upc, String url){
        ProductPojo productPojo = ProductPojo.getProductPojo(name,description,type,image, shipping, model, manufacturer,price,upc,url);
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post(EndPoints.CREATE_PRODUCT).then();
    }

    @Step("Updating in new product")
    public ValidatableResponse updateProduct(int productId, String name, String description,String type, String image, double shipping,String model,
                                             String manufacturer, double price, String upc, String url){
        ProductPojo productPojo = ProductPojo.getProductPojo(name,description,type,image, shipping, model, manufacturer,price,upc,url);
        return SerenityRest.given()
                .pathParam("productID", productId)
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .patch(EndPoints.PATCH_PRODUCT_BY_ID).then();
    }

    @Step("Deleting Product")
    public ValidatableResponse deleteProduct(int productId){
        return SerenityRest.given().log().all()
                .pathParam("productID",productId)
                .when()
                .get(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();

    }

    @Step("Getting product information with product id")
    public ValidatableResponse getProductById(int productId){
        return  SerenityRest.given().log().all()
                .pathParam("productID" ,productId)
                .when()
                .get(EndPoints.GET_PRODUCT_BY_ID)
                .then();

    }

}
