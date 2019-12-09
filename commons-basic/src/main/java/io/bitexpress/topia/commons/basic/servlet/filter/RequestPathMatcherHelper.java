package io.bitexpress.topia.commons.basic.servlet.filter;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class RequestPathMatcherHelper {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    private List<String> excludePathList = new ArrayList<>();

    public boolean match(HttpServletRequest request) {
        String path = new UrlPathHelper().getPathWithinApplication(request);
        for (String excludePath : excludePathList) {
            if (antPathMatcher.match(excludePath, path)) {
                return true;
            }
        }
        return false;
    }

    public void setExcludePathList(@NotNull List<String> excludePathList) {
        this.excludePathList = excludePathList;
    }

}
