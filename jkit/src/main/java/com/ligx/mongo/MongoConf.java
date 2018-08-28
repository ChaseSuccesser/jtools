package com.ligx.mongo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MongoConf {

    private String url;

    private String username;

    private String password;

    private String authDatabase;  // the name of the database in which the username is defined

    private String database;

}
