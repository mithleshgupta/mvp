package mvpbackend.mvp.MainApp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoundStatusCountDTO {
    private long inboundCount;
    private long outboundCount;

    public BoundStatusCountDTO(long inboundCount, long outboundCount) {
        this.inboundCount = inboundCount;
        this.outboundCount = outboundCount;
    }
}
