package com.cbastian.apicat.resources.kernel.command.searchbreadbyname;

import com.cbastian.apicat.resources.kernel.command.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchBreedByNameCommand implements Command, Serializable {
    private String authorization;
    private String messageUuid;
    private String name;
}
