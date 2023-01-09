package com.gorest.constants;

public class Endpoints {

    public static final String CREATE_USER = "/users";
    public static final String GET_USER_BY_ID = "/users/{userID}";
    public static final String UPDATE_USER_BY_ID = "/users/{userID}";
    public static final String DELETE_USER_BY_ID = "/users/{userID}";

    //Posts

    public static final String GET_ALL_POST = "/posts";
    public static final String GET_POST_BY_ID = "/posts/{postID}";
    public static final String CREATE_POST = "users/{userId}/posts";
    public static final String UPDATE_POST_BY_ID = "/posts/{postID}";
    public static final String DELETE_POST_BY_ID = "/posts/{userID}";


}
