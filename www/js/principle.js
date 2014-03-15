(function(attach) {
    window.addEventListener('load', function() {
        var system = function(name, context) {
            var cache = [context, name];
            var action = function() {
                require(cache.join(""));
            };
            var root = document.createEvent("Event");
            root.initEvent(name + root.timeStamp, true, true);
            document.addEventListener(root.type, action, true);
            return function() {
                document.dispatchEvent(root);
                return function() {
                    document.removeEventListener(root.type, action, true);
                    root = null, delete root;
                };
            };
        };
        var coordinate = function(head, tail) {
            var state = null, width = tail[0] - head[0],
                    height = tail[1] - head[1];
            var up = width < 0, down = height < 0;
            if (up) {
                width *= -1;
            }
            if (down) {
                height *= -1;
            }
            if (up) {
                if (down) {
                    head[0] = tail[0],
                            head[1] = tail[1],
                            state = "nw";
                } else {
                    head[0] = head[0] - width;
                    state = "sw";
                }
            } else {
                if (down) {
                    head[1] = head[1] - height;
                    state = "ne";
                } else {
                    state = "se";
                }
            }
            if (state) {
                var dup = function(copy) {
                    var clone = {
                        transform: function(at, name) {
                            at[name] = clone;
                        },
                        clone: function() {
                            return dup(clone);
                        },
                        left: copy.left,
                        top: copy.top,
                        width: copy.width,
                        height: copy.height,
                        state: copy.state,
                        destroy: function() {
                            clone.save = null, delete clone.save,
                                    clone.clone = null, delete clone.clone,
                                    clone.left = null, delete clone.left,
                                    clone.top = null, delete clone.top,
                                    clone.width = null, delete clone.width,
                                    clone.height = null, delete clone.height,
                                    clone.state = null, delete clone.state,
                                    clone.destroy = null, delete clone.destroy,
                                    clone = null, delete clone;
                        }
                    };
                };
                var clone = {
                    transform: function(at, name) {
                        at[name] = clone;
                    },
                    clone: function() {
                        dup(clone);
                    },
                    left: head[0],
                    top: head[1],
                    width: width,
                    height: height,
                    state: state,
                    destroy: function() {
                        clone.save = null, delete clone.save,
                                clone.left = null, delete clone.left,
                                clone.top = null, delete clone.top,
                                clone.width = null, delete clone.width,
                                clone.height = null, delete clone.height,
                                clone.state = null, delete clone.state,
                                clone.destroy = null, delete clone.destroy,
                                clone = null, delete clone;
                    }
                };
                return clone;
            }
            return null;
        };
        attach.coordinates = function(head, tail) {
            return coordinate(head, tail);
        };
        var style = document.createElement("style");
        
        var reset = function() {
            document.head.appendChild(style);
        };
        reset();
        var proxy = function(title, element, clean) {
            if (clean) {
                if (element.className.indexOf(title) > 0) {
                    element.className = element.className.replace(" " + title, "");
                    return;
                }
                if (element.className.indexOf(" ")) {
                    element.className = element.className.replace(title + " ", "");
                    return;
                }
                element.className = element.className.replace(title + "", "");
            }
            if (element.className.indexOf(title) > -1) {
                return;
            }
            if (element.className.length) {
                element.className += " ";
            }
            element.className += title;
        };
        var selectors = [[], []];
        var create = function(local) {
            var key = selectors[0].indexOf(local);
            if (key < 0) {
                var name = local,
                        type = ".",
                        properties = [[], []];
                var product = null;
                var promote = function() {
                    var backup = promote;
                    promote = function() {
                        promote = backup;
                        style.removeChild(product);
                        promote();
                    };
                    product = document.createTextNode(type + name + "{" + properties[1].join("") + "}");
                    style.appendChild(product);
                };
                var pattern = {
                    view: function() {
                        return type + name + "{" + properties[1].join("") + "}";
                    },
                    name: function() {
                        return name;
                    },
                    promote: function() {
                        promote();
                    },
                    property: function(name, value) {
                        var key = properties[0].indexOf(name);
                        if (key < 0) {
                            properties[0].unshift(name),
                                    properties[1].unshift(name + ":" + value + ";");
                            key = 0;
                        }
                        if (value) {
                            properties[1][key] = name + ":" + value + ";";
                        }
                        return properties[1][key];
                    },
                    as: {
                        name: function() {
                            type = ".";
                        },
                        id: function() {
                            type = "#";
                        },
                        context: function() {
                            type = "";
                        }
                    },
                    toggle: function(element, clean) {
                        proxy(name, element, clean);
                    },
                    destroy: function() {
                        style.removeChild(product);
                        var key = selectors[0].indexOf(name);
                        selectors[0].splice(key, 1),
                                selectors[1].splice(key, 1);
                        name = null, delete name,
                                type = null, delete type,
                                properties = null, delete properties,
                                promote = null, delete promote,
                                pattern.name = null, delete pattern.name,
                                pattern.promote = null, delete pattern.promote,
                                pattern.property = null, delete pattern.property,
                                pattern.as = null, delete pattern.as,
                                pattern.toggle = null, delete pattern.toggle,
                                pattern.destroy = null, delete pattern.destroy,
                                pattern = null, delete pattern;
                    }
                };
                selectors[0].unshift(name),
                        selectors[1].unshift(pattern),
                        key = 0;
            }
            return selectors[1][key];
        };
        var stylesheet = {
            rule: function(name) {
                return create(name);
            },
            view: function() {
                console.group("--- Stylesheet ---");
                var wall = selectors[1].concat();
                while (wall.length) {
                    wall.shift().debug();
                }
                console.groupEnd();
            },
            destroy: function() {
                document.head.removeChild(style);
                var wall = selectors[1].concat();
                while (wall.length) {
                    wall.shift().destroy();
                }
                create = null, delete create,
                        selectors = null, delete selectors,
                        proxy = null, delete proxy,
                        reset = null, delete reset,
                        stylesheet.rule = null, delete stylesheet.rule,
                        stylesheet.destroy = null, delete stylesheet.destroy,
                        stylesheet = null, delete stylesheet,
                        style = null, delete style;
            }
        };
        attach.stylesheet = function() {
            return stylesheet;
        };
        var rule = attach.stylesheet().rule("dynamic");
        rule.property("position", "static");
        rule.property("overflow", "hidden");
        rule.property("z-index", 0);
        rule.property("margin", 0);
        rule.property("padding", 0);
        rule.property("background-color", "#000000");
        //rule.property("background-image", "url('/img/PeriodicTable.jpg')");
        rule.property("background-repeat", "no-repeat");
        rule.property("background-attachment", "fixed");
        rule.property("background-position", "center");
        var resize = function() {
            rule.property("width", window.innerWidth + "px");
            rule.property("height", window.innerHeight + "px");
            rule.promote();
        };
        window.addEventListener("resize", function() {
            resize();
        }, true);
        resize();
        rule.toggle(document.body);

        var standard = attach.stylesheet().rule("standard");
        standard.property("width", "200px");
        standard.promote();

        var spacer = attach.stylesheet().rule("spacer");
        spacer.property("margin", "5px");
        spacer.promote();

        area = document.createElement("div");
        var go = document.createElement("button");
        go.innerHTML = "Load these Configurations";
        spacer.toggle(go);
        var stop = document.createElement("button");
        stop.innerHTML = "Nevermind...";
        spacer.toggle(stop);

        stop.addEventListener("mousedown", function() {
            document.body.removeChild(area);
            document.addEventListener("mousedown", require, true);
        });
        go.addEventListener("mousedown", function() {
            document.removeEventListener("mousedown", require, true);
            document.body.removeChild(area);
            var load = 0;
            while (load < count) {
                if (keep[load]) {
                    var local = keep[load];
                    system(local.value + ".js", "/js/google/")()();
                    load++;
                } else {
                    return;
                }
            }
        });

        var count = 2;

        var remove = document.createElement("select");
        standard.toggle(remove);
        remove.multiple = "multiple";
        remove.size = count;

        var keep = document.createElement("select");
        standard.toggle(keep);
        keep.multiple = "multiple";
        keep.size = count;

        keep.addEventListener("mousedown", function(event) {
            if (event.target && event.target !== keep) {
                remove.appendChild(event.target);
            }
        }, true);
        remove.addEventListener("mousedown", function(event) {
            if (event.target && event.target !== remove) {
                keep.appendChild(event.target);
            }
        }, true);

        var tracking = document.createElement("option");
        tracking.value = "analytics";
        tracking.innerHTML = "Google Tracking";

        var timeline = document.createElement("option");
        timeline.value = "timeline";
        timeline.innerHTML = "Logging Timeline";

        var title = document.createElement("h2");
        title.innerHTML = "Keep (Left) - or - Remove (Right)";
        area.appendChild(title);
        keep.appendChild(tracking);
        keep.appendChild(timeline);
        area.appendChild(keep);
        area.appendChild(remove);
        area.appendChild(go);
        area.appendChild(stop);


        var css = attach.stylesheet().rule("require");
        css.property("position", "absolute");
        css.property("padding", "20px");
        css.property("text-align", "center");
        css.property("background-color", "#ffffff");
        css.property("width", "400px");
        css.property("height", "400px");
        var relative = [0, 0];
        var principle = [0, 0];
        var resize = function() {
            if (relative[0] > 0 && relative[1] > 0) {
                var ratio = [relative[0] / window.innerWidth, relative[1] / window.innerHeight];
                principle[0] = parseInt(principle[0] * ratio[0]);
                principle[1] = parseInt(principle[1] * ratio[1]);
            }
            relative[0] = parseInt(window.innerWidth * .4);
            relative[1] = parseInt(window.innerHeight * .4);
            css.property("width", relative[0] + "px");
            css.property("height", relative[1] + "px");
            css.promote();
        };
        window.addEventListener("resize", function() {
            resize();
        }, true);
        css.toggle(area);
        resize();
        var require = function(event) {
            document.removeEventListener("mousedown", require, true);
            principle[0] = event.clientX;
            principle[1] = event.clientY;
            css.property("left", event.clientX - parseInt(relative[0] / 2) + "px");
            css.property("top", event.clientY - parseInt(relative[1] / 2) + "px");
            css.promote();
            document.body.appendChild(area);
        };
        document.addEventListener("mousedown", require, true);
    }, true);
})({});