package com.j3thr0.trueke.truekeapi.settings.files.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {

    private String name;
    private String uri;
    private String type;
    private String size;
}
