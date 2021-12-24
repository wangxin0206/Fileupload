/*Jsgrid Init*/
$(function() {
	"use strict";
	
    $("#jsgrid_1").jsGrid({
        height: "450px",
        width: "100%",
 
        filtering: true,
        editing: true,
        sorting: true,
        paging: true,
        autoload: true,
 
        pageSize: 15,
        pageButtonCount: 5,
 
        deleteConfirm: "Do you really want to delete the client?",
 
        controller: db,
 
        fields: [
            { name: "id", type: "text", width: 50 },
            { name: "budget", type: "text", width: 50 },
            { name: "popularity", type: "text", width: 40 },
            { name: "release_date", type: "text", width: 70 },
            { name: "runtime", type: "number", width: 50 },
            { name: "title", type: "text", width: 100 },
            { name: "vote_average", type: "text", width: 50 },
            { name: "vote_count", type: "number", width: 50 },
            { type: "control" }
        ]
    });
 
});