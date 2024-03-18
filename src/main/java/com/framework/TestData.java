package com.framework;

import java.io.FileInputStream;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestData {
	/* Using HashMap instead of HashTable as we can have null values from TDS */
	private HashMap<String, String> objToReturn = new HashMap<>();
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	/**
	 * @Method - getDataFromAPI
	 * @Description - To read TDS data values from TOSCA TDM
	 * @return HashMap<String, String>
	 * @author - aggarkan
	 * @DateCreated - 21-09-2020
	 * @DateModified - 04-01-2021
	 */
	public HashMap<String, String> getDataFromAPI(String repositoryName, String typeName) {
		try {
			String tokenURL = getConfigData("TOSCA_TOKEN_ENDPOINT");
			OAuthClient client = new OAuthClient(new URLConnectionClient());
			// Requesting Token from TOSCA TDM Token EndPoint
			OAuthClientRequest request = OAuthClientRequest.tokenLocation(tokenURL)
					.setGrantType(GrantType.CLIENT_CREDENTIALS).setClientId(getConfigData("TOSCA_clientID"))
					.setClientSecret(getConfigData("TOSCA_clientSecret")).buildBodyMessage();

			// Extracting access Token from request
			String accessToken = client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class)
					.getAccessToken();
			String repositoryURL = getConfigData("TOSCA_REPOSITORY_ENDPOINT");
			// Building repositoryURL based on repository name and type
			repositoryURL = repositoryURL + repositoryName + "/types/" + typeName + "/items";

			// Requesting TDSRepostory and TDSType using above extracted access Token
			request = new OAuthBearerClientRequest(repositoryURL).setAccessToken(accessToken).buildHeaderMessage();
			OAuthResourceResponse resourceResponse = client.resource(request, OAuth.HttpMethod.GET,
					OAuthResourceResponse.class);

			// Saving JSON response to String
			String apiResponse = resourceResponse.getBody();

			// Converting API response to JSONObject class
			JSONObject json = new JSONObject(apiResponse);

			// Extracting items root element from JSON Structure
			JSONArray result = json.getJSONArray("items");
			JSONObject resultSet;
			JSONObject dataSet;
			String dataParameter;
			String dataValue;
			for (int i = 0; i < result.length(); i++) {
				resultSet = result.getJSONObject(i);
				dataSet = resultSet.getJSONObject("data");
				dataParameter = dataSet.getString("Data_Parameter");
				dataValue = dataSet.getString("Data_Value");
				objToReturn.put(dataParameter, dataValue);
			}
		} catch (Exception exn) {
			log.error(exn + "Fail to get data from API");
			return objToReturn;
		}
		return objToReturn;
	}
	
	/**
	 * @Method - getDataFromAPIGeneric
	 * @Description - To read TDS data values from TOSCA TDM if default params are not used. ID from TDS will be used as key. Hence it should always be unique
	 * @return HashMap<String, String>
	 * @author - singhje1
	 * @DateCreated - 07-11-2021
	 * @DateModified - ?
	 */
	public HashMap<String, JSONObject> getDataFromAPIGeneric(String repositoryName, String typeName) {
		HashMap<String, JSONObject> objToReturn = new HashMap<String, JSONObject>();
		try {
			String tokenURL = getConfigData("TOSCA_TOKEN_ENDPOINT");
			OAuthClient client = new OAuthClient(new URLConnectionClient());
			// Requesting Token from TOSCA TDM Token EndPoint
			OAuthClientRequest request = OAuthClientRequest.tokenLocation(tokenURL)
					.setGrantType(GrantType.CLIENT_CREDENTIALS).setClientId(getConfigData("TOSCA_clientID"))
					.setClientSecret(getConfigData("TOSCA_clientSecret")).buildBodyMessage();

			// Extracting access Token from request
			String accessToken = client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class)
					.getAccessToken();
			String repositoryURL = getConfigData("TOSCA_REPOSITORY_ENDPOINT");
			// Building repositoryURL based on repository name and type
			repositoryURL = repositoryURL + repositoryName + "/types/" + typeName + "/items";

			// Requesting TDSRepostory and TDSType using above extracted access Token
			request = new OAuthBearerClientRequest(repositoryURL).setAccessToken(accessToken).buildHeaderMessage();
			OAuthResourceResponse resourceResponse = client.resource(request, OAuth.HttpMethod.GET,
					OAuthResourceResponse.class);

			// Saving JSON response to String
			String apiResponse = resourceResponse.getBody();

			// Converting API response to JSONObject class
			JSONObject json = new JSONObject(apiResponse);

			// Extracting items root element from JSON Structure
			JSONArray result = json.getJSONArray("items");
			JSONObject resultSet;
			JSONObject dataSet;
			String id = null;
			for (int i = 0; i < result.length(); i++) {
				resultSet = result.getJSONObject(i);
				dataSet = resultSet.getJSONObject("data");
				id = resultSet.getString("id");
				objToReturn.put(id, dataSet);
			}
		} catch (Exception exn) {
			log.error(exn + "Fail to get data from API");
			return objToReturn;
		}
		return objToReturn;
	}

	/**
	 * @Method - getData
	 * @Description - To read config.properties file
	 * @return String
	 * @author - aggarkan
	 * @DateCreated - 22-09-2020
	 * @DateModified - 04-01-2021
	 */
	public static String getConfigData(String elementName) throws Exception {
		Properties properties;
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		FileInputStream locator = null;
		try {
			encryptor = new StandardPBEStringEncryptor();
			encryptor.setPassword(getUserData("USER_KEY"));
			new EncryptableProperties(encryptor);
			locator = new FileInputStream("config.properties");
			properties = new EncryptableProperties(encryptor);
			properties.load(locator);
			return properties.getProperty(elementName);
		} catch (Exception e) {
			log.error(e + "Fail to get Data");
			return "";
		} finally {
			locator.close();
		}
	}

	/**
	 * @Method - getUserData
	 * @Description - To read config.properties file
	 * @return String
	 * @author - aggarkan
	 * @DateCreated - 22-09-2020
	 * @DateModified - 04-01-2021
	 */
	public static String getUserData(String elementName) throws Exception {
		Properties properties;
		FileInputStream locator = null;
		try {
			locator = new FileInputStream("user.properties");
			properties = new Properties();
			properties.load(locator);
			return properties.getProperty(elementName);
		} catch (Exception e) {
			log.error(e + "Fail to get user data");
			return "";
		} finally {
			locator.close();
		}
	}

	/**
	 * @Method - writeDataToAPI
	 * @Description - To write Test data values to TOSCA TDM
	 * @author - aggarkan
	 * @DateCreated - 02-02-2021
	 */
	public String writeDataToAPI(String repositoryName, String typeName, JSONObject json) {
		try {
			String tokenURL = getConfigData("TOSCA_TOKEN_ENDPOINT");
			OAuthClient client = new OAuthClient(new URLConnectionClient());
			// Requesting Token from TOSCA TDM Token EndPoint
			OAuthClientRequest request = OAuthClientRequest.tokenLocation(tokenURL)
					.setGrantType(GrantType.CLIENT_CREDENTIALS).setClientId(getConfigData("TOSCA_clientID"))
					.setClientSecret(getConfigData("TOSCA_clientSecret"))
					.buildBodyMessage();

			// Extracting access Token from request
			String accessToken = client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class)
					.getAccessToken();
			String repositoryURL = getConfigData("TOSCA_REPOSITORY_ENDPOINT");

			// Building repositoryURL based on repository name and type
			repositoryURL = repositoryURL + repositoryName + "/types/" + typeName + "/items";

			// Requesting TDSRepostory and TDSType using above extracted access Token
			request = new OAuthBearerClientRequest(repositoryURL)
					.setAccessToken(accessToken)
					.buildHeaderMessage();

			// Saving Json Data to String
			String params = json.toString();

			// In Headers, set Content-Type as header and application/json in value and set
			// data in body
			request.setHeader(OAuth.HeaderType.CONTENT_TYPE, "application/json");
			request.setBody("{ data:" + params + "}");

			OAuthResourceResponse resourceResponse = client.resource(request, OAuth.HttpMethod.POST,
					OAuthResourceResponse.class);

			// return id of response
			String body = resourceResponse.getBody();
			int idd = body.indexOf("id");
			String id = body.substring((idd + 5), (idd + 41));
			return id;
		} catch (Exception exn) {
			log.error(exn + "Fail to get data from API");
			return "Fail";
		}
	}

	/**
	 * @Method - updateDataToAPI
	 * @Description - To update Test data values to TOSCA TDM
	 * @author - aggarkan
	 * @DateCreated - 24-02-2021
	 */
	public void updateDataToAPI(String repositoryName, String typeName, String id, JSONObject json) {
		try {
			String tokenURL = getConfigData("TOSCA_TOKEN_ENDPOINT");
			OAuthClient client = new OAuthClient(new URLConnectionClient());
			// Requesting Token from TOSCA TDM Token EndPoint
			OAuthClientRequest request = OAuthClientRequest.tokenLocation(tokenURL)
					.setGrantType(GrantType.CLIENT_CREDENTIALS).setClientId(getConfigData("TOSCA_clientID"))
					.setClientSecret(getConfigData("TOSCA_clientSecret"))
					.buildBodyMessage();

			// Extracting access Token from request
			String accessToken = client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class)
					.getAccessToken();
			String repositoryURL = getConfigData("TOSCA_REPOSITORY_ENDPOINT");

			// Building repositoryURL based on repository name and type
			repositoryURL = repositoryURL + repositoryName + "/types/" + typeName + "/items/" + id;

			// Requesting TDSRepostory and TDSType using above extracted access Token
			request = new OAuthBearerClientRequest(repositoryURL)
					.setAccessToken(accessToken)
					.buildHeaderMessage();

			// Saving Json Data to String
			String params = json.toString();

			// In Headers, set Content-Type as header and application/json in value and set
			// data in body
			request.setHeader(OAuth.HeaderType.CONTENT_TYPE, "application/json");
			request.setBody("{ data:" + params + "}");

			OAuthResourceResponse resourceResponse = client.resource(request, OAuth.HttpMethod.PUT,
					OAuthResourceResponse.class);

		} catch (Exception exn) {
			log.error(exn + "Fail to get data from API");
		}
	}
}
