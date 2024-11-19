package com.study.home_project.aop;

import com.study.home_project.dto.AuthNicknameCheckRequestDto;
import com.study.home_project.dto.AuthSignupRequestDto;
import com.study.home_project.dto.AuthUsernameCheckRequestDto;
import com.study.home_project.dto.OAuth2SignupRequestDto;
import com.study.home_project.security.exception.ValidException;
import com.study.home_project.repository.UserMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ValidAop {

    @Autowired
    private UserMapper userMapper;

    @Pointcut("@annotation(com.study.home_project.annotation.ValidAspect)")
    private void pointCut() {

    }

    @Around("pointCut()")
    public Object around (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();

        Object[] args = proceedingJoinPoint.getArgs();

        BeanPropertyBindingResult bindingResult = null;

        for (Object arg : args) {
            if (arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
            }
        }
        
        // 아이디 중복 체크
        if(methodName.equals("usernameCheck")) {
            AuthUsernameCheckRequestDto authUsernameCheckRequestDto = null;
            for(Object arg : args) {
                if(arg.getClass() == AuthUsernameCheckRequestDto.class) {
                    authUsernameCheckRequestDto = (AuthUsernameCheckRequestDto) arg;
                }
            }
            if(userMapper.findUserByUsername(authUsernameCheckRequestDto.getUsername()) != null){
                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 사용자이름입니다.");
                bindingResult.addError(objectError);
            }
        }

        if(methodName.equals("nicknameCheck")) {
            AuthNicknameCheckRequestDto authNicknameCheckRequestDto = null;
            for(Object arg : args) {
                if(arg.getClass() == AuthNicknameCheckRequestDto.class) {
                    authNicknameCheckRequestDto = (AuthNicknameCheckRequestDto) arg;
                }
            }
            if(userMapper.findUserByNickname(authNicknameCheckRequestDto.getNewNickname()) != null){
                ObjectError objectError = new FieldError("newNickname", "newNickname", "이미 존재하는 닉네임입니다.");
                bindingResult.addError(objectError);
            }
        }

        if(methodName.equals("signup")) {
            AuthSignupRequestDto authSignupRequestDto = null;
            for(Object arg : args) {
                if(arg.getClass() == AuthSignupRequestDto.class) {
                    authSignupRequestDto = (AuthSignupRequestDto) arg;
                }
            }
//            if(userMapper.findUserByUsername(authSignupRequestDto.getUsername()) != null){
//                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 사용자이름입니다.");
//                bindingResult.addError(objectError);
//            }
//            if(userMapper.findUserByNickname(authSignupRequestDto.getNickname()) != null) {
//                ObjectError objectError = new FieldError("nickname", "nickname", "이미 존재하는 닉네임입니다.");
//                bindingResult.addError(objectError);
//            }
        }

        if(methodName.equals("oAuth2Signup")) {
            OAuth2SignupRequestDto oAuth2SignupRequestDto = null;
            for(Object arg : args) {
                if(arg.getClass() == OAuth2SignupRequestDto.class) {
                    oAuth2SignupRequestDto = (OAuth2SignupRequestDto) arg;
                }
            }
            if(userMapper.findUserByUsername(oAuth2SignupRequestDto.getUsername()) != null){
                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 사용자이름입니다.");
                bindingResult.addError(objectError);
            }
//            if(userMapper.findUserByNickname(oAuth2SignupRequestDto.getNickname()) != null) {
//                ObjectError objectError = new FieldError("nickname", "nickname", "이미 존재하는 닉네임입니다.");
//                bindingResult.addError(objectError);
//            }
            if(userMapper.findUserByOAuth2name(oAuth2SignupRequestDto.getOauth2Name()) != null) {
                ObjectError objectError = new FieldError("message", "error", "로그인한 기록이 있습니다.");
                bindingResult.addError(objectError);
            }
        }


        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                String fieldName = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                errorMap.put(fieldName, message);
            }
            throw new ValidException(errorMap);
        }

        return proceedingJoinPoint.proceed();
    }
}
