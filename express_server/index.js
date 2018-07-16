const express = require("express");
const app = express();
const {GOOGLE_PLACE_API} = require("./config/keys");
const axios = require("axios");
const rootAPI = "https://maps.googleapis.com/maps/api/place/textsearch/json?";

app.get("/store", async (req, res) => {
  const { data } = await axios.get(rootAPI, {
    params: {
      query: req.query.query,
      key: GOOGLE_PLACE_API
    }
  });
  res.send(data);
});

app.listen(5000);
