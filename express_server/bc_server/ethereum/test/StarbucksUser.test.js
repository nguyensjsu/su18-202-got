const assert = require("assert");
const ganache = require("ganache-cli");
const Web3 = require("web3");
const web3 = new Web3(ganache.provider());

const compiledUsers = require("../build/StarbucksUsers.json");

let accounts;
let starbucksUsers;

beforeEach(async () => {
  accounts = await web3.eth.getAccounts();
  starbucksUsers = await new web3.eth.Contract(JSON.parse(compiledUsers.interface))
    .deploy({ data: compiledUsers.bytecode })
    .send({ from: accounts[0], gas: 5000000 });
});

describe("Starbucks Users", () => {
  it("deployed user list has addresses", () => {
    assert.ok(starbucksUsers.options.address);
  });

  it("it's the user list manager who created the list", async () => {
    const manager = await starbucksUsers.methods.manager().call();
    assert.deepStrictEqual(manager, accounts[0]);
  });

  it("added user can be fetched by findOne()", async () => {
    await starbucksUsers.methods
      .addUser("123", "abc@test.com", "James")
      .send({ from: accounts[5], gas: "1000000" });
    const user = await starbucksUsers.methods.findOne(accounts[5]).call();
    assert.deepStrictEqual(user[0], "123");
    assert.deepStrictEqual(user[1], "abc@test.com");
    assert.deepStrictEqual(user[2], "James");
  });

  it("added user can be fetched by findSelf()", async () => {
    await starbucksUsers.methods
      .addUser("123", "abc@test.com", "James")
      .send({ from: accounts[4], gas: "1000000" });

    //for functions with no parameter, add msg.sender in call()
    const user = await starbucksUsers.methods.findSelf().call({from: accounts[4]});
    assert.deepStrictEqual(user[0], "123");
    assert.deepStrictEqual(user[1], "abc@test.com");
    assert.deepStrictEqual(user[2], "James");
  });

  it("added user has the default card with balance of $20", async () => {
    await starbucksUsers.methods
      .addUser("123", "abc@test.com", "James")
      .send({ from: accounts[3], gas: "1000000" });

    //for functions with no parameter, add msg.sender in call()
    const card = await starbucksUsers.methods.showCard().call({from: accounts[3]});
    assert.deepStrictEqual(card[0], "000000000");
    assert.deepStrictEqual(card[1], "000");
    assert.deepStrictEqual(card[2], "20");
  });

  it("added cards can be fetched by showCard()", async () => {
    await starbucksUsers.methods
      .addCard("123456", "789", 200)
      .send({ from: accounts[1], gas: "1000000" });

    //for functions with no parameter, add msg.sender in call()
    const card = await starbucksUsers.methods.showCard().call({from: accounts[1]});
    assert.deepStrictEqual(card[0], "123456");
    assert.deepStrictEqual(card[1], "789");
    assert.deepStrictEqual(card[2], "200");
  });
});
