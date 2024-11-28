package com.example.Booking_Care_Web.auth;

import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private UserServiceImpl userService;

    @Autowired
    public CustomOAuth2SuccessHandler(UserServiceImpl userService, OAuth2AuthorizedClientService authorizedClientService) {
        this.userService = userService;
        this.authorizedClientService = authorizedClientService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        String accessToken = getAccessToken(oauthToken);
        String provider = oauthToken.getAuthorizedClientRegistrationId();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        if(provider.equals("github")){
            email = getEmailFromGitHub(accessToken);
        }

        User checkUser = userService.findByEmail(email);
        if (checkUser == null) {
            User user = new User();
            user.setUserId(userService.createNewUserId("pt"));
            user.setEmail(email);
            user.setName(name);

            userService.saveUser(user);
        }

        response.sendRedirect("/dashboard"); // Chuyển hướng đến trang dashboard
    }

    private String getAccessToken(OAuth2AuthenticationToken oauthToken) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName()
        );
        return client.getAccessToken().getTokenValue();
    }

    public String getEmailFromGitHub(String accessToken) {
        // URL API GitHub để lấy email
        String apiUrl = "https://api.github.com/user/emails";

        // Cấu hình header với access token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Gọi API GitHub
        org.springframework.web.client.RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        try {
            // Phân tích JSON trả về
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> emails = objectMapper.readValue(response.getBody(), List.class);

            // Duyệt qua danh sách email để tìm email chính
            for (Map<String, Object> emailInfo : emails) {
                if ((Boolean) emailInfo.get("primary")) {
                    return (String) emailInfo.get("email");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Không tìm thấy email chính
    }

}
