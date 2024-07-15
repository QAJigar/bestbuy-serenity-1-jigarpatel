package com.bestbuy.crudtest;

import com.bestbuy.bestbuyinfo.StoreSteps;
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
public class StoresCURDTest extends TestBase {

    static String name = "Mike" + TestUtils.getRandomValue();
    static String type = "sendBox" + TestUtils.getRandomValue();
    static String address = "391 City Road" + TestUtils.getRandomValue();
    static String address2 = "Angel" + TestUtils.getRandomValue();
    static String city = "Islington";
    static String state = "London";
    static String zip = "387001";
    static int storeId;

    @Steps
    StoreSteps storeSteps;
    @Title("Creating  a new Store")
    @Test
   public void testNo01(){
        ValidatableResponse response = storeSteps.createStore(name,type,address,address2,city,state,zip);
        response.statusCode(201);
        response.log().all();
        storeId = response.extract().path("id");
    }

    @Title("Verify that new store added")
    @Test
    public void testNo02() {
        ValidatableResponse response = storeSteps.getStoreById(storeId).statusCode(200);


    }

    @Title("Update store City")
    @Test
    public void testNo03() {

        ValidatableResponse response = storeSteps.updateStore(storeId,name,type,address,address2,city,state,zip);
        response.statusCode(200);

    }

    @Title("Delete the store")
    @Test
    public void testNo04() {
        storeSteps.deleteStore(storeId).statusCode(200);

    }
}