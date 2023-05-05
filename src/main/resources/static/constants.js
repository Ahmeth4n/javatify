var api_domain = "http://127.0.0.1:8080/";

function formToJson(targetForm){
    let uncleandata = targetForm.serializeArray();
    let data = {};
    uncleandata.forEach(item=>{
        data[item.name] = item.value;
    });
    return JSON.stringify(data);
}