package ru.live.kamaz_cs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {

//        String request = "https://query1.finance.yahoo.com/v10/finance/quoteSummary/SPY?modules=price";
        File file = new File("json1.txt");
//        String resultOne = performRequest(request); //получение ответа с сервера в виде строки
        String resultOne = st(file); //получение ответа с сервера в виде строки
//        System.out.println(resultOne);

        Gson gson = new GsonBuilder().create(); //созданный объект Gson используется для серриализации и десерриализации
        JSON json = (JSON) gson.fromJson(resultOne, JSON.class); //из строки JSON (result) восстановить объект класса JSON

        for (Result result : json.quoteSummary.result) {
            System.out.println(result.fundProfile.legalType);
        }

    }

//    private static String performRequest(String urlStr) throws IOException {
//        URL url = new URL(urlStr);
//        StringBuilder sb = new StringBuilder();
//
//        HttpURLConnection http = (HttpURLConnection) url.openConnection();
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
//            char[] buf = new char[1000000];
//
//            int r = 0;
//            do {
//                if ((r = br.read(buf)) > 0)
//                    sb.append(new String(buf, 0, r));
//            } while (r > 0);
//        } finally {
//            http.disconnect();
//        }
//
//        return sb.toString();
//    }

    private static String st(File file) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str = null;
            for (; (str = br.readLine()) != null;) {
                sb.append(str).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
