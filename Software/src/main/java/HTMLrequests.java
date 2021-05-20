import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @author : Briez-Bañuls Valentin
 * @date : 21/04/2021
 * @brief : La classe Request ne sert qu'a une chose, effectuer une requète, que ce soit POST ou GET.
 * Il nous suffit de lui passer en paramètre de quoi compléter la requete URI et c'est bon.
 * Nous l'avons crée pour éviter de faire un copié / collé des instruction de la requète dans chaque méthode qui
 * l'utilise. Cette classe contient une seule méthode qui sert à faire la requete.
 */
public class HTMLrequests {
    public static String HTMLrequests(String args) {
        /**
         * Pour commencer, nous initialisation les quelques variables qui nous seront utiles. On concatène l'adresse
         * du web services ainsi que la requete pour former la requete uri.
         */
        args = args.replaceAll(" ", "&nbsp;");
        String url = "http://152.228.217.228:8000/"+args;
        int responseCode = 0;
        String responseString = null;
        String line = null;

        /**
         * Ici, nous faisons un test de connection avec un try / catch au cas où il y a un problème
         */
        HttpURLConnection httpClient = null;
        try {
            httpClient = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        /**
         * Ici, nous donnons la méthode de communication dans l'entête. Ici nous transmettons que GET car pour
         * une raison inconnu POST ne fonctionne pas
         */
        try {
            httpClient.setRequestMethod("GET");
        } catch (ProtocolException protocolException) {
            protocolException.printStackTrace();
        }

        /**
         * Nous donnons encore des informations dans le header de la requete
         */
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

        try {
            responseCode = httpClient.getResponseCode();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        /**
         * Nous affichons la requete ainsi que le code de réponse
         */
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        /**
         * Ici, nous effectuons la requete et nous lison la réponse ligne par ligne
         */
        try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();

            while (true) {
                try {
                    if ((line = in.readLine()) == null) break;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                response.append(line);
            }
            responseString = response.toString();
            //System.out.println(responseString);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        /**
         * Enfin, nous retournons la réponse
         */
        return responseString;
    }
}
