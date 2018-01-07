package com.example.root.gni_transport.gni.ui.models;

import static com.example.root.gni_transport.gni.utils.Sharedpref.routenumber;

/**
 * Created by root on 3/1/18.
 */

public class Routeselectmodel {
    public static String RouteNumber;
    public static String FcmrouteId;
    public static String FullRoute;
    public static String Startoint;
    public static String Endpoint;
    public static String Viapoint;
    public Routeselectmodel (String RouteNumber,String FcmrouteId, String FullRoute ,
                        String Endpoint, String Viapoint, String Startpoint)
    {
        this.RouteNumber=routenumber;
        this.FcmrouteId=FcmrouteId;
        this.FullRoute=FullRoute;
        this.Endpoint=Endpoint;
        this.Viapoint=Viapoint;
        this.Startoint=Startpoint;
    }

    public static String getFcmrouteId() {
        return FcmrouteId;
    }

    public static void setFcmrouteId(String fcmrouteId) {
        FcmrouteId = fcmrouteId;
    }

    public static String getRouteNumber() {
        return RouteNumber;
    }

    public static void setRouteNumber(String routeNumber) {
        RouteNumber = routeNumber;
    }

    public static String getFullRoute() {
        return FullRoute;
    }

    public static void setFullRoute(String fullRoute) {
        FullRoute = fullRoute;
    }

    public static String getStartoint() {
        return Startoint;
    }

    public static void setStartoint(String startoint) {
        Startoint = startoint;
    }

    public static String getEndpoint() {
        return Endpoint;
    }

    public static void setEndpoint(String endpoint) {
        Endpoint = endpoint;
    }

    public static String getViapoint() {
        return Viapoint;
    }

    public static void setViapoint(String viapoint) {
        Viapoint = viapoint;
    }
}
