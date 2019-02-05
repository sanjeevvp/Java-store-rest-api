package hello;

public class Response {
    private int code;
    private String message;
    private Object body;

    public Response(int code,String message, Object object) {
        this.code = code;
        this.message = message;
        this.body = object;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

    public Object getBody(){
        return body;
    }
}