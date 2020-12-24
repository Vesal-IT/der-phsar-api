package com.kshrd.derphsar_api.rest.message;


import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageProperties {
    private MessageSource source;
    private MessageSourceAccessor accessor;

    @Autowired
    public void setSource(MessageSource source) {
        this.source = source;
    }

    @PostConstruct
    private void init(){
        accessor = new MessageSourceAccessor(source);
    }

    public String selected(String resourceName){
        return accessor.getMessage("message.selected",
                new Object[] {resourceName});
    }

    public String selectedOne(String resourceName){
        return accessor.getMessage("message.selected-one",
                new Object[] {resourceName});
    }

    public String inserted(String resourceName){
        return accessor.getMessage("message.inserted",
                new Object[] {resourceName});
    }

    public String updated(String resourceName){
        return accessor.getMessage("message.updated",
                new Object[] {resourceName});
    }


    public String deleted(String resourceName){
        return accessor.getMessage("message.deleted",
                new Object[] {resourceName});
    }

    public String insertError(String resourceName){
        return accessor.getMessage("message.insert-error",
                new Object[] {resourceName});
    }

    public String updateError(String resourceName){
        return accessor.getMessage("message.update-error",
                new Object[] {resourceName});
    }

    public String deleteError(String resourceName){
        return accessor.getMessage("message.delete-error",
                new Object[] {resourceName});
    }


    public String hasEmptyValue(String resourceName){
        return accessor.getMessage("message.has-empty-value",
                new Object[] {resourceName});
    }

    public String hasNoRecords(String resourceName){
        return accessor.getMessage("message.has-no-records",
                new Object[] {resourceName});
    }


    public String hasNullValue(String resourceName){
        return accessor.getMessage("message.has-null-value",
                new Object[] {resourceName});
    }



    public String hasNoRecord(String resourceName){
        return accessor.getMessage("message.has-no-record",
                new Object[] {resourceName});
    }

    public String loginSuccess(String resourceName){
        return accessor.getMessage("message.login-success",
                new Object[] {resourceName});
    }
}
