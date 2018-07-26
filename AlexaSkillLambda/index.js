var Alexa = require('alexa-sdk');
const aws = require('aws-sdk');
const dynamo = new aws.DynamoDB.DocumentClient();
const tableName = process.env.TABLE_NAME;
const https = require('https');
var http = require("http");

exports.handler = function(event, context, callback) {
    var alexa = Alexa.handler(event, context);


    alexa.registerHandlers(handlers);
    alexa.execute();
};

var handlers = {
    
    'GetStarbucksData': function () {
     getStarbucksAppData(this);
    },
    'AMAZON.HelpIntent': function () {
         this.emit(':ask', 'Learn everything you need to know about Amazon Web Services to pass your exam by listening to your very own study notes. You can start by saying, Kumar help me study.', 'try again');
     },

     'AMAZON.CancelIntent': function () {
         this.emit(':tell', 'Goodbye Kumar');
     },

     'AMAZON.StopIntent': function () {
         this.emit(':tell', 'Goodbye Kumar');
     }
};


function getStarbucksAppData(thisval){

 var url = 'http://54.89.204.70:8080/getbalance?userid=TT';
 http.get(url, (res) => {
  console.log('statusCode:', res.statusCode);
  console.log('headers:', res.headers);
  var body = '';
    res.on('data', (d) => {
      body += d;
    });
      res.on('end', function() {
          
         // console.log('len='+length);
          //var parsed = JSON.parse(body);

          console.log('Starbucks body of Balance='+body);

          var say = 'Welcome to Starbucks. Your current balance is '+body+'. Thank You.';
          thisval.emit(':tell', say );
          //console.log("data= "+JSON.stringify(body));
      });
  }).on('error', (e) => {
    //console.error(e);
    thisval.emit(':tell', 'Starbucks, Error in getting Balance' );
  });
        
}