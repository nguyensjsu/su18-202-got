const express = require("express");
const app = express();

// https://git.heroku.com/protected-harbor-31970.git

app.get("/", (req, res) => {
  res.send("hello!");
});

const PORT = process.env.PORT || 5000;
app.listen(PORT);
