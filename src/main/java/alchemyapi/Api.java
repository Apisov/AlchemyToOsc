package alchemyapi;

import models.DocumentsResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {

    String API_KEY = "248de9bee9eb62f52a833059d062d5dca929a5f6";
    String CONCEPT_REQUEST = "q.enriched.url.concepts.concept.text";

    @GET("calls/data/GetNews?return=enriched.url.docSentiment&count=10&start=now-1&end=now&outputMode=json&apikey=" + API_KEY)
    Call<DocumentsResult> getNewsSentiments(@Query(CONCEPT_REQUEST) String conceptWord);
}
