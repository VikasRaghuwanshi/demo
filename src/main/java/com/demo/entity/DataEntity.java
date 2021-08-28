package com.demo.entity;

import lombok.*;
import org.intellij.lang.annotations.Identifier;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Component
public class DataEntity {
    @Id
    private String id;
    private String date;
    private String distName;
    private String mandiName;
    private String commGroupName;
    private String commName;
    private String distCode;
    private String mandiCode;
    private String commGroupCode;
    private String commCode;
    private String minValue;
    private String maxValue;
}
