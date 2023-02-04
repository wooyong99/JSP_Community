package com.jwy.exam.util;

import com.jwy.exam.exception.SQLErrorException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
  public static Map<String, Object> selectRow(Connection dbConn, SecSql sql) {
    List<Map<String, Object>> rows = selectRows(dbConn, sql);

    if (rows.size() == 0) {
      return new HashMap<>();
    }

    return rows.get(0);
  }
  // Exception은 Error보다 비교적 경미한 오류이며, try-catch문을 이용해 프로그램의 비정상적인 종료를 막을 수 있다.
  // Checked Exception은 컴파일 전 예측이 가능하므로, 예외처리에 대한 강제성이 있다.
  // 반면에, Unchecked Exception은 강제성이 없다.
  public static List<Map<String, Object>> selectRows(Connection dbConn, SecSql sql) throws SQLErrorException {
    List<Map<String, Object>> rows = new ArrayList<>();

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = sql.getPreparedStatement(dbConn);
      rs = stmt.executeQuery();
      ResultSetMetaData metaData = rs.getMetaData();
      int columnSize = metaData.getColumnCount();

      while (rs.next()) {
        Map<String, Object> row = new HashMap<>();

        for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
          String columnName = metaData.getColumnName(columnIndex + 1);
          Object value = rs.getObject(columnName);

          if (value instanceof Long) {
            int numValue = (int) (long) value;
            row.put(columnName, numValue);
          } else if (value instanceof Timestamp) {
            String dateValue = value.toString();
            dateValue = dateValue.substring(0, dateValue.length() - 2);
            row.put(columnName, dateValue);
          } else {
            row.put(columnName, value);
          }
        }

        rows.add(row);
      }
    } catch (SQLException e) {
      throw new SQLErrorException("SQL 예외, SQL : " + sql, e);
    }finally{
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          throw new SQLErrorException("SQL 예외, rs 닫기, SQL : " + sql, e);
        }
      }

      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          throw new SQLErrorException("SQL 예외, stmt 닫기, SQL : " + sql, e);
        }
      }
    }

    return rows;
  }

  public static int selectRowIntValue(Connection dbConn, SecSql sql) {
    Map<String, Object> row = selectRow(dbConn, sql);

    for (String key : row.keySet()) {
      return (int) row.get(key);
    }

    return -1;
  }

  public static String selectRowStringValue(Connection dbConn, SecSql sql) {
    Map<String, Object> row = selectRow(dbConn, sql);

    for (String key : row.keySet()) {
      return (String) row.get(key);
    }

    return "";
  }

  public static boolean selectRowBooleanValue(Connection dbConn, SecSql sql) {
    Map<String, Object> row = selectRow(dbConn, sql);

    for (String key : row.keySet()) {
      return ((int) row.get(key)) == 1;
    }

    return false;
  }

  public static int insert(Connection dbConn, SecSql sql) {
    int id = -1;

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = sql.getPreparedStatement(dbConn);
      stmt.executeUpdate();
      rs = stmt.getGeneratedKeys();

      if (rs.next()) {
        id = rs.getInt(1);
      }

    } catch (SQLException e) {
      throw new SQLErrorException("SQL 예외, SQL : " + sql, e);
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          throw new SQLErrorException("SQL 예외, rs 닫기, SQL : " + sql, e);
        }
      }

      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          throw new SQLErrorException("SQL 예외, stmt 닫기, SQL : " + sql, e);
        }
      }

    }

    return id;
  }

  public static int update(Connection dbConn, SecSql sql) {
    int affectedRows = 0;

    PreparedStatement stmt = null;

    try {
      stmt = sql.getPreparedStatement(dbConn);
      affectedRows = stmt.executeUpdate();
    } catch (SQLException e) {
      throw new SQLErrorException("SQL 예외, SQL : " + sql, e);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          throw new SQLErrorException("SQL 예외, stmt 닫기, SQL : " + sql, e);
        }
      }
    }

    return affectedRows;
  }

  public static int delete(Connection dbConn, SecSql sql) {
    return update(dbConn, sql);
  }
}