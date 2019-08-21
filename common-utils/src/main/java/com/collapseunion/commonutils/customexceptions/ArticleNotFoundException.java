package com.collapseunion.commonutils.customexceptions;

/**
 * 文章未找到异常
 *
 * @author CloudSen
 */
public class ArticleNotFoundException extends BusinessException {
    public ArticleNotFoundException() {
        super();
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException(String message, Object data) {
        super(message, data);
    }

    public ArticleNotFoundException(String originStr, Object... objects) {
        super(originStr, objects);
    }

    public ArticleNotFoundException(String originStr, Object data, Object... objects) {
        super(originStr, data, objects);
    }
}
