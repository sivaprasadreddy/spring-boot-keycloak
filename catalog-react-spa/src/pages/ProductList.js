import React from "react";
import * as api from "../services/api";
import Products from "../components/Products";

class ProductList extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      products: []
    };
  }

  componentDidMount() {
    this.fetchProducts();
  }

  fetchProducts = () => {
    api.fetchProducts()
      .then(response => {
        this.setState({products: response.data})
      })
      .catch(e => console.log("error", e));
  }


  render() {
    return (
        <div className="row">
          <div className="col-md-9">

            <Products
              products={this.state.products}
            />
          </div>

        </div>
    );
  }
}

export default ProductList;
