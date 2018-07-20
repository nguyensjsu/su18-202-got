import React, {Component} from 'react';
import { Card, Button } from 'semantic-ui-react';
import web3 from '../../ethereum/web3';
import starbucksUsers from '../../ethereum/users';
import Layout from '../../components/layout';
import { Link } from '../../routes';

class StarbucksIndex extends Component{

  static async getInitialProps(){
    const accounts = await web3.eth.getAccounts();
    console.log(accounts);
    const user = await starbucksUsers.methods.findSelf().call({from: accounts[0]});
    const card = await starbucksUsers.methods.showCard().call({from: accounts[0]});
    // console.log(user);
    return { user, card, accounts };
  }

  renderUser(){

    const items = [
      {
        header: this.props.user[2],
        description: `Email: ${this.props.user[1]}`,
        meta: `Ethereum ID: ${this.props.accounts}`,
        fluid: true
      },
      {
        header: this.props.card[0],
        description: `Balance: ${this.props.card[2]}`,
        meta: "",
        fluid: true
      }
    ]
    return <Card.Group items={items} />
  }

  render() {
    return (
      <Layout>
        <div>
          <h3>Welcome to Starbucks!</h3>
          <Link route="/homes/new">
            <a>
              <Button
                floated = "right"
                content = "Add Card"
                icon = "add"
                primary
              />
            </a>
          </Link>
          {this.renderUser()}
        </div>
      </Layout>
    );
  }
}

export default StarbucksIndex;