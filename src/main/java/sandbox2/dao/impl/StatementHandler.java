package sandbox2.dao.impl;

import sandbox2.models.Container;
import sandbox2.utils.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class StatementHandler {

    public enum Mode {
        UPDATE(0), CREATE(1), READ(2);
        public int value;
        private Mode(int value) {
            this.value = value;
        }
    }
    private static Class aPrepStat = PreparedStatement.class;
    private static Class aResSet = ResultSet.class;

    public static void create(PreparedStatement statement, Container container) {
        setUpdate(Mode.CREATE, statement, container);
    }

    public static void update(PreparedStatement statement, Container container) {
        setUpdate(Mode.UPDATE, statement, container);
    }

    public static void read(ResultSet resultSet, Container container) {
        setQuery(Mode.READ, resultSet, container);
    }

    public static void setUpdate(Mode mode, PreparedStatement statement, Container container) {

        Class aCont = container.getClass();
        Field[] fields = aCont.getDeclaredFields();
        int containerEnd = fields.length;


        try {
            for (int i= mode.value; i < containerEnd; i++) {
                String contName = "get" + Utils.capitalize(fields[i].getName());
                Method contMethod = aCont.getDeclaredMethod(contName);
                Class contType = contMethod.getReturnType();
                String statName = "set" + Utils.capitalize(contType.getSimpleName());
                Method statMethod = aPrepStat.getDeclaredMethod(statName, int.class, contType);

                if (i == 0) {
                    statMethod.invoke(statement, containerEnd, contMethod.invoke(container));
                } else {
                    statMethod.invoke(statement, i, contMethod.invoke(container));
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void setQuery(Mode mode, ResultSet resultSet, Container container) {

        Class aCont = container.getClass();
        Field[] fields = aCont.getDeclaredFields();
        int containerEnd = fields.length;


        try {
            for (int i= 0; i < containerEnd; i++) {
                String fieldName = fields[i].getName();
                String contName = "set" + Utils.capitalize(fieldName);
                Class contType = fields[i].getType();
                Method contMethod = aCont.getDeclaredMethod(contName, contType);

                String statName = "get" + Utils.capitalize(contType.getSimpleName());
                Method statMethod = aResSet.getDeclaredMethod(statName, String.class);

                contMethod.invoke(container, statMethod.invoke(resultSet, fieldName));
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
