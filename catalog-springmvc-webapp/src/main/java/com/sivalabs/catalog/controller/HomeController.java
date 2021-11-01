package com.sivalabs.catalog.controller;

import com.sivalabs.catalog.model.AuthUserModel;
import com.sivalabs.catalog.service.SecurityHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("")
    public String home(Model model) {
        AuthUserModel loginUser = SecurityHelper.getLoginUser();
        if(loginUser != null) {
            model.addAttribute("username", loginUser.getFullName());
        } else {
            model.addAttribute("username", "Guest");
        }
        return "home";
    }

    @GetMapping("/user")
    public String user(Model model) {
        AuthUserModel loginUser = SecurityHelper.getLoginUser();
        model.addAttribute("loginUser", loginUser);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        OidcIdToken idToken = principal.getIdToken();
        OidcUserInfo userInfo = principal.getUserInfo();

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
            oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());

        String accessToken = client.getAccessToken().getTokenValue();

        log.info("accessToken: {}", accessToken);
        log.info("idToken: {}", idToken.getTokenValue());
        log.info("userInfo.getClaims() = {}", userInfo.getClaims());

        return "user";
    }

}
