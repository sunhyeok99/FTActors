package com.a602.actors.global.common.enums;

public enum FolderType{

    MONTAGE_PATH("montages/"),
    PORTFOLIO_PATH("portfolios/"),
    PROFILE_PATH("profiles/"),
    REPORT_PATH("reports/"),
    RECRUIT_PATH("recruits/"),
    APPLY_PATH("applies/");
    private final String folderPath;

    FolderType(String folderPath){
        this.folderPath = folderPath;
    }

    public String getPath(){
        return folderPath;
    }


}