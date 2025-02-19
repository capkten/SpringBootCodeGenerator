/**
* GCP - dataflow job jjs for [report_comments_posts - report_comments_posts]
* AUTHOR capkin
*
* User-defined function (UDF) to transform events as part of a Dataflow template job.
* upload to GCS and create dataflow job with this js file and method as 'process'
* @param {string} inJson input Pub/Sub JSON message (stringified)
* @return {string} outJson output JSON message (stringified)
*/
function process(inJson) {
    //for local js debug
    //var obj = JSON.parse(JSON.stringify(inJson));
    //for online jjs
    var obj = JSON.parse(inJson);
    var includePubsubMessage = obj.data && obj.attributes;
    var data = includePubsubMessage ? obj.data : obj;
    //debug and show error if you need special logic
    if(data.hasOwnProperty('show_error')){
        throw new ERROR("show_error:"+JSON.stringify(data))
    }
    // INSERT CUSTOM TRANSFORMATION LOGIC HERE
    var tableObj= {};
    tableObj.insert_time=new Date().toUTCString()
    tableObj.cp_id=data.cpId
    return JSON.stringify(tableObj);
}

//field name = field name
    tableObj.cpId=data.cpId

//column name = column name
    tableObj.cp_id=data.cp_id
