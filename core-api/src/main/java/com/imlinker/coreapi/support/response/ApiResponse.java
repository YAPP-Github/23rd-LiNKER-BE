package com.imlinker.coreapi.support.response;

import com.imlinker.error.ErrorMessage;
import com.imlinker.error.ErrorType;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

  private ResponseType responseType;
  private T data;
  private ErrorMessage error;
  private String debug;

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(ResponseType.SUCCESS, data, null, null);
  }

  public static ApiResponse<Object> error(ErrorType errorType, String errorData, String debug) {
    return new ApiResponse<>(
        ResponseType.ERROR, null, new ErrorMessage(errorType, errorData), debug);
  }
}
