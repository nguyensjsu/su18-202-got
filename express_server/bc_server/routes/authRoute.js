const passport = require("passport");

module.exports = app => {
  app.get(
    "/auth/google",
    passport.authenticate("google", { scope: ["profile", "email"] })
  );

  app.get(
    "/auth/google/callback",
    passport.authenticate("google"),
    (req, res) => {
      res.redirect("/homes/main");
    }
  );

  app.get("/api/current_user", (req, res) => {
    if (req.user) {
      res.send(req.user);
    } else {
      res.status(404).send("User not found");
    }
  });

  app.get("/api/logout", (req, res) => {
    if (req.user) {
      req.logout();
      res.redirect("/");
    } else {
      res.status(404).send("User not found");
    }
  });

  app.get("/", (req, res) => {
    res.send("Please log in!");
  });

  // app.get("/homes", (req, res) => {
  //   res.send("Welcome to Starbucks, " + req.user.firstName + "!");
  // });
};
