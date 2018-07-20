pragma solidity ^0.4.17;

contract StarbucksUsers {

  struct Card{
    string cardId;
    string cardCode;
    uint bal;
  }

  struct User {
    string googleId;
    string email;
    string firstName;
    address addr;
    Card[] cards;
  }
  address public manager;
  mapping(address => User) users;

  modifier managerOnly(){
    require(manager == msg.sender);
    _;
  }

  constructor() public {
    manager = msg.sender;
  }

  function addUser (string _id, string _email, string _firstName) public {
    Card memory card = Card({cardId: "000000000", cardCode: "000", bal: 20});
    users[msg.sender].googleId = _id;
    users[msg.sender].email = _email;
    users[msg.sender].firstName = _firstName;
    users[msg.sender].addr = msg.sender;
    users[msg.sender].cards.push(card);
  }

  function findOne(address _addr) public view returns (string,string, string) {
    return (users[_addr].googleId, users[_addr].email, users[_addr].firstName);
  }

  function findSelf() public view returns (string,string, string) {
    return (users[msg.sender].googleId, users[msg.sender].email, users[msg.sender].firstName);
  }

  function addCard (string _id, string _code, uint _bal) public{
    Card memory card = Card({cardId: _id, cardCode: _code, bal: _bal});
    users[msg.sender].cards.push(card);
  }

  function showCard() public view returns(string, string, uint){
    uint last = users[msg.sender].cards.length -1;
    Card memory lastCard = users[msg.sender].cards[last];
    return (lastCard.cardId, lastCard.cardCode, lastCard.bal);
  }

  function payByCard (uint amount) public payable{
    require(msg.value == amount);
    uint last = users[msg.sender].cards.length -1;
    uint newBal = users[msg.sender].cards[last].bal-amount;
    addCard(
      users[msg.sender].cards[last].cardId,
      users[msg.sender].cards[last].cardCode,
      newBal
    );
  }
}