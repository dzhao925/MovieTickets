package com.example.movies_danqi_dzhao40.models;

import java.util.List;

public class ResponseObject {
    /*
"dates":{
"maximum":"2022-08-09",
"minimum":"2022-06-22"
},
"page":1,
"results":[],
"total_pages":4,
"total_results":75
}
*/
    private List<Movie> results;

    public List<Movie> getResults(){return results;}
}
