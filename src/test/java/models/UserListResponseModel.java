package models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserListResponseModel {
    int page;
    int per_page;
    int total;
    int total_pages;
    List<Data> data = new ArrayList<>();
    Support support;

    @lombok.Data
    public static class Data {
        int id;
        String email;
        String first_name;
        String last_name;
        String avatar;
    }
    @lombok.Data
    public static class Support {
        String url;
        String text;
    }
}
