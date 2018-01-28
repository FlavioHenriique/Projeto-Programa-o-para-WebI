package com.ifpb.sisride.factory;

public class DaoFactory {

    public static DaoFactoryIF createFactory() {
        return new DaoFactoryBD();
    }
}
