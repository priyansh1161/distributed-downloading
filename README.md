# Distributed Downloading

## How to run
## Client
   ( Yet to be written )
## Server
Download node LTS from https://nodejs.org/en/ 
### Setup Dependencies
Run `npm install` inside /server  to setup dependencies.

### Database
Download MongoDB 3.2+ from https://docs.mongodb.com/manual/installation/ and follow the instructions to Setup Database locally

### Run Locally
Run `mongod` to start to primary daemon process for the MongoDB system.

In new Terminal run `npm start` inside /server to fire up the server, default port is 3000.
 
## API

### Download Route

To initiate downloading send a POST request to `localhost:3000/download`.

  All POST request must have a url and parts field.
  example : 
  
  ``` javascript
   {
        url : 'http://something.com/somename.ext',
        parts : '20'
   }
   ```
   
   if no error in url link or no internal error then a response as such is sent back
   ``` javascript
   {
     "__v": 0,
     "name": "somename",
     "ext" : ".ext"
     "partCount": 3,
     "_id": "58c522831a3a1919a103c863",
     "reason": "none",
     "parts": [],
     "createdOn": "2017-03-12T10:27:15.827Z",
     "status": "downloading"
   }
   ```
   and if some error is occurred then err is return with a status code of 500.
   
### Retrieve route

To initiate downloading parts send a POST request to `localhost:3000/retrieve/status`.

having a valid `_id` property that was returned in downloading phase. this _id is key to all new API interactions.

**Response**

``` javascript
{
  "_id": "58c52387f4e3e81a0ccaa1cd",
  "name": "somename",
  "ext": ".ext",
  "partCount": 3,
  "__v": 1,
  "reason": "none",
  "createdOn": "2017-03-12T10:31:35.037Z",
  "status": "done"
}
```
here status is current downloading status it can be

1. Downloading
2. Done
3. failed

if the status is failed then reason will have a failure reason other then none.
***IF AND ONLY IF** the status is Done

make a POST request to `localhost:3000/retrieve` having `_id` and `index` keys in it
 
 NOTE : index must be less then partCount
 
 **Response** will be a Deflate UTF-8 encoded file. So before recombination data need to be inflated.
  

   
   
  
                