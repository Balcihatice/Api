package com.apitest.day12;

import com.apitest.Pojos.TodosPojo;
import com.apitest.testBase.JsonPlaceHolderTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostRequestWithPojo01 extends JsonPlaceHolderTestBase {

    /*Put request
   https://jsonplaceholder.typicode.com/todos urlsine asagidaki body gonderildiginde
   {
   "userId": 21,
   "id": 201,
   "title": "Tidy your room",
   "completed": false
   }
   Donen responsun statuscode`un 201 ve response nin sagidaki gibi oldugunu test edin
    {
  "userId": 21,
   "id": 201,
   "title": "Tidy your room",
   "completed": false
   }
    */
    @Test
    public void test() {
        spec01.pathParam("parametre1", "todos");

        TodosPojo requestExpected = new TodosPojo(21, 201, "Tidy your room", false);
        //parametreli contructori ilk deger atamak icin kullanacagiz
        System.out.println(requestExpected);
        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin", "password123").
                body(requestExpected).when().post("/{parametre1}");

        response.prettyPrint();

        //de serialization : Pojo kullaninca Map`e gerek yok
        TodosPojo actualData = response.as(TodosPojo.class);

        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(requestExpected.getUserId(),actualData.getUserId());
        Assert.assertEquals(requestExpected.getTitle(),actualData.getTitle());
        Assert.assertEquals(requestExpected.getId(),actualData.getId());
        Assert.assertEquals(requestExpected.isCompleted(),actualData.isCompleted());

    }
}
