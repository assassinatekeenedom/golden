(function(attach) {
    var forward = attach;
    var process = function(nio, io) {
        
        var createEvent = function(named) {
            var event = document.createEvent("Event");
            event.initEvent(named+event.timeStamp, true, true);
            return event;
        };
        attach.tracker = function(named) {
            var owner = named;
            nio.push(function() {
                io._createTracker(forward.account, owner);
            });
            var options = [owner + '._trackPageview', owner + '._trackEvent'];
            return {
                page: function(page) {
                    var locale = createEvent('Page');
                    var cache = [options[0], page];
                    var fire = function() {
                        nio.push(cache);
                    };
                    document.addEventListener(locale.type, fire, true);
                    return locale;
                },
                event: function(category, action, label, id) {
                    var event = createEvent('Event');
                    var cache = [options[1], category, action, label, id];
                    var fire = function() {
                        nio.push(cache);
                    };
                    document.addEventListener(event.type, fire, true);
                    return event;
                }
            };
        };
        forward("root.js", "/js/events/")()();
    };

    var consider = function() {
        try {
            process(_gaq, _gat);
        } catch (e) {
            window.setTimeout(consider, 1);
        }
    };
    consider();
})(connecture);