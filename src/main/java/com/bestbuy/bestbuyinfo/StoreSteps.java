package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class StoreSteps {
    @Step("Creating new Store")
    public ValidatableResponse createStore(String name, String type, String address,String address2, String city, String state, String zip){
        StorePojo storePojo = StorePojo.getStorePojo(name, type,address,address2,city,state,zip);
        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post(EndPoints.CREATE_STORE).then();
    }

    @Step("Updating Store")
    public ValidatableResponse updateStore(int storeId,String name, String type, String address,String address2, String city, String state, String zip){
        StorePojo storePojo = StorePojo.getStorePojo(name,type,address,address2,city,state,zip);
        return SerenityRest.given()
                .pathParam("storeID", storeId)
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch(EndPoints.PATCH_STORE_BY_ID).then();

    }

    @Step("Delete store")
    public ValidatableResponse deleteStore(int storeId){
        return SerenityRest.given().log().all()
                .pathParam("storeID",storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then();
    }

    @Step("Getting store information with store id")
    public ValidatableResponse getStoreById(int storeId){
        return SerenityRest.given().log().all()
                .pathParam("storeID" , storeId)
                .when()
                .get(EndPoints.GET_STORE_BY_ID)
                .then();

    }
}
