import Keycloak from "keycloak-js";
import {setUser} from './services/user-service';

export const keycloak = Keycloak({
      "realm": "siva-realm",
      "url": "http://localhost:9191/auth",
      "clientId": "catalog-react-client"
    }
);

export const initKeycloak = (onInitCallback) => {
  keycloak.init({
    onLoad: 'check-sso',
  }).then(authenticated => {
    if (authenticated) {
      keycloak.loadUserProfile().then(profile => {
        //console.log("Profile:", profile);
        setUser({
          username: profile.username,
          email: profile.email,
          access_token: keycloak.token,
          roles: keycloak.tokenParsed.realm_access.roles,
          refresh_token: keycloak.refreshToken
        });
      });
    }
    onInitCallback();
  })
};

export const keycloakLogin = keycloak.login;
export const keycloakLogout = keycloak.logout;
export const keycloakRedirectUri = "http://localhost:3000/";