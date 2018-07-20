const express = require("express");
const keys = require("./config/keys");
const mongoose = require("mongoose");
const cookieSession = require('cookie-session');
const bodyParser = require('body-parser');

const passport = require('passport');
require('./models/Users');
require("./services/passport");

mongoose.connect(keys.mongoDBURI);

const app = express();

app.use(bodyParser.json());
app.use(cookieSession({
    maxAge: 30*24*60*60*1000,
    keys: [keys.cookieKey]
  }
));

app.use(passport.initialize());
app.use(passport.session());

require("./routes/authRoute")(app);

const next = require('next');
const routes = require('./routes');
const next_app = next({
  dev: process.env.NODE_ENV !=='production'
});
const handler = routes.getRequestHandler(next_app);

// const { createServer } = require('http');
// app.prepare().then(()=>{
//   createServer(handler).listen(5000, (err)=>{
//     if(err) throw err;
//     console.log('Ready on localhost:5000');
//   });
// });

next_app.prepare().then(()=>{
  const PORT = process.env.PORT || 5000;
  app.use(handler).listen(PORT, (err)=>{
    if(err) throw err;
    console.log(`Ready on port: ${PORT}`);
  });
});


// if(process.env.NODE_ENV ==='production'){
//   app.use(express.static('.next'));
//   const path = require('path');
//   app.get('*', (req,res)=>{
//     res.sendFile(path.resolve(__dirname, '.next', 'index.html'));
//   });
// }
// https://git.heroku.com/protected-harbor-31970.git
//
// const PORT = process.env.PORT || 5000;
// app.listen(PORT);
