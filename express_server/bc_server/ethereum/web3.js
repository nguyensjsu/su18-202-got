const Web3 = require("web3");
const keys = require("../config/keys");
const HDWalletProvider = require("truffle-hdwallet-provider");
const { mnemonic_rinkeby, provider_url } = require("../config/keys");
const provider = new HDWalletProvider(mnemonic_rinkeby, provider_url);
// import {provider_url} from '../config/keys';

//with Next server, window is not directly accessble
// const web3 = new Web3(window.web3.currentProvider);
//
// let web3;
//
// if (typeof window !== "undefined" && window.web3 !== "undefined") {
//   //we are in browser & metamask is running
//   web3 = new Web3(window.web3.currentProvider);
// } else {
//   //we are on the server OR the user is not using metamask
//
//   const provider = new Web3.providers.HttpProvider(keys.provider_url);
//   web3 = new Web3(provider);
// }

const web3 = new Web3(provider);

module.exports = web3;
