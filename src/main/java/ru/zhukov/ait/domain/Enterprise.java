package ru.zhukov.ait.domain;

public enum Enterprise {
    GOTEK("ГОТЭК"){
        @Override
        String connectString() {
            return "jdbc:jtds:sqlserver://srv-sqlbox/ait_db;instance=AIT";
        }
    },
    POLYPACK("Полипак") {
        @Override
        String connectString() {
            return null;
        }
    };

    private final String name;

    public String getNameEnterprise(){
        return name;
    }

    Enterprise(String name) {
        this.name  = name;
    }

    abstract String connectString();

}
