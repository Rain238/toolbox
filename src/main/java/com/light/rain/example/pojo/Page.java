package com.light.rain.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: LightRain
 * @Description:
 * @DateTime: 2023-02-25 11:40
 * @Version：1.0
 **/
@Getter
@Setter
@AllArgsConstructor
public class Page {
    private int start;
    private int pageSize;
    private int pages;
    private int total;

    public Page() {
    }
}
