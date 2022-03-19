package com.example.backend.model.apis.route;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RouteError{
    public Integer errorCode;
    public String message;
}