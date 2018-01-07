package com.example.root.gni_transport.gni.ui.models;

/**
 * Created by root on 3/1/18.
 */

public class SelectRoutemodel  {
    public  String RouteNumber;
    public  String FcmrouteId;
    public  String FullRoute;
    public  String Startoint;
    public  String Endpoint;
    public  String Viapoint;
    /*public Routeselectmodel (String RouteNumber,String FcmrouteId, String FullRoute ,
//                             String Endpoint, String Viapoint, String Startpoint)
//    {
//        this.RouteNumber=routenumber;
//        this.FcmrouteId=FcmrouteId;
//        this.FullRoute=FullRoute;
//        this.Endpoint=Endpoint;
//        this.Viapoint=Viapoint;
//        this.Startoint=Startpoint;
//    }*/
    public SelectRoutemodel(){
        
    }

    public  String getFcmrouteId() {
        return FcmrouteId;
    }

    public  void setFcmrouteId(String fcmrouteId) {
        FcmrouteId = fcmrouteId;
    }

    public  String getRouteNumber() {

        return RouteNumber;
    }

    public  void setRouteNumber(String routeNumber) {
        RouteNumber = routeNumber;
    }

    public  String getFullRoute() {
        return FullRoute;
    }

    public  void setFullRoute(String fullRoute) {
        FullRoute = fullRoute;
    }

    public  String getStartoint() {
        return Startoint;
    }

    public  void setStartoint(String startoint) {
        Startoint = startoint;
    }

    public  String getEndpoint() {
        return Endpoint;
    }

    public  void setEndpoint(String endpoint) {
        Endpoint = endpoint;
    }

    public  String getViapoint() {
        return Viapoint;
    }

    public  void setViapoint(String viapoint) {
        Viapoint = viapoint;
    }

}
