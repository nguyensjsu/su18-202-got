import React, { Component } from 'react';
import { Form, Button, Input, Message } from 'semantic-ui-react';
import Layout from '../../components/layout';
import starbucksUsers from '../../ethereum/users';
import web3 from '../../ethereum/web3';
import { Link, Router } from '../../routes';

class CardNew extends Component {

  state = {
    id: '',
    code: '',
    bal: 20,
    errorMessage: '',
    loading: false
  };

  onSubmit = async (event)=>{
    event.preventDefault();
    this.setState({loading:true, errorMessage:''});
    try{
      const accounts = await web3.eth.getAccounts();
      await starbucksUsers.methods
        .addCard(this.state.id, this.state.code, this.state.bal)
        .send({
          from: accounts[0],
          gas: "1000000"
        });
      Router.pushRoute('/homes/main');
    } catch(err){
      this.setState({errorMessage: err.message});
    }
    this.setState({loading:false});
  };
  render (){
    return (
      <Layout>
        <Link route={'/homes/main'}>
          <a>Back</a>
        </Link>
        <h3>Add A Card</h3>
        <Form onSubmit={this.onSubmit} error={!!this.state.errorMessage}>
          <Form.Field>
            <label>Card ID</label>
            <Input placeholder = "Enter 9-digit card numbers"
                   value = {this.state.id}
                   onChange = {(event)=>{
                     this.setState({id: event.target.value})
                   }}
            />
          </Form.Field>
          <Form.Field>
            <label>Safety Code</label>
            <Input type = "password"
                   value = {this.state.code}
                   onChange = {(event)=>{
                     this.setState({code: event.target.value})
                   }}
            />
          </Form.Field>
          <Form.Field>
            <label>Card Balance</label>
            <Input type = "number"
                   value = {this.state.bal}
                   onChange = {(event)=>{
                     this.setState({bal: event.target.value})
                   }}
            />
          </Form.Field>
          <Message
            error
            header="Error occurred!"
            content={this.state.errorMessage}/>
          <Button loading={this.state.loading} primary type='submit'>Add</Button>

        </Form>
      </Layout>
    );
  }
}

export default CardNew;