package com.cbastian.apicat.resources.kernel.command.getbreads;

import com.cbastian.apicat.resources.kernel.command.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetBreadsCommand  implements Command {
    private String authorization;
    private String messageUuid;
}
