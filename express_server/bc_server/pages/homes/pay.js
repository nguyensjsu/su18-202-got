import React, { Component } from "react";
import { Form, Button, Input, Message } from "semantic-ui-react";
import Layout from "../../components/layout";
import starbucksUsers from "../../ethereum/users";
import web3 from "../../ethereum/web3";
import { Router } from "../../routes";

class Pay extends Component {
  state = {
    amount: 0,
    errorMessage: "",
    loading: false
  };

  onSubmit = async event => {
    event.preventDefault();
    this.setState({ loading: true, errorMessage: "" });
    try {
      const accounts = await web3.eth.getAccounts();
      await starbucksUsers.methods.payByCard(this.state.amount).send({
        from: accounts[0],
        value: this.state.amount
      });
      Router.pushRoute("/homes/main");
    } catch (err) {
      this.setState({ errorMessage: err.message });
    }
    this.setState({ loading: false });
  };
  render() {
    return (
      <Layout>
        <h3>Create an User HomeSafe</h3>
        <Form
          onSubmit={event => this.onSubmit(event)}
          error={!!this.state.errorMessage}
          style={{ marginTop: "20px" }}
        >
          <ul>
            <li>Hot Coffee: $2 </li>
            <li>Expresso: $3 </li>
            <li>Frappie: $4 </li>
            <li>Sandwich: $5 </li>
          </ul>
          <Input
            placeholder="Amount to Pay"
            type = "number"
            value={this.state.amount}
            onChange={event => {
              this.setState({ amount: event.target.value });
            }}
          />
          <Message
            error
            header="Error occurred!"
            content={this.state.errorMessage}
          />
          <Button loading={this.state.loading} primary type="submit">
            Pay
          </Button>
        </Form>
      </Layout>
    );
  }
}

export default Pay;
