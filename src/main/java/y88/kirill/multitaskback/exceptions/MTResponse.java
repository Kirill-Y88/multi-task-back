package y88.kirill.multitaskback.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class MTResponse {

    private long status;
    private String message;
    private Date timestamp;

    public MTResponse(long status, String message) {
        this.status = status;
        this.message = message;
    }
}
