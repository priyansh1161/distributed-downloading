const express = require('express');
const mongoose = require('mongoose');
const zlib = require('zlib');
const File = mongoose.model('file');

const router = express.Router();

function sendJSON(obj,status,res){
    res.status(status);
    res.send(obj);
}

router.post('/', (req, res) => {
   if(!req.body._id && !req.body.index){
     return sendJSON({type : 'error', reason : 'no _id key or index'},404,res);
   }
   File
       .findById({ _id : req.body._id})
       .slice('parts',[parseInt(req.body.index), 1])
       .exec(function (err,data) {
           if(err){
                return sendJSON(err,500,res);
           }
           if(Object.keys(data).length === 0){
               return sendJSON({type : 'error', reason : 'Invalid _id'},404,res);
           }
           zlib.deflate(data.parts[0], (err,buff) => {
               if(err)
                   return sendJSON(err,500,res);
               res.send(buff);
           });
   });

});

router.post('/status', (req, res) => {
    if(!req.body._id){
        return sendJSON({type : 'error', reason : 'no _id key'},404,res);
    }
    File
        .findById(req.body._id)
        .select('-parts')
        .exec()
        .then(data => sendJSON(data, 200, res))
        .catch(err => sendJSON(err, 500, res));
});

module.exports = router;