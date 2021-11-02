import {cleanState, getAccessToken, setUserAuth, isAuthenticated} from "./localStorage";

export const setUser = (user) => {
  setUserAuth(user);
}

export const logout = () => {
  cleanState();
}

export const getUserAccessToken = () => {
  return getAccessToken();
}

export const isUserAuthenticated = () => {
  return isAuthenticated();
}