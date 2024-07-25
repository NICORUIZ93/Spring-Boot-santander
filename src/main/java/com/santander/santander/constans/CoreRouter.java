package com.santander.santander.constans;

public class CoreRouter {

    private CoreRouter() {
        throw new IllegalStateException("CoreRouter");
    }

    public static final String API_BASE_PATH = "/api";

    public static class ProductRoutes {
        private ProductRoutes() {
            throw new IllegalStateException("ProductRoutes");
        }

        public static final String ROOT = API_BASE_PATH + "/products";
        public static final String GET_ALL = "";
        public static final String GET_BY_ID = "/{id}";
        public static final String CREATE = "";
        public static final String UPDATE = "/{id}";
        public static final String DELETE = "/{id}";
    }
}