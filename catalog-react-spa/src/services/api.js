import axios from "axios";

export function fetchProducts() {
  return axios(`http://localhost:8181/api/products`);
}
