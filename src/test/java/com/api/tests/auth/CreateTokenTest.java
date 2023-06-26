package com.api.tests.auth;

import com.api.data.auth.CreateTokenData;
import com.api.service.auth.CreateTokenService;
import com.api.tests.BaseTest;
import com.microsoft.playwright.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import static com.api.data.auth.CreateTokenBuilder.setWrongTokenData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Slf4j
public class CreateTokenTest extends BaseTest {

    @Test(description = "Should create token successfully")
    public void shouldCreateTokenSuccessfully() {
        CreateTokenService createTokenService = new CreateTokenService();
        int responseStatusCode = createTokenService.getStatusCode(createTokenData, requestManager);
        assertEquals(responseStatusCode, 200, "Status code should be 200");
        String tokenValue = createTokenService.getTokenValue(createTokenData, requestManager);
        assertNotNull(tokenValue, "Token value should be not null");
    }

    @Test(description = "Should response status code 405 when create token with wrong method")
    public void shouldCreateTokenFailWithWrongMethod() {
        CreateTokenService createTokenService = new CreateTokenService();
        APIResponse response = createTokenService.getResponseWithWrongMethod(createTokenData, requestManager);
        int responseStatusCode = response.status();
        assertEquals(responseStatusCode, 405, "Status code should be 405 when send request with wrong method");
    }

    @Test(description = "Should response status code 404 when create token with wrong url")
    public void shouldCreateTokenFailWithWrongUrl() {
        CreateTokenService createTokenService = new CreateTokenService();
        APIResponse response = createTokenService.getResponseWithWrongUrl(createTokenData, requestManager);
        int responseStatusCode = response.status();
        assertEquals(responseStatusCode, 404, "Status code should be 404 when send request with wrong Url");
    }

    @Test(description = "Should response status code 401 when create token with wrong auth body data")
    public void shouldCreateTokenFailWithWrongBody() {
        CreateTokenData createTokenDataWrong = setWrongTokenData();
        CreateTokenService createTokenService = new CreateTokenService();
        APIResponse response = createTokenService.getResponseWithWrongBody(createTokenDataWrong, requestManager);
        int responseStatusCode = response.status();
        assertEquals(responseStatusCode, 401, "Status code should be 401 when send request with wrong auth data");

    }
}
