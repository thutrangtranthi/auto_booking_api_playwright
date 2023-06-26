package com.api.service.auth;

import com.api.data.auth.CreateTokenData;
import com.api.service.RequestManager;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.json.JSONObject;


public class CreateTokenService {
    public APIResponse getResponse(CreateTokenData createTokenData, RequestManager requestManager) {
        return requestManager.postRequest("/auth", RequestOptions.create()
                .setData(createTokenData));
    }

    public int getStatusCode(CreateTokenData createTokenData, RequestManager requestManager) {
        return getResponse(createTokenData, requestManager).status();

    }

    public String getTokenValue(CreateTokenData createTokenData, RequestManager requestManager) {
        return new JSONObject(getResponse(createTokenData, requestManager)
                .text())
                .getString("token");
    }

    public APIResponse getResponseWithWrongMethod(CreateTokenData createTokenData, RequestManager requestManager) {
        return requestManager.getRequest("/auth", RequestOptions.create()
                .setData(createTokenData));
    }

    public APIResponse getResponseWithWrongUrl(CreateTokenData createTokenData, RequestManager requestManager) {
        return requestManager.postRequest("/auth12345", RequestOptions.create()
                .setData(createTokenData));
    }

    public APIResponse getResponseWithWrongBody(CreateTokenData createTokenDataWrong, RequestManager requestManager) {
        return requestManager.postRequest("/auth", RequestOptions.create()
                .setData(createTokenDataWrong));
    }





}
