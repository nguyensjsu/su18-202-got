const routes = require('next-routes')();

routes
  .add('/homes/main', '/homes/main')
  .add('/homes/new', '/homes/new')
  .add('/homes/pay', '/homes/pay')
  .add('/homes/rewards', '/homes/rewards')
  .add('/homes/store', '/homes/store')
  .add('/api/logout', '/api/logout');

module.exports = routes;