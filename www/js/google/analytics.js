(function(attach) {
    attach("tracking.js","/js/google/")()();
    attach("client.js?onload=handleClientLoad","https://apis.google.com/js/")()();    
})(connecture);