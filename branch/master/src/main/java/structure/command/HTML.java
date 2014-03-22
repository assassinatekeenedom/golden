package structure.command;

public enum HTML {

    TITLE("<html><head><title>"),
    SCRIPT("</title><script>"),
    BODY("</script></head><body>"),
    END("</body></html>");

    private final String state;

    HTML(String part) {
        this.state = part;
    }

    public HTML next(StringBuilder builder, String with) {
        builder.append(this.state);
        switch (this) {
            case TITLE:
                builder.append(with);
                return SCRIPT;
            case SCRIPT:
                JS.REQUIRE.next(builder, with).next(builder, "");
                return BODY;
            case BODY:
                builder.append(with);
                return END;
            case END:
                return END;
        }
        return TITLE;
    }

    public enum JS {

        REQUIRE("var script=document.createElement('script');script.type='text/javascript';script.src='"),
        LOAD("';document.head.appendChild(script);document.head.removeChild(script);delete script, script=null;");

        private final String state;

        JS(String part) {
            this.state = part;
        }

        public JS next(StringBuilder builder, String with) {
            builder.append(this.state);
            switch (this) {
                case REQUIRE:
                    builder.append(with);
                    return LOAD;
                case LOAD:
                    builder.append(with);
                    return LOAD;
            }
            return REQUIRE;
        }

    }

}
