const passport = require("passport");
const GoogleStrategy = require("passport-google-oauth20").Strategy;
const mongoose = require("mongoose");
const keys = require("../config/keys");
const User = mongoose.model("users");

passport.use(
  new GoogleStrategy(
    {
      clientID: keys.GOOGLE_CLIENT_ID,
      clientSecret: keys.GOOGLE_CLIENT_SECRET,
      callbackURL: "/auth/google/callback",
      proxy: true
    },
    async (accessToken, refreshToken, profile, callback) => {
      const existingUser = await User.findOne({ googleId: profile.id });

      if (existingUser) {
        return callback(null, existingUser);
      }
      const user = await new User({
        googleId: profile.id,
        email: profile.emails[0].value,
        firstName: profile.name.givenName
      }).save();
      callback(null, user);
    }
  )
);

passport.serializeUser((user, callback) => {
  callback(null, user.id);
});

passport.deserializeUser(async (id, callback) => {
  const user = await User.findById(id);
  callback(null, user);
});
