package com.pb.puenteblancovet.dto.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VaccineDto {
    private String type;
    private String age;
    private String doses;
    private String frequency;
    private String comments;
}
