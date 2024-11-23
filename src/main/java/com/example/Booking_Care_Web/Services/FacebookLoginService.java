package com.example.Booking_Care_Web.Services;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FacebookLoginService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        // Lấy thông tin người dùng từ Facebook
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Lấy các thông tin từ Facebook response
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String facebookId = (String) attributes.get("id");
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");
        Map<String, Object> picture = (Map<String, Object>) attributes.get("picture");

        // In ra thông tin để kiểm tra
        System.out.println("Facebook ID: " + facebookId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Picture URL: " + picture.get("url"));


        return oAuth2User;
    }
}
