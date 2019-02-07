package search;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static final String CACHECONTROL = "Cache-Control";
    private static final String NOCACHE = "no-cache";
    private static final String OUTPUTJSON = "&output=json";

    public static String getResponse(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CACHECONTROL, NOCACHE)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String function(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < args.length; i++) {
            if ( i == 0 ) {
                temp = args[0];
                list.add( temp );
            } else {
                temp = args[i];
                list.add("%20");
                list.add( temp );
            }
        }
        String input = String.join("",list);
        //return URLEncoder.encode(input, "UTF-8");
        return input;
    }

    public static void main( String[] args ) throws Exception {
        String APPID = getAPPID();
        System.out.println("URL : " + "http://api.wolframalpha.com/v2/query?input=" + function(args) + "&appid=" + APPID + OUTPUTJSON);

        String response = getResponse("http://api.wolframalpha.com/v2/query?input=" + function(args) + "&appid=" + APPID + OUTPUTJSON);
        System.out.println(response);
        writeResponseToJson(response);
    }

    private static void writeResponseToJson(String response) throws Exception {
        PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "/response.json", "UTF-8");
        writer.println(response);
        writer.close();
    }

    private static String getAPPID() throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "wolframAPI.txt";
        List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        String[] APPID = lines.get(lines.size()-1).split(" ");
        return APPID[1];
    }
}
