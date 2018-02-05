package ru.zhukov.ait.domain;

public enum Enterprise {
    GOTEK("ГОТЭК"){
        @Override
        String connectString() {
            return "jdbc:jtds:sqlserver://srv-sqlbox/ait_db;instance=AIT";
        }

        @Override
        public String nameDb() {
            return "ait_db_tarif";
        }
    },
    POLYPACK("Полипак") {
        @Override
        String connectString() {
            return "jdbc:jtds:sqlserver://srv-sqlbox/polypack;instance=AIT";
        }
        @Override
        public String nameDb() {
            return "polypack_tarif";
        }
    },

    INVESTHOME("Инвест Номе") {
        @Override
        String connectString() {
            return "jdbc:jtds:sqlserver://srv-sqlbox/polypack;instance=AIT";
        }
        @Override
        public String nameDb() {
            return "g_invst";
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
    public abstract String nameDb();

}
