package com.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CategoryDTO {
    public int id;
    public String name;
    public String image;
    public Date creationAt;
    public Date updatedAt;
}
