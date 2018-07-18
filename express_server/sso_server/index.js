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
// https://git.heroku.com/protected-harbor-31970.git

const PORT = process.env.PORT || 5000;
app.listen(PORT);
