(function(attach) {

    var page = function(event) {
        console.info("Page Listener: ", event);
        document.removeEventListener(event[0].type,page,false);
    };
    var action = function(event) {
        console.info("Action Listener: ", event);
        document.removeEventListener(event[1].type,action,false);
    };

    var root = attach.tracker("root");
    var event = [root.page('custom-name'), root.event('custom-category', 'custom-action', 'custom-label', 5)];
    document.addEventListener(event[0].type,page,false);
    document.addEventListener(event[1].type,action,false);
    var handle = [function(){document.dispatchEvent(event[0]);},function(){document.dispatchEvent(event[1]);}];
    
    console.log("Event: ",event);
    console.log("Handle: ",handle);
    handle[0]();
    handle[1]();
    
    //make each debug message from the logs (jsonp callback) a page unto itself.... using /rest/syntax!
    //PAGE@/${tracker-name}/thread/priority/cat/a/gory/
    
    //EVENT@ .... tie the dynamic 'dev/qa/ba' actions to the REST location; inspection type.
    
    //GOAL@ .... if there are callbacks to a dynamic service; provide a Goal.
    
    //OPTION A -- Log Remote (LIVE)
    //
    
    //@@@ - - - Selenium

})(connecture);