package com.example.root.gni_transport.gni.utils;

/**
 * Created by ${ram} on 3/1/18.
 */

public  class Contants {
    private static final String BaseUrl ="http://192.168.43.121/project_php/";
    public static  final String Allroutes = BaseUrl+"All_routes.php";
    public static final  String complaints= BaseUrl+"complaints.php";
    public static final  String noticeboard= BaseUrl+"notice_board.php";
    public static final String coordinates=BaseUrl+"coordinates.php";
    public static final String gmapsBaseUrl="https://maps.googleapis.com/maps/api/directions/";
    public static final String gmapsResultType="json?";
    public static final String gmapsOrigin ="origin=";
    public static final String gmapsDestination="destination=";
    public static final String gmapsWaypoints="waypoints=";
    public static final String gmapsWaypointOptimize="optimize:";
    public static final String gmapsAnd="&";
    public static final String gmapsSeparator="|";
    public static final String gmapsSensor ="sensor=";
    public static final String gmapsMode="mode=";
    public static final String gmapsModeStyle="driving";
    public static final String gmapsAlternative="alternatives=";
    public static final String gmapskey="key";
}
