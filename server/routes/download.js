const express = require('express');
const request = require('request');
const zlib = require('zlib');
const mongoose = require('mongoose');
const File = mongoose.model('file');
const router = express.Router();

function sendJSON(obj,status,res){
    res.status(status);
    res.send(obj);
}

let makeParts = (body, parts) => {
    let partsContainer = [];
    let sp = Math.floor(body.length/parts);
    let partLen = Math.floor(body.length/parts);
    let prv = 0,temp = [];
    for(let i =0 ;i<parts;i++){
        temp = [];
        for(let j=prv;j<sp;j++){
            temp.push(body[j]);
        }
        partsContainer.push(new Buffer(temp));
        prv = sp;
        sp = partLen + sp;
    }
    temp = [];
    for(let i=prv;i<body.length;i++){
        temp.push(body[i]);
    }
    let last = partsContainer.pop();
    let tm = new Buffer(temp);
    partsContainer.push( Buffer.concat([last,tm]));
    return partsContainer;
};
let saveToDb = (file, url) => {
    let {partCount} = file;
    request({
        method : 'GET',
        url,
        encoding : null
    },(err, response, body)=>{
        if(err || response.statusCode !== 200){
            file.status = 'failed';
            file.reason = err ? err.message : 'Wrong url' ;
            file.save(function (err, data) {
                //todo handle 2nd layer errors
            });
            return;
        }
        file.parts = makeParts(body,partCount);
        file.status = 'done';
        file.save(function (err, data) {
            //todo handle 2nd layer errors
        });

    });
};
router.post('/',(req, res) => {
    const url = req.body.url;
    const parts = req.body.parts;
    const [, name, ext] = url.match(/\/([A-Za-z0-9-_]+)(\.\w+)$/i);
    console.log(name,ext);

    File
        .create({
            name,
            ext,
            partCount: parts
        })
        .then((data)=>{
            setTimeout(function(){
                saveToDb(data,url);
            },0);
            sendJSON(data,200,res);
        })
        .catch((err)=>{
            sendJSON(err,500,res);
        });


}
);

module.exports = router;