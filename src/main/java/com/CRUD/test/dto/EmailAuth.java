package com.CRUD.test.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter @Setter
@NoArgsConstructor
public class EmailAuth {
    private String id;
    private int number;


}
