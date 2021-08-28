package com.demo.service;

import com.demo.entity.DataEntity;
import com.demo.repository.DemoRepository;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DemoService {

    @Autowired
    DemoRepository demoRepository;

    public void makeRequest(DataEntity dataEntity){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(180000, TimeUnit.SECONDS)
                .readTimeout(180000,TimeUnit.SECONDS)
                .writeTimeout(180000,TimeUnit.SECONDS)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        String data = "{\"date\":\""+dataEntity.getDate()+"\",\"distCode\":\""+dataEntity.getDistCode()+"\",\"mandiCode\":\""+dataEntity.getMandiCode()+"\",\"commGroupCode\":\""+dataEntity.getCommGroupCode()+"\",\"commCode\":\""+dataEntity.getCommCode()+"\"}";
        RequestBody body = RequestBody.create(mediaType, data);
        Request request = new Request.Builder()
                .url("https://eanugya.mp.gov.in/Anugya_e/frontData.asmx/getAllData")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        System.out.println(data);
        try (Response response = client.newCall(request).execute()){
            JSONObject dataReceived = new JSONObject(response.body().string());
            System.out.println(dataReceived);
            if (dataReceived.getJSONArray("d").length()!=0) {
                dataReceived = dataReceived.getJSONArray("d").getJSONObject(0);
                String distName = dataReceived.getString("distName");
                String mandiName = dataReceived.getString("mandiName");
                String commName = dataReceived.getString("commName");
                String commGroupName = dataReceived.getString("commGroupName");
                String minValue = dataReceived.getString("minValue");
                minValue = minValue.substring(0,minValue.indexOf('.'));
                String maxValue = dataReceived.getString("maxValue");
                maxValue = maxValue.substring(0,maxValue.indexOf('.'));
                dataEntity.setDistName(distName);
                dataEntity.setMandiName(mandiName);
                dataEntity.setCommName(commName);
                dataEntity.setCommGroupName(commGroupName);
                dataEntity.setMinValue(minValue);
                dataEntity.setMaxValue(maxValue);
                demoRepository.createSingleBhav(dataEntity,dataEntity.getDate()); /*passing date as collection name in mongo db*/
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public long  get(String collection){
        return demoRepository.getCount(collection);
    }
}
