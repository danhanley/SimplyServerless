package com.simplyserverless.example1;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by dan on 13/09/16.
 *
 * ##  See http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-mapping-template-reference.html
 body-json={}, params={path={}, querystring={q1=Fred, q2=Perry}, header={}}, stage-variables={}, context={account-id=534737636834, api-id=qvpphez67b, api-key=test-invoke-api-key, authorizer-principal-id=, caller=534737636834, cognito-authentication-provider=, cognito-authentication-type=, cognito-identity-id=, cognito-identity-pool-id=, http-method=GET, stage=test-invoke-stage, source-ip=test-invoke-source-ip, user=534737636834, user-agent=Apache-HttpClient/4.5.x (Java/1.8.0_102), user-arn=arn:aws:iam::534737636834:root, request-id=test-invoke-request, resource-id=qnutaqa1o0, resource-path=/}

 */
public class Request {



    public Map<String, String> bodyJSON;
    public Map<String, Map<String,String>> params;
    public Map<String, String> stageVariables;
    public Map<String, String> context;

    public Map<String, String> getBodyJSON() {
        return bodyJSON;
    }

    public void setBodyJSON(Map<String, String> bodyJSON) {
        this.bodyJSON = bodyJSON;
    }

    public Map<String, Map<String, String>> getParams() {
        return params;
    }

    public void setParams(Map<String, Map<String, String>> params) {
        this.params = params;
    }

    public Map<String, String> getStageVariables() {
        return stageVariables;
    }

    public void setStageVariables(Map<String, String> stageVariables) {
        this.stageVariables = stageVariables;
    }

    public Map<String, String> getContext() {
        return context;
    }

    public void setContext(Map<String, String> context) {
        this.context = context;
    }

}