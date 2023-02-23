package com.apitest.utilities;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;

public class JsonUtil {
//ObjectMapperi tanimlayalim
    private static ObjectMapper mapper;

    static{
        mapper=new ObjectMapper();
    }

    //Burada T herhangi bir daratype olabilir.Data type belli olmayan yapilarda kullanilir.
    // readValue() methoddan gelen degeri oku ve javaResult icine aata.
    // err error mesajlari icin kullanilir, Consolda kirmizi gorunur
    //throw ve try-catch farki nedir? Reusable methodlarda genellikle trycatch kullanilir,
    //Baska bir classta cagirinca tekrar handle etmek zorunda kalmam.

    //Bu method kendisine gonderilen string datayi java objesine donusturup bize return ediyor
    public static <T> T convertJsonToJava(String json,Class<T> cls){
        T javaResult= null;
        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            System.err.println("json datası javaya dönüştürülemedi");
        }
        return javaResult;
    }
}
