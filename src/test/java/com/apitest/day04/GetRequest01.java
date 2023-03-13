package com.apitest.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
    //https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde
    // donecek cevap(response) icin
    //>>HTTP satatus kodunun 200
    //>>Content type in JsonbMapper>>responstaki Header den bakariz
    //>>ve status Line`in HTTP/1.1 200 OK oldugunu test edin
    @Test
    public void apiTest() {


        //1-api testi yaparken ilk olarak url(endpoint) belirlenmeli
        String url = "https://restful-booker.herokuapp.com/booking/3";

        //2-beklenen sonuc(expected result) olusturulur.bu case de benden body
        //dogrulamasi istenmedigi icin simdilik beklenen sonuc olusturmuyoruz.
        //3-request gonder
        Response responce = given().//gelen data formatimim application json olarak kabul et
                accept(ContentType.JSON).//accept("application/json") kullanilabilir.
                when().
                get(url);

        //responce.prettyPrint(); -->> yazdirma islemini responce ile yapariz

        //4-Actual result olustur(bunu java objesi gibi olustururuz)
        // respons body aslinda actual resuldir, Bunu bi yerde saklamaliyiz
        //simdilik respons body kullanmayacagimiz icin olusturmayacagiz

        //5-Dogrulama yap(assertion)
        System.out.println("Status Code : " + responce.getStatusCode());//responsdan gelen status codu aldik
        System.out.println("Content Type : " + responce.getContentType());//responsdan gelen content type aldik
        System.out.println("status Line : " + responce.getStatusLine());//responsdan gelen status line aldik
        // Istersek Header in tamamini yazdirabiliriz
        System.out.println(responce.getHeaders());


        Assert.assertEquals(200, responce.getStatusCode());
        //expected kismi bize task olarak verilen degerdir.Actual ksimi ise
        //responcee dan donen degerdir.
        Assert.assertEquals("application/json; charset=utf-8", responce.getContentType());
        Assert.assertEquals("HTTP/1.1 200 OK", responce.getStatusLine());


        //api deki assert etme yondemlerinden bir tanesini gorelim
        responce.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

    }
}
