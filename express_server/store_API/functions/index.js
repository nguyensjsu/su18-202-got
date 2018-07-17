const functions = require("firebase-functions");

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

const { GOOGLE_PLACE_API } = require("./config/keys");
const axios = require("axios");
const rootAPI = "https://maps.googleapis.com/maps/api/place/textsearch/json?";

exports.store_api = functions.https.onRequest((req, res) => {
  axios
    .get(rootAPI, {
      params: {
        query: req.query.query,
        key: GOOGLE_PLACE_API
      }
    })
    .then((response) => res.send(response.data));
});
