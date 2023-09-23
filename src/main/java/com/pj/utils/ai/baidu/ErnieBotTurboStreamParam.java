package com.pj.utils.ai.baidu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErnieBotTurboStreamParam implements Serializable {

    private List<BaiduChatMessage> messages;
    private Boolean stream;
    private String user_id;

    public boolean isStream() {
        return Objects.equals(this.stream, true);
    }
}