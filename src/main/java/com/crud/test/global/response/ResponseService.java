package com.crud.test.global.response;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 내부 로직을 처리해줌
@RequiredArgsConstructor // 초기화되지 않은 fianl 필드나 notnull 필드의 생성자를 생성함
public class ResponseService {
    public enum CommonResponse{
        SUCCESS(0, "성공하셨습니다."),
        FAIL(-1, "실패하셨습니다.");

        int code;
        String message;

        CommonResponse(int code, String message){
            this.code = code;
            this.message = message;
        }
        public int getCode(){
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public <T> SingleResult<T> getSingleResult(T data){
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }
    public CommonResult getSuccessResult(){
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }
    private void setSuccessResult(CommonResult result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMessage(CommonResponse.SUCCESS.getMessage());
    }
}
