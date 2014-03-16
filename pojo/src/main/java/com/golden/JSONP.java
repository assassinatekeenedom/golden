package com.golden;

public enum JSONP {

    CALLBACK("jsonp", "js/jsonp/server.js");

    private final String callback;
    private final String script;

    JSONP(String callback, String script) {
        this.callback = callback;
        this.script = script;
    }
    
}
