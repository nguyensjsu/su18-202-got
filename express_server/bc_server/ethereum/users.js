const web3 = require("./web3");
const StarbucksUsers = require("./build/StarbucksUsers.json");
const keys = require("../config/keys");
// import { deployed_address } from "../config/keys";

const instance = new web3.eth.Contract(
  JSON.parse(StarbucksUsers.interface),
  keys.deployed_address
);

module.exports = instance;
