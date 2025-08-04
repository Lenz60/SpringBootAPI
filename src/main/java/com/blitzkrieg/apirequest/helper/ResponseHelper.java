package com.blitzkrieg.apirequest.helper;

import java.util.HashMap;
import java.util.Map;

public class ResponseHelper { //Non complicant

    public ResponseHelper() {
        // Default constructor
    }

    public static Map<String, Object> successResponse(Object data, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", message);
        // Optionally add data if not null
        if (data != null) {
            response.put("data", data);
        }
        return response;
    }

    public static Map<String, String> errorResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return response;
    }
}
