package api.endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import api.utilities.reporting.ExtentReportManager;
import api.payload.User;
import api.utilities.helpers.RestReqFilter;

import static io.restassured.RestAssured.given;

import java.util.Map;

public class RestUtils {

    public static RestReqFilter restReqFilter;

    public static RequestSpecification getRequestSpecification(String endPoint, Object requestPayload, Map<String,String>headers) {
        return RestAssured.given()
                .baseUri(endPoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(requestPayload);
    }

    public static void printRequestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("Endpoint is " + queryableRequestSpecification.getBaseUri());
        ExtentReportManager.logInfoDetails("Method is " + queryableRequestSpecification.getMethod());
        ExtentReportManager.logInfoDetails("Headers are ");
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Request body is ");
        ExtentReportManager.logJson(queryableRequestSpecification.getBody());
    }

    public static void printResponseLogInReport(Response response) {
        ExtentReportManager.logInfoDetails("Response status is " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response Headers are ");
        ExtentReportManager.logHeaders(response.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Response body is ");
        ExtentReportManager.logJson(response.getBody().prettyPrint());
    }

    public static void printCurlLogInReport(String capturedCurl) {
        ExtentReportManager.logInfoDetails("Captured Curl is ");
        ExtentReportManager.logCurl(capturedCurl);
    }

    public static Response performPost(String endPoint, String requestPayload, Map<String,String>headers) {

        restReqFilter = new RestReqFilter();

        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);
        Response response =  requestSpecification.filter(restReqFilter).post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        printCurlLogInReport(restReqFilter.getCapturedCurl());
        return response;
    }

    public static Response performPost(String endPoint, Map<String, Object> requestPayload, Map<String,String>headers) {

        restReqFilter = new RestReqFilter();

        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);
        Response response =  requestSpecification.filter(restReqFilter).post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        printCurlLogInReport(restReqFilter.getCapturedCurl());
        return response;
    }

    public static Response performPost(String endPoint, Object requestPayloadAsPojo, Map<String,String>headers) {

        restReqFilter = new RestReqFilter();

        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayloadAsPojo, headers);
        Response response =  requestSpecification.filter(restReqFilter).post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        printCurlLogInReport(restReqFilter.getCapturedCurl());
        return response;
    }

    public static Response performPostDraft(String endPoint, String requestPayload, Map<String, String> headers) {

        restReqFilter = new RestReqFilter();

        return RestAssured.given().log().all()
            .baseUri(endPoint)
            .headers(headers).filter(restReqFilter)
            .contentType(ContentType.JSON)
            .body(requestPayload)
            .post()
            .then().log().all().extract().response();
    }
    
    public static Response createUser(String endPoint, Object requestPayloadAsPojo, Map<String,String>headers)
	{
		restReqFilter = new RestReqFilter();
		
		RequestSpecification requestSpecification = RestUtils.getRequestSpecification(endPoint, requestPayloadAsPojo, headers);
        Response response =  requestSpecification.filter(restReqFilter).post();
		
		RestUtils.printRequestLogInReport(requestSpecification);
		RestUtils.printResponseLogInReport(response);
		RestUtils.printCurlLogInReport(restReqFilter.getCapturedCurl());
			
		return response;
	}
}
