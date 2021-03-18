package com.guhun.locatorapplication07.data.model;


public class MapModel {
    private int mapId;
    private String mapName;
    private String mapUrl;
    private int mapX;
    private int mapY;

    @Override
    public String toString() {
        return "MapModel{" +
                "mapId=" + mapId +
                ", mapName='" + mapName + '\'' +
                ", mapUrl='" + mapUrl + '\'' +
                ", mapX=" + mapX +
                ", mapY=" + mapY +
                '}';
    }
    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public int getMapX() {
        return mapX;
    }

    public void setMapX(int mapX) {
        this.mapX = mapX;
    }

    public int getMapY() {
        return mapY;
    }

    public void setMapY(int mapY) {
        this.mapY = mapY;
    }
}
