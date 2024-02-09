package com.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@ToString
public class ProductsDTO
{
    public int id;
    public String title;
    public int price;
    public String description;
    public ArrayList<String> images;
    public Date creationAt;
    public Date updatedAt;
    public CategoryDTO category;
}