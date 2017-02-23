package hkust.fypta1;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by kongchingyiii on 5/10/16.
 */

public class NetworkClientImp implements NetworkClient{

//    public void postSharing(String item, Context context) throws Exception{}
//
//    public int postMessage(String message, Context context) throws Exception{return 0;}
//
//    public Follower getFollowerByUserId(String id) throws Exception{return null;}
//
//    public List<Event> getEventsByCat(String category) throws Exception{return null;}
//
//    public List<Event> getEventsByLocation(String GPS) throws Exception{return null;}
//
//    public List<Message> getMessages(String userId, Context context) throws Exception{return null;}
//
//    public String getUserNameById(Long userId) throws Exception{return null;}



        private String serverUrl;
        private NetworkProvider networkProvider;
        public static Event event;


        public NetworkClientImp(String serverUrl, NetworkProvider networkProvider) {
            this.serverUrl = serverUrl;
            this.networkProvider = networkProvider;
        }


        public Event fetchEvent() throws Exception {

            String stringUrl = this.serverUrl;

//            new EventFragment().DownloadWebpageTask().execute(stringUrl);
            return event;

        }

    public HttpURLConnection connection(URL url) throws IOException {

        HttpURLConnection conn = new DefaultNetworkProvider().getConnection(url);
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setDoInput(true);

        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Accept", "text/plain");

        return conn;

    }

    public String getEvent(HttpURLConnection conn) throws IOException {
        StringBuilder out = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append("\n");
            }

            String result = out.toString();
            return result;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }






    private String url;

        private int responseCode;
        private String message;

        private String response;

        public String getResponse() {
            return response;
        }

        public String getErrorMessage() {
            return message;
        }

        public int getResponseCode() {
            return responseCode;
        }

        public NetworkClientImp(String url)
        {
            this.url = url;

        }

//
//
//        public void Execute(RequestMethod method) throws Exception
//        {
//            switch(method) {
//                case GET:
//                {
//                    //add parameters
//                    String combinedParams = "";
//                    if(!params.isEmpty()){
//                        combinedParams += "?";
//                        for(NameValuePair p : params)
//                        {
//                            String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(),”UTF-8″);
//                            if(combinedParams.length() > 1)
//                            {
//                                combinedParams  +=  "&" + paramString;
//                            }
//                            else
//                            {
//                                combinedParams += paramString;
//                            }
//                        }
//                    }
//
//                    HttpGet request = new HttpGet(url + combinedParams);
//
//                    //add headers
//                    for(NameValuePair h : headers)
//                    {
//                        request.addHeader(h.getName(), h.getValue());
//                    }
//
//                    executeRequest(request, url);
//                    break;
//                }
//                case POST:
//                {
//                    HttpPost request = new HttpPost(url);
//
//                    //add headers
//                    for(NameValuePair h : headers)
//                    {
//                        request.addHeader(h.getName(), h.getValue());
//                    }
//
//                    if(!params.isEmpty()){
//                        request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                    }
//
//                    executeRequest(request, url);
//                    break;
//                }
//            }
//        }
//
//        private void executeRequest(HttpUriRequest request, String url)
//        {
//            HttpClient client = new DefaultHttpClient();
//
//            HttpResponse httpResponse;
//
//            try {
//                httpResponse = client.execute(request);
//                responseCode = httpResponse.getStatusLine().getStatusCode();
//                message = httpResponse.getStatusLine().getReasonPhrase();
//
//                HttpEntity entity = httpResponse.getEntity();
//
//                if (entity != null) {
//
//                    InputStream instream = entity.getContent();
//                    response = convertStreamToString(instream);
//
//                    // Closing the input stream will trigger connection release
//                    instream.close();
//                }
//
//            } catch (ClientProtocolException e)  {
//                client.getConnectionManager().shutdown();
//                e.printStackTrace();
//            } catch (IOException e) {
//                client.getConnectionManager().shutdown();
//                e.printStackTrace();
//            }
//        }
//
//        private static String convertStreamToString(InputStream is) {
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            StringBuilder sb = new StringBuilder();
//
//            String line = null;
//            try {
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line + "\n");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return sb.toString();
//        }




}
