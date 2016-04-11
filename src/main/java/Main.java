import alchemyapi.ApiManager;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;
import models.Document;
import models.DocumentsResult;
import models.Sentiment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Main {
    private OSCPortOut sender;
    private int sendPort = 6448;
    private String message = "/wek/inputs";

    private ApiManager apiManager;

    private JPanel mainForm;
    private JTextField articleConcept;
    private JButton load;

    private Main() {
        try {
            sender = new OSCPortOut(InetAddress.getLocalHost(), sendPort);
        } catch (SocketException ex) {
            System.out.println("Error: Socket exception");
        } catch (UnknownHostException ex) {
            System.out.println("Error: Unknown Host Exception");
        }
        load.addActionListener(e -> updateOSC());
        apiManager = new ApiManager();
    }

    private void updateOSC() {
        apiManager.loadNewsSentiments(articleConcept.getText(), new Callback<DocumentsResult>() {
            @Override
            public void onResponse(Call<DocumentsResult> call, Response<DocumentsResult> response) {
                DocumentsResult documentsResult = response.body();
                int size = documentsResult.getDocuments().getDocuments().size();
                float averageMixed = 0;
                float averageScore = 0;
                float negativeTypeCount = 0;
                float positiveTypeCount = 0;
                float neutralTypeCount = 0;
                for (Document document : documentsResult.getDocuments().getDocuments()) {
                    Sentiment sentiment = document.getSource().getEnriched().getArticle().getSentiment();
                    averageMixed += sentiment.getMixed();
                    averageScore += sentiment.getScore();
                    negativeTypeCount += getTypeNumber(sentiment);
                    switch (sentiment.getType()) {
                        case NEGATIVE: {
                            negativeTypeCount++;
                        }
                        case POSITIVE: {
                            positiveTypeCount++;
                        }
                        case NEUTRAL: {
                            neutralTypeCount++;
                        }
                    }
                }

                Float[] o = new Float[3];
                o[0] = averageMixed / size;
                o[1] = averageScore / size;
                o[2] = Math.max(Math.max(negativeTypeCount, positiveTypeCount), neutralTypeCount);

                OSCMessage msg = new OSCMessage(message, o);

                try {
                    sender.send(msg);
                } catch (IOException ex) {
                    System.out.println("Could not send message: " + ex.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<DocumentsResult> call, Throwable t) {

            }
        });

    }

    private int getTypeNumber(Sentiment sentiment) {
        switch (sentiment.getType()) {
            case NEGATIVE: {
                return -1;
            }
            case POSITIVE: {
                return 1;
            }
            case NEUTRAL: {
                return 0;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().mainForm);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
