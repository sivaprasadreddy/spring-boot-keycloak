import React from "react";

const Products = props => {
  let productRows = props.products.map(product => {
      return (
          <tr key={product.id} >
            <td>{product.id}</td>
            <td>{product.name}</td>
            <td>{product.originalPrice}</td>
            <td>{product.discount}</td>
            <td>{product.price}</td>
          </tr>
      );
    });

  return (
      <div className={"container"}>

        <div className="row">
          <table className="table">
            <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Original Price</th>
              <th>Discount</th>
              <th>Price</th>
            </tr>
            </thead>
            <tbody>
            {productRows}
            </tbody>
          </table>

        </div>
      </div>
  );
}

export default Products;
