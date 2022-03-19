package com.example.backend.model.apis.route;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Info{
    public Integer statuscode;
    public Copyright copyright;
    public String debug;
    public List<Object> messages;
}
