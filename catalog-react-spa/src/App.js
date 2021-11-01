import React from "react";
import {Route, Switch} from "react-router-dom";
import NavBar from "./components/NavBar";

import Home from "./pages/Home";
import ProductList from "./pages/ProductList";

class App extends React.Component {
  render() {
    return (
        <div className="App">
          <NavBar />
          <main role="main" className="container-fluid">
            <Switch>
              <Route exact path="/" component={Home}/>
              <Route path="/products" component={ProductList} />

            </Switch>
          </main>

        </div>
    );
  }
}

export default App;
