package tests.QITS;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpPostJsonQITS {
	
	
	public static String method() throws FileNotFoundException {

		JSONObject job = new JSONObject();

		Map<String, Object> labresponse = new LinkedHashMap();
		labresponse.put("Auditid", "130");
		labresponse.put("Plant", "ForFarmers");
		labresponse.put("Contact", "Yogesh Dudhe");
		labresponse.put("Orderdate", "25/7/2022");
		labresponse.put("PONumber", "11");
		labresponse.put("GRD Code", "29111996");
		labresponse.put("Material", "Y material");       

//LinkedHashMap is created for address data
		Map<String, JSONArray> paramlist = new LinkedHashMap();

		JSONArray paramdata = new JSONArray();
		
		Map<String, String> method1 = new LinkedHashMap();
		method1.put("IMParID", "10000002");
		method1.put("IMMethodName", "Acid/alkali digestion-EU 152/2009-GAFTA");
		method1.put("Paramid", "MCH00120");
		method1.put("Paramname", "Selenium (Se)");
		method1.put("UOM", "ppb");
		method1.put("MinValue", "1");
		method1.put("MaxValue", "5");
		method1.put("TargetValue", "3");
		method1.put("ResultValue", "1.25");

//		Map method2 = new LinkedHashMap();
//		method2.put("type1", "home1");
//		method2.put("no", "9738128018");
//		method2.put("type1", "home1");
//		method2.put("no", "9738128018");
//		method2.put("type1", "home1");
//		method2.put("no", "9738128018");
//		method2.put("type1", "home1");
//		method2.put("no", "9738128018");
//		method2.put("type1", "home1");
		
//adding map to list	
		paramdata.put(method1);
//		paramdata.put(method2);

		paramlist.put("Paramdata", paramdata);

		labresponse.put("Paramlist", paramlist);
		job.put("Labresponse", labresponse);
		String payload = job.toString();
		return payload;

	}

    
    public static void main(String[] args) throws Exception {
    	
    	String URI ="https://prod-181.westus.logic.azure.com/workflows/994508f0a91d4d7394f8f72290f5a8e1/triggers/manual/paths/invoke/qitsendpoint/qitsdev743t?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=qI527qw9zIdr5xiJNAyauL19dHAXEHlrRdHAX6F7WuQ";
    	method();
    	String payload = method();

    	System.out.println(payload);
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(URI);
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        System.out.println(response.getStatusLine().getStatusCode());
        String responseString = new BasicResponseHandler().handleResponse(response);
        System.out.println(responseString);
        System.out.println("done");
    }
    

}

