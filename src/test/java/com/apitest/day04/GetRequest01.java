package com.apitest.day04;

import io.restassured.response.Response;
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
        Response responce = given().accept("application/json").when().get(url);

        //4-Actual result olustur
        // respons body aslinda actual resuldir, Bunu bi yerde saklamaliyiz
        //5-Dogrulama yap(assertion)
        responce.prettyPrint();

    }
}
