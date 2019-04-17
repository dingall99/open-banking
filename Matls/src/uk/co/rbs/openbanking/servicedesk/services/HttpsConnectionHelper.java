package uk.co.rbs.openbanking.servicedesk.services;


import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;


public class HttpsConnectionHelper {


    /** Call end-point with Closable HTTP client.*/
    public static HttpResponse callEndPoint(CloseableHttpClient aHTTPClient, String aEndPointURL, StringEntity entity,
                                     Header[] headers, RequestMethod requestMethod) {
        HttpResponse response = null;
        try {
            String query;

            if (requestMethod == RequestMethod.HTTP_GET) {
                HttpGet get = new HttpGet(aEndPointURL);
                get.setHeaders(headers);
                query = get.getURI().toASCIIString();
                System.out.println(query);
                response = aHTTPClient.execute(get);
            }
            else if(requestMethod == RequestMethod.HTTP_POST) {
                HttpPost post = new HttpPost(aEndPointURL);
                post.setHeaders(headers);
                post.setEntity(entity);
                query = post.getURI().toASCIIString();
                System.out.println(query);
                response = aHTTPClient.execute(post);
            }
        } catch (Exception ex) {
            System.out.println("Boom, we failed: " + ex);
            ex.printStackTrace();
        }
        return response;
    }
}
