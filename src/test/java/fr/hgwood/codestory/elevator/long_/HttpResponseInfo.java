package fr.hgwood.codestory.elevator.long_;

public class HttpResponseInfo {
    
    public final int status;
    public final String content;
    
    public HttpResponseInfo(int status, String content) {
        this.status = status;
        this.content = content;
    }
    
    @Override public String toString() {
        return HttpResponseInfo.class.getSimpleName() + 
            ": \"" + content + "\" (" + status + ")";
    }

}
