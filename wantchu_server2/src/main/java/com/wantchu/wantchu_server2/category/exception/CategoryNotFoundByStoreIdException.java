package com.wantchu.wantchu_server2.category.exception;

public class CategoryNotFoundByStoreIdException extends CategoryException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "해당 store_id를 가진 카테고리 정보가 존재하지 않습니다.";

    public CategoryNotFoundByStoreIdException() {
        super(ERRNO, MESSAGE);
    }

    @Override
    public String getMessage() {return MESSAGE;}

    @Override
    public int getErrno() {
        return ERRNO;
    }
}
