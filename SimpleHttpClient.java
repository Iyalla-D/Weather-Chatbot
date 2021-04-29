package chatbot;

import java.io.*;
import java.net.*;
import java.io.StringWriter;
import java.util.Base64;
import java.util.Base64.*;
import javax.net.ssl.*;

public final class SimpleHttpClient {

    private String auth = null;
    private URL httpurl = null;

    public SimpleHttpClient(URL httpurl_) {
        httpurl = httpurl_;
    }

    public String httpConnect(String outStr, String connectMethod, String userNm,
    String pass) throws Exception {

        //System.out.println("Starting HTTP Request....");
        //Start new HttpURLConnection
        HttpURLConnection con = (HttpURLConnection) httpurl.openConnection();

        // Get Basic Authorization
        if (auth != null) {
            con.setRequestProperty("Authorization", "Basic " + auth);
        }

        if (userNm == null) {
            auth = null;
        }
        auth = userNm + ":" + ((pass == null) ? "" : pass);
        Encoder encoder = Base64.getEncoder();
        auth = encoder.encodeToString(auth.getBytes());

        //To accept anonymous connection without adding any certificates 
        if (con instanceof HttpsURLConnection) {
            HttpsURLConnection https_con = (HttpsURLConnection) con;
            //Create dummy Hostname Verifier
            https_con.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String nm, SSLSession session) {
                    return true;
                }
            });
        }

        //Set connection parameters
        con.setInstanceFollowRedirects(false);
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.setAllowUserInteraction(true);
        con.setRequestMethod(connectMethod.toUpperCase());

        //Get connection response
        if (connectMethod.equals(connectMethod)) {
            con.connect();
            //System.out.println("Connection Response code:" + con.getResponseCode());
        }

        if (connectMethod.equals("POST") || connectMethod.equals("PUT")) {
            OutputStream out = con.getOutputStream();
            out.write(outStr.getBytes(), 0, outStr.getBytes().length);
            out.flush();

            try {
                out.close();
            } catch (Exception e) {
            }
        }

        //Read HTTP response.
        StringWriter strWtr = new StringWriter();
        InputStream instr = null;

        try {
            instr = con.getInputStream();
            int rint = instr.read();
            strWtr.write(rint);
            while (rint > 0) {
                rint = instr.read();
                strWtr.write(rint);
            }
        } catch (Exception ex) {
            //ex.printStackTrace();

            //System.out.println("HTTP esponseCode=" + con.getResponseCode());

            if ((con.getResponseCode() / 100) != 2) {
                throw new IOException(con.getResponseMessage());
            }

        }

        //Printout HTTP Response
        //System.out.println(strWtr.toString());

        try {
            instr.close();
        } catch (Exception ex) {
           // ex.printStackTrace();
        }

        return strWtr.toString();
    }

    // Dummy Trust Manager for secured connection, it does 
    //not validate certificate chains
    static {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
            }
        }};

            SSLContext sc = SSLContext.getInstance("SSL");

            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory sslSocketFactory = sc.getSocketFactory();

            HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
        } catch (Exception e) {
            System.out.println("Error in trust manager...");
            e.printStackTrace();
        }
    }
 
   // Main Method to Run the HTTP Client
     public static void main(String[] arrs) {

        try {
            URL url = new URL("http://javaonlinelessons.com");
            SimpleHttpClient ht = new SimpleHttpClient(url);
            //Connect to a HTTP url with "Connect Method", "User" and "Password" 
            //Use post_str parameter while using HTTP POST to put post data
            ht.httpConnect("post_str", "GET", "user", "password");
        } catch (Exception e) {
           // e.printStackTrace();
        }
    }     
}
