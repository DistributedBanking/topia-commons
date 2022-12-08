package io.bitexpress.topia.commons.data.sql;

import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GrantSqlMaker {

    private static final Logger logger = LoggerFactory.getLogger(GrantSqlMaker.class);

    private static String sequencePattern = "create sequence ";
    private static String tablePattern = "create table ";

    private static String grant = "grant ${auth} on ${schema}.${objectName} to ${user};";
    private static String alias = "create or replace synonym ${user}.${objectName} for ${schema}.${objectName};";

    public static List<String> grant(InputStream inputStream, String usernamePostfix) throws IOException {
        String string = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        return grant(Arrays.asList(StringUtils.split(string, "\r\n")), usernamePostfix);
    }

    public static List<String> grant(List<String> sqls, String usernamePostfix) {
        List<String> grantList = new ArrayList<>();
        for (String line : sqls) {
            Param param = getParam(line, usernamePostfix);
            if (param != null) {
                grantList.addAll(grant(param));
            }
        }
        return grantList;
    }

    public static Param getParam(String sql, String usernamePostfix) {
        if (StringUtils.isNotBlank(StringUtils.substringAfter(sql, sequencePattern))) {
            String fullNameObject = StringUtils.substringBefore(StringUtils.substringAfter(sql, sequencePattern), ";")
                    .trim();
            logger.trace("fullNameObject:{}", fullNameObject);
            Param param = new Param();
            param.schema = StringUtils.substringBefore(fullNameObject, ".");
            param.objectName = StringUtils.substringAfter(fullNameObject, ".");
            param.user = param.schema + usernamePostfix;
            param.auth = "select";
            return param;
        } else if (StringUtils.isNotBlank(StringUtils.substringAfter(sql, tablePattern))) {
            String fullNameObject = StringUtils.substringAfter(sql, tablePattern).trim();
            logger.trace("fullNameObject:{}", fullNameObject);
            Param param = new Param();
            param.schema = StringUtils.substringBefore(fullNameObject, ".");
            param.objectName = StringUtils.substringAfter(fullNameObject, ".");
            param.user = param.schema + usernamePostfix;
            param.auth = "select,insert,update,delete";
            return param;
        } else {
            return null;
        }
    }

    @Data
    public static class Param {
        public String schema;
        public String objectName;
        public String user;
        public String auth;

    }

    public static List<String> grant(Param param) {
        Map<String, String> paramMap;
        try {
            paramMap = BeanUtils.describe(param);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ContextedRuntimeException(e);
        }

        StringSubstitutor sub = new StringSubstitutor(paramMap);
        String grantSql = sub.replace(grant);
        String aliasSql = sub.replace(alias);
        return Arrays.asList(grantSql, aliasSql, "");
    }

}
