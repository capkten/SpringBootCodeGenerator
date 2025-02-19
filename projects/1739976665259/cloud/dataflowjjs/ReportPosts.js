/**
* GCP - dataflow job jjs for [report_posts - report_posts]
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
    tableObj.p_id=data.pId
    return JSON.stringify(tableObj);
}

//field name = field name
    tableObj.pId=data.pId

//column name = column name
    tableObj.p_id=data.p_id
