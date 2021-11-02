import React from "react";
import {NavLink} from "react-router-dom";
import {isUserAuthenticated, logout} from '../services/user-service';
import {keycloakLogin, keycloakLogout, keycloakRedirectUri} from "../keycloak-service";

class NavBar extends React.Component {

  loginHandler = () => {
    keycloakLogin({
      "redirectUri": keycloakRedirectUri
    });
  };

  logoutHandler = () => {
    logout();
    keycloakLogout();
    window.location = "/";
  };

  render() {
    let isAuthenticated = isUserAuthenticated();
      let authenticatedLinks;

      if (isAuthenticated) {
          authenticatedLinks = (
              <>

              <li className="nav-item">
                  <NavLink className="nav-link" to="/login" onClick={this.logoutHandler}>
                      Logout
                  </NavLink>
              </li>
              </>
          );
      } else {
          authenticatedLinks = (
              <li className="nav-item">
                  <NavLink className="nav-link" to="/login" onClick={this.loginHandler}>
                      Login
                  </NavLink>
              </li>
          );
      }

    return (
      <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <NavLink className="navbar-brand" to="/">
          Catalog
        </NavLink>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarCollapse"
          aria-controls="navbarCollapse"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarCollapse">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <NavLink className="nav-link" to="/">
                Home <span className="sr-only">(current)</span>
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/products">
                Products
              </NavLink>
            </li>
          </ul>
          <ul className="navbar-nav">
              {authenticatedLinks}
          </ul>
        </div>
      </nav>
    );
  }
}

export default NavBar;
