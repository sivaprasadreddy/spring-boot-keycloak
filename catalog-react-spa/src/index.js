import React from "react";
import ReactDOM from "react-dom";
import {HashRouter} from 'react-router-dom';
import {createBrowserHistory} from "history";
import {initKeycloak} from './keycloak-service';
import App from "./App";
import "./index.css";
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap/dist/js/bootstrap.js';
import 'jquery';
import 'popper.js';

export const history = createBrowserHistory();

const renderApp = () => ReactDOM.render(
    <HashRouter>
      <App />
    </HashRouter>,
    document.getElementById("root")
);

initKeycloak(renderApp);
