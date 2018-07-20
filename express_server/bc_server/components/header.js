import React from 'react';
import { Menu } from 'semantic-ui-react';
import { Link } from '../routes';

export default ()=>{
  return (
    <Menu style = {{ marginTop: '10px'}}>
      <Link route='/homes/main'>
        <a className='item' style={{fontSize:24}}>Starbucks</a>
      </Link>
      <Menu.Menu position = 'right'>
        <Link route='/homes/pay'>
          <a className='item' style={{fontSize:20}}>Payment</a>
        </Link>
        <Link route='/homes/rewards'>
          <a className='item' style={{fontSize:20}}>Rewards</a>
        </Link>
        <Link route='/homes/store'>
          <a className='item' style={{fontSize:20}}>Store</a>
        </Link>
        <Link route='/api/logout'>
          <a className='item' style={{fontSize:20}}>Logout</a>
        </Link>
      </Menu.Menu>
    </Menu>
  );
}