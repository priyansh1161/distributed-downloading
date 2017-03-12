let mongoose = require('mongoose');

let Schema = mongoose.Schema;
let fileSchema = new Schema({
    name : String,
    ext : String,
    status : {type:String, default: 'downloading'},
    createdOn : {type : Date, default : Date.now},
    partCount : Number,
    parts : [Buffer],
    reason : {type : String, default : 'none'}
});

mongoose.model('file', fileSchema);