package fr.hgwood.codestory.elevator;

public class HttpResponseInfo {
    
    public final int status;
    public final String content;
    
    public HttpResponseInfo(int status, String content) {
        this.status = status;
        this.content = content;
    }

}
