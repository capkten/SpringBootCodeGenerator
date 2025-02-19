/**
* GCP - dataflow job jjs for [report_comments_news - report_comments_news]
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
    tableObj.cn_id=data.cnId
    return JSON.stringify(tableObj);
}

//field name = field name
    tableObj.cnId=data.cnId

//column name = column name
    tableObj.cn_id=data.cn_id
