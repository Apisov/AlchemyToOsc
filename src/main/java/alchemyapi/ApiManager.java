package alchemyapi;

import models.DocumentsResult;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    Api api;

    public ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway-a.watsonplatform.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public void loadNewsSentiments(String concept, Callback<DocumentsResult> documentsResultCallback) {
        api.getNewsSentiments(concept).enqueue(documentsResultCallback);
    }
}
