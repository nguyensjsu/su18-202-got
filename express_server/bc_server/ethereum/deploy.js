const HDWalletProvider = require("truffle-hdwallet-provider");
const Web3 = require("web3");
const { mnemonic_rinkeby, provider_url } = require("../config/keys");
const compiledUsers = require("./build/StarbucksUsers.json");
const provider = new HDWalletProvider(mnemonic_rinkeby, provider_url);

const web3 = new Web3(provider);

const deploy = async () => {
  const accounts = await web3.eth.getAccounts();

  console.log(`Attempting to deploy from account: ${accounts[0]}`);
  const result = await new web3.eth.Contract(
    JSON.parse(compiledUsers.interface)
  )
    .deploy({ data: "0x" + compiledUsers.bytecode })
    .send({ from: accounts[0], gas: 5000000 });

  console.log(`Contract deployed to: ${result.options.address}`);
};
deploy();
